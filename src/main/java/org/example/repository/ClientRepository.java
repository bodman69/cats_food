package org.example.repository;

import org.example.entity.ClientEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientRepository extends BaseRepository {

    public void saveClient(String name, String phoneNumber) {
        try {
            getStatement().execute(
                    "INSERT INTO public.client(name, phone_number)" +
                            " VALUES ('" + name + "','" + phoneNumber + "');"
            );
        } catch (SQLException e) {
            System.out.println("SQL exception occurred" + e);
        }
    }

    public ClientEntity findClientById(long id) {
        try {
            return mapToEntity("SELECT * FROM client where id = '" + id + "'");
        } catch (SQLException e) {
            System.out.println("SQL exception occurred" + e);
        }
        return null;
    }

    public List<ClientEntity> findAllClients() {
        try {
            return mapToEntityList("SELECT * FROM client");
        } catch (SQLException e) {
            System.out.println("SQL exception occurred" + e);
        }

        return new ArrayList<>();
    }


    public void updateClient(ClientEntity clientEntity) {
        try {
            getStatement().execute(
                    "UPDATE client " +
                            "SET name = '" + clientEntity.getName() + "', phone_number = '" + clientEntity.getPhoneNumber() + "' " +
                            "WHERE id = '" + clientEntity.getId() + "';"
            );
        } catch (SQLException e) {
            System.out.println("SQL exception occurred" + e);
        }
    }

    public void deleteClientById(long id) {
        try {
            getStatement().execute(
                    "DELETE FROM client WHERE id = '" + id + "';"
            );
        } catch (SQLException e) {
            System.out.println("SQL exception occurred" + e);
        }
    }

    public boolean existByPhoneNumber(String phoneNumber) {
        try {
            ResultSet rs = getStatement().executeQuery(
                    "select count(1) from client where phone_number = '" + phoneNumber + "';"
            );
            rs.next();
            return rs.getInt(1) > 0;
        } catch (SQLException e) {
            System.out.println("SQL exception occurred" + e);
        }
        return false;
    }

    public boolean existClientById(long id) {
        try {
            ResultSet rs = getStatement().executeQuery(
                    "select count(1) from client where id = '" + id + "';"
            );
            rs.next();
            return rs.getInt(1) > 0;
        } catch (SQLException e) {
            System.out.println("SQL exception occurred" + e);
        }
        return false;
    }

    private List<ClientEntity> mapToEntityList(String str) throws SQLException {
        List<ClientEntity> resultList = new ArrayList<>();
        ResultSet rs = getStatement().executeQuery(str);
        while (rs.next()) {
            resultList.add(
                    new ClientEntity(
                            rs.getLong("id"),
                            rs.getString("name"),
                            rs.getString("phone_number")
                    )
            );
        }

        return resultList;
    }

    private ClientEntity mapToEntity(String str) throws SQLException {
        ResultSet rs = getStatement().executeQuery(str);
        rs.next();

        return new ClientEntity(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getString("phone_number")
        );
    }
}
