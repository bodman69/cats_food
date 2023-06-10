package com.lesson.shop.service;

import com.lesson.shop.exception.BadRequestException;
import com.lesson.shop.exception.EntityNotFoundException;
import com.lesson.shop.model.OrderModel;
import com.lesson.shop.model.entity.OrderEntity;
import com.lesson.shop.model.entity.OrderProductEntity;
import com.lesson.shop.model.entity.ProductEntity;
import com.lesson.shop.model.enums.OrderStatus;
import com.lesson.shop.model.request.OrderProductRequest;
import com.lesson.shop.model.request.OrderRequest;
import com.lesson.shop.model.request.OrderUpdateRequest;
import com.lesson.shop.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderProductService orderProductService;
    private final ClientService clientService;
    private final ProductService productService;


    public void create(OrderRequest request) {
        List<ProductEntity> products = productService.findAllByIds(
                request.getProducts().stream()
                        .map(OrderProductRequest::getProductId)
                        .collect(Collectors.toList())
        );

        validateCreateRequest(request, products);
        OrderEntity orderEntity = createOrder(request, products);
        createOrderProducts(request, orderEntity);
        updateProductCount(request, products);

    }

    public List<OrderModel> getAllOrders() {
        return orderRepository.findAllOrders();
    }

    private void validateCreateRequest(OrderRequest request, List<ProductEntity> products) {
        if (!clientService.existById(request.getClientId())) {
            throw new EntityNotFoundException("Client with id " + request.getClientId() + " doesn't exist");
        }

        if (request.getProducts().isEmpty()) {
            throw new BadRequestException("Products should be specified");
        }


        Map<Long, ProductEntity> productMap = products.stream()
                .collect(Collectors.toMap(ProductEntity::getId, Function.identity()));

        request.getProducts().forEach(requestProduct -> {
            if (!productMap.containsKey(requestProduct.getProductId())) {
                throw new EntityNotFoundException("Product with id = " + requestProduct.getProductId() + " doesn't exist");
            } else if (productMap.get(requestProduct.getProductId()).getCount() < requestProduct.getCount()) {
                throw new BadRequestException("Product with id = " + requestProduct.getProductId() + " doesn't have enough capacity");
            }
        });
    }

    private OrderEntity createOrder(OrderRequest request, List<ProductEntity> products) {
        Map<Long, ProductEntity> productMap = products.stream()
                .collect(Collectors.toMap(ProductEntity::getId, Function.identity()));

        BigDecimal totalPrice = new BigDecimal(0);

        for (OrderProductRequest productRequest : request.getProducts()) {
            totalPrice = totalPrice.add(
                    BigDecimal.valueOf(productRequest.getCount()).multiply(
                            productMap.get(productRequest.getProductId()).getPrice()
                    )
            );
        }

        OrderEntity orderEntity = OrderEntity.builder()
                .clientId(request.getClientId())
                .date(new Date(Instant.now().toEpochMilli()))
                .totalPrice(totalPrice)
                .orderStatus(OrderStatus.NEW)
                .build();

        return orderRepository.save(orderEntity);
    }

    private void createOrderProducts(OrderRequest request, OrderEntity orderEntity) {
        List<OrderProductEntity> orderProductEntityList = new ArrayList<>();
        request.getProducts().forEach(product -> {
            orderProductEntityList.add(
                    OrderProductEntity.builder()
                            .orderId(orderEntity.getId())
                            .productId(product.getProductId())
                            .count(product.getCount())
                            .build()
            );
        });

        orderProductService.save(orderProductEntityList);
    }

    private void updateProductCount(OrderRequest request, List<ProductEntity> products) {
        Map<Long, ProductEntity> productMap = products.stream()
                .collect(Collectors.toMap(ProductEntity::getId, Function.identity()));

        for (OrderProductRequest productRequest : request.getProducts()) {
            productMap.get(productRequest.getProductId()).setCount(
                    productMap.get(productRequest.getProductId()).getCount() - productRequest.getCount()
            );
        }
        List<ProductEntity> productEntities = new ArrayList<>(productMap.values());
        productService.saveAll(productEntities);
    }

    private void updateOrderProductCount(OrderUpdateRequest orderUpdateRequest, List<ProductEntity> products) {
        Map<Long, ProductEntity> productMap = products.stream()
                .collect(Collectors.toMap(ProductEntity::getId, Function.identity()));

        for (OrderProductRequest productRequest : orderUpdateRequest.getOrderProducts()) {
            productMap.get(productRequest.getProductId()).setCount(
                    productMap.get(productRequest.getProductId()).getCount() + productRequest.getCount()
            );
        }
        List<ProductEntity> productEntities = new ArrayList<>(productMap.values());
        productService.saveAll(productEntities);
    }

    @Transactional
    public void updateOrder(Long id, OrderUpdateRequest orderUpdateRequest) {
        OrderEntity orderEntity = orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order with id " + id + " doesn't exist"));

        if (orderUpdateRequest.getOrderStatus() != null) {
            orderEntity.setOrderStatus(orderUpdateRequest.getOrderStatus());
            orderRepository.save(orderEntity);
        }
        if (orderUpdateRequest.getOrderProducts() != null) {
            List<ProductEntity> productList = productService.findAllByIds(
                    orderUpdateRequest.getOrderProducts().stream()
                            .map(OrderProductRequest::getProductId)
                            .collect(Collectors.toList())
            );

            updateOrderProductCount(orderUpdateRequest, productList);

            orderProductService.deleteOrderProduct(id);

            orderEntity = orderRepository.save(orderEntity);
            createOrderProducts(
                    OrderRequest.builder()
                            .clientId(orderEntity.getClientId())
                            .products(orderUpdateRequest.getOrderProducts())
                            .build(),
                    orderEntity
            );

            updateProductCount(
                    OrderRequest.builder()
                            .clientId(orderEntity.getClientId())
                            .products(orderUpdateRequest.getOrderProducts())
                            .build(),
                    productList
            );
        }
    }

    public void deleteById(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new EntityNotFoundException("Order with id " + id + " doesn't exist");
        }
        orderRepository.deleteById(id);
    }

}
