package com.lightricks.ex1;

public class ContactDetails {
    private String name;
    private String email;
    private String number;
    // RKARL TODO: add image

    public ContactDetails(String name,
                          String email,
                          String number){
        this.name = name;
        this.email = email;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
