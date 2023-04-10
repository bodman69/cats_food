package org.example.entity;


import java.math.BigDecimal;
import java.sql.Date;

public class OrderEntity {
    private long orderId;
    private long clientId;
    private Date date;
    private BigDecimal totalPrice;

    public OrderEntity(long clientId, BigDecimal totalPrice) {
        this.clientId = clientId;
        this.totalPrice = totalPrice;
    }

    public OrderEntity(long orderId, long clientId, Date date, BigDecimal totalPrice) {
        this.orderId = orderId;
        this.clientId = clientId;
        this.date = date;
        this.totalPrice = totalPrice;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public long getClientId() {
        return clientId;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
