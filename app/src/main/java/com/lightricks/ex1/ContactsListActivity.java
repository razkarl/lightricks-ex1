package com.lightricks.ex1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.List;


public class ContactsListActivity extends AppCompatActivity {

    private RecyclerView contactRV;
    private ContactRVAdapter contactRVAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contacts_list_activity);

        List<Contact> contacts = ContactRepository.getInstance().getContacts();
        contactRVAdapter = new ContactRVAdapter(this, contacts);
        contactRV = findViewById(R.id.ContactsListActivity_ContactsRV);
        contactRV.setLayoutManager(new LinearLayoutManager(this));
        contactRV.setAdapter(contactRVAdapter);
        contactRVAdapter.notifyDataSetChanged();
    }
}