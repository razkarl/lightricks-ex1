package com.lightricks.ex1;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ContactRVAdapter extends RecyclerView.Adapter<ContactRVAdapter.ViewHolder> {
    // creating variables for context and array list.
    private Context context;
    private ArrayList<Contact> contactArrayList;

    public ContactRVAdapter(Context context, ArrayList<Contact> contactsModalArrayList) {
        this.context = context;
        this.contactArrayList = contactsModalArrayList;
    }

    @NonNull
    @Override
    public ContactRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // passing our layout file for displaying our card item
        return new ContactRVAdapter.ViewHolder(LayoutInflater.from(context).inflate(R.layout.contacts_rv_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // getting data from array list in our contact details.
        Contact contact = contactArrayList.get(position);

        // on below line we are setting data to our text view.
        holder.contactTV.setText(contact.getName());

        // on below line we are adding on click listener to our item of recycler view.
        holder.itemView.setOnClickListener(v -> {
            // on below line we are opening a new activity and passing data to it.
            Intent i = new Intent(context, ContactDetailsActivity.class);
            i.putExtra("name", contact.getName());
            i.putExtra("email", contact.getEmail());
            i.putExtra("number", contact.getNumber());
            // on below line we are starting a new activity,
            context.startActivity(i);
        });
    }

    @Override
    public int getItemCount() {
        return contactArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // on below line creating a variable
        // for our image view and text view.
        private ImageView contactIV;
        private final TextView contactTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing our image view and text view.
            contactIV = itemView.findViewById(R.id.idIVContact);
            contactTV = itemView.findViewById(R.id.idTVContactName);
        }
    }
}
