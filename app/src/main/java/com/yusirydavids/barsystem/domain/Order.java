package com.yusirydavids.barsystem.domain;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Yusiry Davids on 4/17/2016.
 */
public class Order implements Serializable{
    private static final long serialVersionUID = 1L;
    private String id;
    private String date;
    private double amount;

    private ArrayList<Stock> stock;

    public String getId(){
        return id;
    }

    public String getDate() {
        return date;
    }

    public double getAmount() {
        return amount;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Order(){

    }

    public ArrayList<Stock> getStock() {
        return stock;
    }

    public void setStock(ArrayList<Stock> stock) {
        this.stock = stock;
    }

    private Order(Builder builder){
        this.id = builder.id;
        this.date = builder.date;
        this.amount = builder.amount;
        this.stock = builder.stock;
    }

    public static class Builder{
        private String id;
        private String date;
        private double amount;
        private ArrayList<Stock> stock;

        public Builder id(String val){
            this.id = val;
            return this;
        }

        public Builder date(String val){
            this.date = val;
            return this;
        }

        public Builder amount(double val){
            this.amount = val;
            return this;
        }

        public Builder stock(ArrayList<Stock> val){
            this.stock = val;
            return this;
        }

        public Builder copy(Order val){
            this.id = val.id;
            this.date = val.date;
            this.amount = val.amount;
            this.stock = val.stock;
            return this;
        }

        public Order build(){
            return new Order(this);
        }
    }
}
