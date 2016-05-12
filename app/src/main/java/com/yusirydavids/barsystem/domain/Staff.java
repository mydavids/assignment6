package com.yusirydavids.barsystem.domain;

import java.io.Serializable;

/**
 * Created by Yusiry Davids on 4/17/2016.
 */
public class Staff implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String name;
    private String surname;
    private String idNumber;
    private String password;

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(String accessLevel) {
        this.accessLevel = accessLevel;
    }

    private String accessLevel;

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

    public Staff(){

    }

    private Staff(Builder builder){
        this.id = builder.id;
        this.idNumber = builder.idNumber;
        this.name = builder.name;
        this.surname = builder.surname;
        this.password = builder.password;
        this.accessLevel = builder.accessLevel;
    }

    public static class Builder{
        private String id;
        private String idNumber;
        private String name;
        private String surname;
        private String password;
        private String accessLevel;

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

        public Builder password(String val){
            this.password = val;
            return this;
        }

        public Builder accessLevel(String val){
            this.accessLevel = val;
            return this;
        }

        public Builder copy(Staff val){
            this.id = val.id;
            this.idNumber = val.idNumber;
            this.name = val.name;
            this.surname = val.surname;
            this.password = val.password;
            this.accessLevel = val.accessLevel;
            return this;
        }

        public Staff build(){
            return new Staff(this);
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
