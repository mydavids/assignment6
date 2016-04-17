package com.yusirydavids.barsystem.domain;

/**
 * Created by Yusiry Davids on 4/17/2016.
 */
public class OrderStock {
    private int orderNumber;
    private String stockID;
    private int amount;

    public OrderStock() {
    }

    public OrderStock(int orderNumber, String stockID, int amount) {
        this.orderNumber = orderNumber;
        this.stockID = stockID;
        this.amount = amount;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getStockID() {
        return stockID;
    }

    public void setStockID(String stockID) {
        this.stockID = stockID;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
