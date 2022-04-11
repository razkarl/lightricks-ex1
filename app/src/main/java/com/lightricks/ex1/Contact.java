package com.lightricks.ex1;

import android.content.Context;

import java.util.Locale;

public class Contact {
    private String name;
    private String email;
    private String number;

    public Contact(String name,
                   String email,
                   String number) {
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

    public String getImageName() { return name.replace(' ', '_').toLowerCase(Locale.ROOT); }
}
