package com.yusirydavids.barsystem.domain;

/**
 * Created by Yusiry Davids on 4/17/2016.
 */
public class Contact {
    private String cellNumber;
    private String address;
    private String postalCode;

    public Contact(String cellNumber, String address, String postalCode) {
        this.cellNumber = cellNumber;
        this.address = address;
        this.postalCode = postalCode;
    }

    public Contact(){

    }

    public String getCellNumber() {
        return cellNumber;
    }

    public void setCellNumber(String cellNumber) {
        this.cellNumber = cellNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}
