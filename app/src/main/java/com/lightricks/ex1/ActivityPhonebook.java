package com.lightricks.ex1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.List;


public class ActivityPhonebook extends AppCompatActivity {
    private RecyclerView rvPhonebook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phonebook);
        bindViews();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Refresh the contacts list upon resume (may have changed)
        List<Contact> contacts = ContactsRepository.getInstance().getContacts(this);
        setViewsFromContacts(contacts);
    }

    private void setViewsFromContacts(List<Contact> contacts) {
        RecyclerViewAdapterPhonebook rvAdapterPhonebook = new RecyclerViewAdapterPhonebook(this, contacts);
        rvPhonebook.setAdapter(rvAdapterPhonebook);
        rvAdapterPhonebook.notifyDataSetChanged();
    }

    private void bindViews() {
        rvPhonebook = findViewById(R.id.rvPhonebook);
        rvPhonebook.setLayoutManager(new LinearLayoutManager(this));
    }
}