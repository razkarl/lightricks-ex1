package com.lightricks.ex1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;


public class HomeActivity extends AppCompatActivity {

    private ArrayList<ContactDetails> contactDetailsArrayList;
    private RecyclerView contactRV;
    private ContactRVAdapter contactRVAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);

        // on below line we are initializing our variables.
        contactDetailsArrayList = new ArrayList<>();

        ContactDetails andi = new ContactDetails("Andi Lightricksovitch",
                "android.devices@lightricks.com",
                "+972 525 111 111");
        contactDetailsArrayList.add(andi);

        ContactDetails raz = new ContactDetails("Raz Karl",
                "rkarl@lightricks.com",
                "+972 527 221 113");
        contactDetailsArrayList.add(raz);

        contactRV = findViewById(R.id.ContactsRV);

        // calling method to prepare our recycler view.
        prepareContactRV();
    }

    private void prepareContactRV() {
        // in this method we are preparing our recycler view with adapter.
        contactRVAdapter = new ContactRVAdapter(this, contactDetailsArrayList);
        // on below line we are setting layout manager.
        contactRV.setLayoutManager(new LinearLayoutManager(this));
        // on below line we are setting adapter to our recycler view.
        contactRV.setAdapter(contactRVAdapter);
        contactRVAdapter.notifyDataSetChanged();
    }
}