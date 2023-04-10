package org.example.repository;

import com.mysql.cj.x.protobuf.MysqlxCrud;
import org.example.entity.ClientEntity;
import org.example.entity.OrderEntity;
import org.example.model.OrderItem;

import java.sql.*;
import java.util.List;

public class OrderRepository extends BaseRepository {

    public Long save(OrderEntity orderEntity) {
        try {
            Connection con = getConnection();
            PreparedStatement statement = con.prepareStatement(
                    "INSERT INTO public.orders (client_id, date, total_price) " +
                            "VALUES ( " + orderEntity.getClientId() + ", " +
                            "NOW()," +
                            " " + orderEntity.getTotalPrice() + "); ",
                    Statement.RETURN_GENERATED_KEYS);
            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getLong(1);
                } else {
                    throw new SQLException("Creating order failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            System.out.println("SQL exception occurred" + e);
        }
        return null;
    }

    private OrderEntity mapToEntity(String str) throws SQLException {
        ResultSet rs = getStatement().executeQuery(str);
        rs.next();

        return new OrderEntity(
                rs.getLong("id"),
                rs.getLong("client_id"),
                rs.getDate("date"),
                rs.getBigDecimal("total_price")
        );
    }
}
