package com.lesson.shop.repository;

import com.lesson.shop.model.OrderModel;
import com.lesson.shop.model.OrderProductModel;
import com.lesson.shop.model.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    @Query("select new com.lesson.shop.model.OrderModel(o.id, cl.name, o.date, o.totalPrice, o.orderStatus) " +
            "from OrderEntity o inner join ClientEntity cl on o.clientId = cl.id")
    List<OrderModel> findAllOrders();
    @Query("select new com.lesson.shop.model.OrderProductModel(p.name, op.count) " +
            "from ProductEntity p inner join OrderProductEntity op on p.id = op.productId where op.orderId = ?1")
    List<OrderProductModel> getAllOrderProductById (Long id);
}
