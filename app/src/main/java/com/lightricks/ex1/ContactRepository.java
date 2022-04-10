package com.lightricks.ex1;

import java.util.ArrayList;

public class ContactRepository {
    private static ContactRepository instance = null;
    private ArrayList<Contact> contactArrayList;

    private ContactRepository() {
        // on below line we are initializing our variables.
        contactArrayList = new ArrayList<>();

        Contact andi = new Contact("Andi Lightricksovitch",
                "android.devices@lightricks.com",
                "+972 525 111 111");
        contactArrayList.add(andi);

        Contact raz = new Contact("Raz Karl",
                "rkarl@lightricks.com",
                "+972 527 221 113");
        contactArrayList.add(raz);
    }

    public static ContactRepository getInstance() {
        if (instance == null) {
            instance = new ContactRepository();
        }
        return instance;
    }

    public ArrayList<Contact> getContacts() {
        return contactArrayList;
    }
}
