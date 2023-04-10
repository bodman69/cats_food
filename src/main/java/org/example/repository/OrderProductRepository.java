package org.example.repository;

import org.example.entity.OrderProductEntity;
import org.example.model.OrderItem;

import java.sql.SQLException;

public class OrderProductRepository extends BaseRepository {
    public void save(OrderProductEntity orderProductEntity) {
        try {
            getStatement().execute(
                    "INSERT INTO public.order_product VALUES (" +
                            orderProductEntity.getOrderId() + ", " +
                            orderProductEntity.getProductId() + ", " +
                            orderProductEntity.getCount() + ");"
            );
        } catch (SQLException e) {
            System.out.println("SQL exception occurred" + e);
        }
    }

}
