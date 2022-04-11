package com.lightricks.ex1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.List;


public class PhonebookActivity extends AppCompatActivity {
    private RecyclerView phonebookRecyclerView;
    private PhonebookRecyclerViewAdapter phonebookRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phonebook);
        bindViews();
    }

    @Override
    protected void onResume() {
        super.onResume();
        List<Contact> contacts = ContactsRepository.getInstance().getContacts(this);
        setViewsFromContacts(contacts);
    }

    private void setViewsFromContacts(List<Contact> contacts) {
        phonebookRecyclerViewAdapter = new PhonebookRecyclerViewAdapter(this, contacts);
        phonebookRecyclerView.setAdapter(phonebookRecyclerViewAdapter);
        phonebookRecyclerViewAdapter.notifyDataSetChanged();
    }

    private void bindViews() {
        phonebookRecyclerView = findViewById(R.id.rvPhonebook);
        phonebookRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}