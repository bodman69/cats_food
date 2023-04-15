package org.example.entity;

public class ClientEntity {
    private long id;
    private String name;
    private String phoneNumber;

    public ClientEntity(long id, String name, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Client " +
                "id=" + id +
                ", name = " + name +
                ", phoneNumber = " + phoneNumber +
                "\n" + "---------------------------------------------------------" + "\n"
                ;
    }
}
