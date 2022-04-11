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

public class PhonebookRecyclerViewAdapter extends RecyclerView.Adapter<PhonebookRecyclerViewAdapter.ViewHolder> {
    private final Context context;
    private final List<Contact> contacts;

    public PhonebookRecyclerViewAdapter(Context context, List<Contact> contacts) {
        this.context = context;
        this.contacts = contacts;
    }

    @NonNull
    @Override
    public PhonebookRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_contact, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewsHolder, int position) {
        // Fetch the contact at the given position
        Contact contact = contacts.get(position);

        // Update the contact item's views
        setViewsFromContact(viewsHolder, contact);

        // When the contact item is clicked - start the contact's contactDetailsActivity
        viewsHolder.itemView.setOnClickListener(v -> startContactDetailsActivity(contact));
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    private void startContactDetailsActivity(Contact contact) {
        Intent contactDetailsActivityIntent = ContactDetailsActivity.getIntent(context, contact);
        context.startActivity(contactDetailsActivityIntent);
    }

    private void setViewsFromContact(@NonNull ViewHolder holder, Contact contact) {
        // Display contact in PhonebookActivity
        holder.tvName.setText(contact.getName());
        holder.ivImage.setImageResource(contact.getImageId(context));
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvName;
        private ImageView ivImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            findViews(itemView);
        }

        private void findViews(@NonNull View itemView) {
            // Fetch views from activity_phonebook.xml
            tvName = itemView.findViewById(R.id.tvItemContactName);
            ivImage = itemView.findViewById(R.id.ivItemContactImage);
        }
    }
}
