package com.example.graduationproject.model;

public class Order {
    private int orderId;
    private String orderDate;
    private int orderMoney;
    private int accountId;
    private String orderType;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public int getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(int orderMoney) {
        this.orderMoney = orderMoney;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }
}
