package org.example.repository;

import org.example.entity.ClientEntity;
import org.example.entity.ProductEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository extends BaseRepository {
    public void saveProduct(String name, long count, double price) {
        try {
            getStatement().execute(
                    "INSERT INTO public.product(name, count, price)" +
                            " VALUES ('" + name + "','" + count + "','" + price + "');"
            );
        } catch (SQLException e) {
            System.out.println("SQL exception occurred" + e);
        }
    }

    public ProductEntity findProductById(long id) {
        try {
           return mapToEntity(
                    "SELECT FROM public.product WHERE id = " + id + ";"
            );
        } catch (SQLException e) {
            System.out.println("SQL exception occurred" + e);
        }
        return null;
    }

    public List<ProductEntity> findAllProducts() {
        try {
            return mapToEntityList("SELECT * FROM product");
        } catch (SQLException e) {
            System.out.println("SQL exception occurred" + e);
        }
        return new ArrayList<>();
    }

    public void changeProductById(long id, String name, long count, double price) {
        try {
            getStatement().execute(
                    "UPDATE public.product " +
                            "SET name = '" + name + "', count = " + count + ", price =" + price + "" +
                            " WHERE id = " + id + ";"
            );
        } catch (SQLException e) {
            System.out.println("SQL exception occurred" + e);
        }
    }

    public void changeProductCount(long productId, long newProductCount) {
        try {
            getStatement().execute(
                    "UPDATE public.product SET count = " + newProductCount + " WHERE id = " + productId + "; "
            );
        } catch (SQLException e) {
            System.out.println("SQL exception occurred" + e);
        }
    }

    public void deleteProductById(long id) {
        try {
            getStatement().execute(
                    "DELETE FROM product WHERE id = '" + id + "';"
            );
        } catch (SQLException e) {
            System.out.println("SQL exception occurred" + e);
        }
    }

    public boolean existProductByName(String name) {
        try {
            ResultSet rs = getStatement().executeQuery(
                    "select count(1) from product where name = '" + name + "';"
            );
            rs.next();
            return rs.getInt(1) > 0;
        } catch (SQLException e) {
            System.out.println("SQL exception occurred" + e);
        }
        return false;
    }

    public boolean existProductById(long id) {
        try {
            ResultSet rs = getStatement().executeQuery(
                    "select count(1) from product where id = " + id + ";"
            );
            rs.next();
            return rs.getInt(1) > 0;
        } catch (SQLException e) {
            System.out.println("SQL exception occurred" + e);
        }
        return false;
    }

    public List<ProductEntity> findAllByProductIdIn(String ids) {
        try {
            return mapToEntityList("SELECT * FROM product where id in (" + ids + ");");
        } catch (SQLException e) {
            System.out.println("SQL exception occurred" + e);
        }
        return new ArrayList<>();
    }

    private List<ProductEntity> mapToEntityList(String str) throws SQLException {
        List<ProductEntity> resultList = new ArrayList<>();
        ResultSet rs = getStatement().executeQuery(str);
        while (rs.next()) {
            resultList.add(
                    new ProductEntity(
                            rs.getLong("id"),
                            rs.getString("name"),
                            rs.getLong("count"),
                            rs.getBigDecimal("price")
                    )
            );
        }

        return resultList;
    }

    private ProductEntity mapToEntity(String str) throws SQLException {
        ResultSet rs = getStatement().executeQuery(str);
        rs.next();

        return new ProductEntity(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getLong("count"),
                rs.getBigDecimal("price")
        );
    }
}
