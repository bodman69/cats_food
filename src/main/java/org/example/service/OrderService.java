package org.example.service;

import org.example.entity.OrderEntity;
import org.example.entity.OrderProductEntity;
import org.example.entity.ProductEntity;
import org.example.model.OrderItem;
import org.example.repository.OrderRepository;
import org.example.repository.ProductRepository;


import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class OrderService {
    private final ClientService clientService;
    private final ProductService productService;
    private final OrderRepository orderRepository;
    private final OrderProductsService orderProductsService;
    private final ProductRepository productRepository;

    public OrderService() {
        this.productService = new ProductService();
        this.clientService = new ClientService();
        this.orderRepository = new OrderRepository();
        this.orderProductsService = new OrderProductsService();
        this.productRepository = new ProductRepository();
    }

    public void createOrder(long clientId, List<OrderItem> orderItemList) {
        if (validate(clientId, orderItemList)) {

            BigDecimal totalPrice = getProductsEntities(orderItemList).stream()
                    .map(ProductEntity::getPrice)
                    .reduce(BigDecimal.valueOf(0.0), BigDecimal::add);

            OrderEntity orderEntity = new OrderEntity(clientId, totalPrice);
            Long orderId = orderRepository.save(orderEntity); //1 -> insert into order table -> id

            orderItemList.forEach(orderItem -> {
                OrderProductEntity orderProductEntity = new OrderProductEntity(
                        orderId,
                        orderItem.getProductId(),
                        orderItem.getCount()
                );

                orderProductsService.saveOrderItem(orderProductEntity);
                updateProductCount(orderProductEntity, productService.findProduct(orderProductEntity.getProductId()));
            }); //2 -> insert all order items to order_product
        }
    }

    private boolean validate(long clientId, List<OrderItem> orderItemList) {
        if (!clientService.existByClientId(clientId)) {
            System.out.println("Client with id : " + clientId + " does not exist");
            return false;
        }

        return validateOrderItems(orderItemList);
    }

    private boolean validateOrderItems(List<OrderItem> orderItemList) {
        Map<Long, ProductEntity> productIdsMap = getProductsEntities(orderItemList).stream()
                .collect(Collectors.toMap(ProductEntity::getId, Function.identity()));

        for (OrderItem orderItem : orderItemList) {
            if (!productIdsMap.containsKey(orderItem.getProductId())) {
                System.out.println("product with id : " + orderItem.getProductId() + " doesn't exist");
                return false;
            }

            if (productIdsMap.get(orderItem.getProductId()).getCount() < orderItem.getCount()) {
                System.out.println("product with id : " + orderItem.getProductId() + " doesn't have enough capacity");
                return false;
            }
        }

        return true;
    }

    private List<ProductEntity> getProductsEntities(List<OrderItem> orderItemList) {
        List<Long> productIds = orderItemList.stream()
                .map(OrderItem::getProductId)
                .toList();

        return productService.findAllByProductIdIn(productIds);
    }

    private void updateProductCount(OrderProductEntity orderProductEntity, ProductEntity productEntity) {
        long newProductCount = productEntity.getCount() - orderProductEntity.getCount();
        productService.updateCount(productEntity.getId(), newProductCount);
    }
}
