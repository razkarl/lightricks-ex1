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

        // Ask for phone contacts permissions
        if (!hasReadContactsPermission(activity)) {
            requestReadContactsPermission(activity);
        }

        // Get contacts from phone (with permissions)
        else {
            // Initialize unique resource identifier
            Uri contentUri = ContactsContract.Contacts.CONTENT_URI;
            // Sort contacts by ascending order
            String sortOrder = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC";
            // Initialize cursor
            Cursor cursor = activity.getContentResolver().query(contentUri, null, null, null, sortOrder);

            if (cursor.getCount() > 0) {
                // When count is greater than 0, use a while loop
                while (cursor.moveToNext()) {
                    // Get contact id
                    String id = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.Contacts._ID));
                    String name = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME));
                    Uri uriPhone = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
                    String selection = ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " =?";
                    Cursor phoneCursor = activity.getContentResolver().query(
                            uriPhone, null, selection, new String[]{id}, null);
                    if (phoneCursor.moveToNext()) {
                        String number = phoneCursor.getString(phoneCursor.getColumnIndexOrThrow(
                                ContactsContract.CommonDataKinds.Phone.NUMBER));
                        String email = phoneCursor.getString(phoneCursor.getColumnIndexOrThrow(
                                ContactsContract.CommonDataKinds.Email.DATA));
                        Contact contact = new Contact(name, email, number);
                        contacts.add(contact);
                        phoneCursor.close();
                    }
                }
                cursor.close();
            }
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
