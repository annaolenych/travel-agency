package com.example.travel.model;

public class Customer {

    private Integer customerID;
    private String firstname;
    private String lastname;
    private String email;
    private String passportcode;

    public Customer(Integer customerID, String firstname, String lastname, String email, String passportcode) {
        this.customerID = customerID;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.passportcode = passportcode;
    }

    public Integer getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Integer customerID) {
        this.customerID = customerID;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassportcode() {
        return passportcode;
    }

    public void setPassportcode(String passportcode) {
        this.passportcode = passportcode;
    }
}
