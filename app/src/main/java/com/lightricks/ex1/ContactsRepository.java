package com.lightricks.ex1;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class ContactsRepository {
    private static ContactsRepository instance = null;
    private static ArrayList<Contact> contactsHardCoded = null;

    public static ContactsRepository getInstance() {
        if (instance == null) {
            instance = new ContactsRepository();
        }
        return instance;
    }

    private ContactsRepository() {}

    public ArrayList<Contact> getContacts(Activity activity) {
        return getConactsFromPhone(activity);
    }

    private ArrayList<Contact> getContactsHardCoded() {
        // Initialize on first usage, then cache
        if (contactsHardCoded == null) {
            contactsHardCoded = new ArrayList<>();

            Contact andi = new Contact("Andi Lightricksovitch",
                    "android.devices@lightricks.com",
                    "+972 525 111 111");
            contactsHardCoded.add(andi);

            Contact raz = new Contact("Raz Karl",
                    "rkarl@lightricks.com",
                    "+972 527 221 113");
            contactsHardCoded.add(raz);

            Contact omri = new Contact("Omri Hamelech",
                    "gever@lightricks.com",
                    "+972 501 234 567");
            contactsHardCoded.add(omri);
        }
        return contactsHardCoded;
    }

    private ArrayList<Contact> getConactsFromPhone(Activity activity) {
        ArrayList<Contact> contacts = new ArrayList<>();

        // Validate permissions to read contacts from phone
        if (!hasReadContactsPermission(activity)) {
            // Ask for permissions, and return an empty contacts list
            requestReadContactsPermission(activity);
            return contacts;
        }

        // Read contacts from phone (with permissions)
        Cursor cursor = activity.getContentResolver().query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null,
                null,
                null,
                ContactsContract.Contacts.DISPLAY_NAME + " ASC");

        if ((cursor != null ? cursor.getCount() : 0) > 0) {
            while (cursor.moveToNext()) {
                String name = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME));
                String number = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER));
                String email = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Email.DATA));
                Contact contact = new Contact(name, email, number);
                contacts.add(contact);
            }
        }
        if (cursor != null){
            cursor.close();
        }
        return contacts;
    }

    private void requestReadContactsPermission(Activity activity) {
        ActivityCompat.requestPermissions(activity,
                new String[]{Manifest.permission.READ_CONTACTS}, 100);
    }

    private boolean hasReadContactsPermission(Activity activity) {
        return ContextCompat.checkSelfPermission(activity,
                Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED;
    }
}
