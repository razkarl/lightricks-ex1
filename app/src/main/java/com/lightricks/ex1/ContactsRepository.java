package com.lightricks.ex1;

import static com.lightricks.ex1.Common.hasReadContactsPermission;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;

import java.util.ArrayList;
import java.util.List;

public class ContactsRepository {
    private static ContactsRepository instance = null;

    public static ContactsRepository getInstance() {
        if (instance == null) {
            instance = new ContactsRepository();
        }
        return instance;
    }

    private ContactsRepository() {}

    public List<Contact> getPhoneContacts(Context context) {
        ArrayList<Contact> contacts = new ArrayList<>();

        // Validate permissions to read contacts from phone
        if (!hasReadContactsPermission(context)) {
            // Ask for permissions, and return an empty contacts list
            // requestReadContactsPermission(activity);
            return contacts;
        }

        // Read contacts from phone (with permissions)
        Cursor contacts_cursor = context.getContentResolver().query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null,
                null,
                null,
                ContactsContract.Contacts.DISPLAY_NAME + " ASC");

        if ((contacts_cursor != null ? contacts_cursor.getCount() : 0) > 0) {
            while (contacts_cursor.moveToNext()) {
                String contactId = contacts_cursor.getString(contacts_cursor.getColumnIndexOrThrow(ContactsContract.Contacts._ID));
                String name = contacts_cursor.getString(contacts_cursor.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME));
                String number = contacts_cursor.getString(contacts_cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER));
                String email = getContactEmail(context, contactId);

                Contact contact = new Contact(name, email, number);
                contacts.add(contact);
            }
        }
        if (contacts_cursor != null){
            contacts_cursor.close();
        }
        return contacts;
    }

    private String getContactEmail(Context context, String contactId) {
        String email = "";
        Cursor email_cursor = context.getContentResolver().query(
                ContactsContract.CommonDataKinds.Email.CONTENT_URI,
                null,
                ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = ?",
                new String[]{contactId},
                null);
        if (email_cursor != null && email_cursor.moveToFirst()) {
            email = email_cursor.getString(email_cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Email.DATA));
            email_cursor.close();
        }
        return email;
    }
}
