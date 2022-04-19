package com.lightricks.ex1;

import static com.lightricks.ex1.Common.hasReadContactsPermission;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;

import java.util.ArrayList;
import java.util.List;


public final class ContactsRepository {
    // When you think about it there is no state here -> therefore you don't need class..
    // only set of functions..
    private static ContactsRepository instance = null;

    // 1. Why Singleton? Try to avoid Singletons if you dont have to use them...
    // 2. It isn't thread safe.. You can two instances of this class.
    // 3. Note that I can create more instances by inheriting from this class and then use the
    //    default constructor...
    public static ContactsRepository getInstance() {
        if (instance == null) {
            instance = new ContactsRepository();
        }
        return instance;
    }

    private ContactsRepository() {}

    // What would happen if I have 10^6 records? what would be the list size?
    // 1. Do you really need all this information in the memory?
    // 2. Are doing all these calculations on the main thread? (maybe its ok for the exercise..)
    // 3. I wounder if we can avoid loading ALL the information more than once?
    public List<Contact> getPhoneContacts(Context context) {
        ArrayList<Contact> contacts = new ArrayList<>();

        // Validate permissions to read contacts from phone
        if (!hasReadContactsPermission(context)) {
            // Ask for permissions, and return an empty contacts list
            // requestReadContactsPermission(activity);
            return contacts;
        }

        // Read contacts from phone (with permissions)
        // The safest way to handle resources is by using try-with-resource - use it!
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
        // try-with-resource - use it!
        Cursor email_cursor = context.getContentResolver().query(
                ContactsContract.CommonDataKinds.Email.CONTENT_URI,
                null,
                ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = ?",
                new String[]{contactId},
                null);
        if (email_cursor != null && email_cursor.moveToFirst()) {
            // Here for example: if exception is being raised then cursor is still open (try-with-resource).
            email = email_cursor.getString(email_cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Email.DATA));
            email_cursor.close();
        }
        return email;
    }
}
