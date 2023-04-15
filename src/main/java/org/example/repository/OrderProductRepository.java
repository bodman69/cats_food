package org.example.repository;

import org.example.entity.OrderProductEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public List<OrderProductEntity> findAllOrderProducts() {
        try {
            return mapToEntityList("SELECT * FROM public.order_product");
        } catch (SQLException e) {
            System.out.println("SQL exception occurred" + e);
        }

        return new ArrayList<>();
    }

    private List<OrderProductEntity> mapToEntityList(String str) throws SQLException {
        List<OrderProductEntity> resultList = new ArrayList<>();
        ResultSet rs = getStatement().executeQuery(str);
        while (rs.next()) {
            resultList.add(
                    new OrderProductEntity(
                            rs.getLong("order_id"),
                            rs.getLong("product_id"),
                            rs.getLong("count")
                    )
            );
        }

        return resultList;
    }
}
