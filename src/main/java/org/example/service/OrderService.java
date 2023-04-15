package org.example.service;

import org.example.entity.OrderEntity;
import org.example.entity.OrderProductEntity;
import org.example.entity.ProductEntity;
import org.example.model.OrderItem;
import org.example.model.OrderProductItem;
import org.example.model.ProductItem;
import org.example.repository.OrderRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class OrderService {
    private final ClientService clientService;
    private final ProductService productService;
    private final OrderRepository orderRepository;
    private final OrderProductService orderProductService;

    public OrderService() {
        this.productService = new ProductService();
        this.clientService = new ClientService();
        this.orderRepository = new OrderRepository();
        this.orderProductService = new OrderProductService();
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

                orderProductService.saveOrderItem(orderProductEntity);
                updateProductCount(orderProductEntity, productService.findProduct(orderProductEntity.getProductId()));
            }); //2 -> insert all order items to order_product
        }
    }

    public List<OrderProductItem> getAllOrders() {
        List<OrderProductEntity> orderProductEntities = orderProductService.findAll();
        Map<Long, List<ProductItem>> ordersMap = new HashMap<>();
        for (OrderProductEntity orderProduct : orderProductEntities) {
            if (!ordersMap.containsKey(orderProduct.getOrderId())) {
                List<ProductItem> items = new ArrayList<>();
                items.add(
                        new ProductItem(
                                productService.getProductName(orderProduct.getProductId()),
                                orderProduct.getCount()
                        )
                );
                ordersMap.put(orderProduct.getOrderId(), items);
            } else {
                ordersMap.get(orderProduct.getOrderId()).add(
                        new ProductItem(
                                productService.getProductName(orderProduct.getProductId()),
                                orderProduct.getCount()
                        )
                );
            }
        }

        List<OrderProductItem> allOrdersList = new ArrayList<>();
        for (Map.Entry<Long, List<ProductItem>> map : ordersMap.entrySet()) {
            allOrdersList.add(new OrderProductItem(map.getKey(), map.getValue()));
        }
        return allOrdersList;
    }
    public void deleteOrder(long orderId){
        if (!orderRepository.existOrderById(orderId)){
            System.err.println("Order not exist");
        }else {
            orderRepository.deleteOrderById(orderId);
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
        Long newProductCount = productEntity.getCount() - orderProductEntity.getCount();
        productService.update(productEntity.getId(), null, newProductCount, null);
    }
}
