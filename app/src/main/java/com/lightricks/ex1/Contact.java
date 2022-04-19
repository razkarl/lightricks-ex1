package com.lightricks.ex1;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import org.w3c.dom.ls.LSSerializer;

import java.io.Serializable;
import java.util.Locale;

public class Contact {
    private final String name;
    private final String email;
    private final String number;

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

    /**
     * Image name is induced from the contact name, as follows:
     * name      = "Raz Karl"
     * imageName = "raz_karl"
     * Contact images are saved in src/main/res/raw/<contact_name>.<jpg|jpeg|png|...>
     * @return the name of the image resource that corresponds to this contact.
     */
    protected String getImageName() { return name.replace(' ', '_').toLowerCase(Locale.ROOT); }
}
