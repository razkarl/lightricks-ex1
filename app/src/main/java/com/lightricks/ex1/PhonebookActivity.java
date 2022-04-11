package com.lightricks.ex1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;

import java.util.ArrayList;
import java.util.List;


public class PhonebookActivity extends AppCompatActivity {
    private RecyclerView phonebookRecyclerView;
    private PhonebookRecyclerViewAdapter phonebookRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phonebook);

        phonebookRecyclerView = findViewById(R.id.rvPhonebook);
        phonebookRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        checkPermissions();

        List<Contact> contacts = ContactsRepository.getInstance().getContacts(this);
        phonebookRecyclerViewAdapter = new PhonebookRecyclerViewAdapter(this, contacts);
        phonebookRecyclerView.setAdapter(phonebookRecyclerViewAdapter);
        phonebookRecyclerViewAdapter.notifyDataSetChanged();
    }

    private void checkPermissions() {
        // Request permissions if not granted yet
        if (ContextCompat.checkSelfPermission(PhonebookActivity.this,
                Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(PhonebookActivity.this,
                    new String[]{Manifest.permission.READ_CONTACTS}, 100);
        } else {
            getContactsList();
        }
    }

    private void getContactsList() {
        ArrayList<Contact> contacts = new ArrayList<Contact>();

        // Initialize unique resource identifier
        Uri contentUri = ContactsContract.Contacts.CONTENT_URI;
        // Sort contacts by ascending order
        String sortOrder = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC";
        // Initialize cursor
        Cursor cursor = getContentResolver().query(contentUri, null, null, null, sortOrder);
        if (cursor.getCount() > 0){
            // When count is greater than 0, use a while loop
            while (cursor.moveToNext()){
                // Get contact id
                String id = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.Contacts._ID));
                String name = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME));
                Uri uriPhone = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
                String selection = ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " =?";
                Cursor phoneCursor = getContentResolver().query(
                        uriPhone, null, selection, new String[]{id}, null);
                if (phoneCursor.moveToNext()){
                    String number = phoneCursor.getString(phoneCursor.getColumnIndexOrThrow(
                            ContactsContract.CommonDataKinds.Phone.NUMBER));
                    String email = phoneCursor.getString(phoneCursor.getColumnIndexOrThrow(
                            ContactsContract.CommonDataKinds.Email.DATA));
                    Contact contact = new Contact(name,email,number);
                    contacts.add(contact);
                    phoneCursor.close();
                }
            }
            cursor.close();
        }
    }
}