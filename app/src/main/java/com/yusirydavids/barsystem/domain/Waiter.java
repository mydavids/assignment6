package com.yusirydavids.barsystem.domain;

import java.io.Serializable;

/**
 * Created by Yusiry Davids on 4/17/2016.
 */
public class Waiter implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String name;
    private String surname;
    private String idNumber;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public Waiter(){

    }

    private Waiter(Builder builder){
        this.id = builder.id;
        this.idNumber = builder.idNumber;
        this.name = builder.name;
        this.surname = builder.surname;
    }

    public static class Builder{
        private String id;
        private String idNumber;
        private String name;
        private String surname;

        public Builder id(String val){
            this.id = val;
            return this;
        }

        public Builder idNumber(String val){
            this.idNumber = val;
            return this;
        }

        public Builder name(String val){
            this.name = val;
            return this;
        }

        public Builder surname(String val){
            this.surname = val;
            return this;
        }

        public Builder copy(Waiter val){
            this.id = val.id;
            this.idNumber = val.idNumber;
            this.name = val.name;
            this.surname = val.surname;
            return this;
        }

        public Waiter build(){
            return new Waiter(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Manager)) return false;

        Manager role = (Manager) o;

        if (getId() != null ? !getId().equals(role.getId()) : role.getId() != null) return false;
        if (getName() != null ? !getName().equals(role.getName()) : role.getName() != null)
            return false;
        return !(getIdNumber() != null ? !getIdNumber().equals(role.getIdNumber()) : role.getIdNumber() != null);

    }

}
