package com.yusirydavids.barsystem.domain;

import java.io.Serializable;

/**
 * Created by Yusiry Davids on 4/17/2016.
 */
public class Order implements Serializable{
    private static final long serialVersionUID = 1L;
    private String id;
    private String date;
    private double amount;

    public String getId(){
        return id;
    }

    public String getDate() {
        return date;
    }

    public double getAmount() {
        return amount;
    }

    public Order(){

    }

    private Order(Builder builder){
        this.id = builder.id;
        this.date = builder.date;
        this.amount = builder.amount;
    }

    public static class Builder{
        private String id;
        private String date;
        private double amount;

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

        public Builder copy(Order val){
            this.id = val.id;
            this.date = val.date;
            this.amount = val.amount;
            return this;
        }

        public Order build(){
            return new Order(this);
        }
    }
}
