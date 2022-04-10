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

import java.util.List;

public class ContactRVAdapter extends RecyclerView.Adapter<ContactRVAdapter.ViewHolder> {
    private Context context;
    private List<Contact> contactList;

    public ContactRVAdapter(Context context, List<Contact> contactList) {
        this.context = context;
        this.contactList = contactList;
    }

    @NonNull
    @Override
    public ContactRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // passing our layout file for displaying our card item
        return new ContactRVAdapter.ViewHolder(LayoutInflater.from(context).inflate(R.layout.contacts_rv_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Contact contact = contactList.get(position);

        // Set view items content
        holder.nameTV.setText(contact.getName());
        holder.imageIV.setImageResource(contact.getImageId(context));

        // Set view items behaviour
        holder.itemView.setOnClickListener(v -> {
            Intent i = new Intent(context, ContactDetailsActivity.class);
            i.putExtra("name", contact.getName());
            i.putExtra("email", contact.getEmail());
            i.putExtra("number", contact.getNumber());
            i.putExtra("imageId", contact.getImageId(context));
            context.startActivity(i);
        });
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageIV;
        private final TextView nameTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing our image view and text view.
            imageIV = itemView.findViewById(R.id.ContactsRVItem_ImageIV);
            nameTV = itemView.findViewById(R.id.ContactsRVItem_NameTV);
        }
    }
}
