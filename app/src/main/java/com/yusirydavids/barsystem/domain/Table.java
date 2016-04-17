package com.yusirydavids.barsystem.domain;

import java.io.Serializable;

/**
 * Created by Yusiry Davids on 4/17/2016.
 */
public class Table implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private int tableNumber;
    private int seating;
    private String location;

    public String getId() {
        return id;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public int getSeating() {
        return seating;
    }

    public String getLocation() {
        return location;
    }

    public Table(){

    }

    private Table(Builder builder){
        this.id = builder.id;
        this.tableNumber = builder.tableNumber;
        this.seating = builder.seating;
        this.location = builder.location;
    }

    public static class Builder{
        private String id;
        private int tableNumber;
        private int seating;
        private String location;

        public Builder id(String val){
            this.id = val;
            return this;
        }

        public Builder tableNumber(int val){
            this.tableNumber = val;
            return this;
        }

        public Builder seating(int val){
            this.seating = val;
            return this;
        }

        public Builder location(String val){
            this.location = val;
            return this;
        }

        public Builder copy(Table val){
            this.id = val.id;
            this.tableNumber = val.tableNumber;
            this.seating = val.seating;
            this.location = val.location;
            return this;
        }

        public Table build(){
            return new Table(this);
        }
    }
}
