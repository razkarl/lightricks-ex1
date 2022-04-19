package com.lightricks.ex1;

import static com.lightricks.ex1.Common.glideSetImage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class PhonebookAdapter extends RecyclerView.Adapter<PhonebookAdapter.ContactItemViewsHolder> {

    public static class ContactItemViewsHolder extends RecyclerView.ViewHolder {
        private TextView tvName;
        private ImageView ivImage;

        public ContactItemViewsHolder(@NonNull View itemView) {
            super(itemView);
            bindViews(itemView);
        }

        private void bindViews(@NonNull View itemView) {
            // Fetch views from item_contact.xml
            tvName = itemView.findViewById(R.id.tvContactName);
            ivImage = itemView.findViewById(R.id.ivContactImage);
        }
    }

    private List<Contact> contacts = new ArrayList<>();
    private Context context;

    @NonNull
    @Override
    public ContactItemViewsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View contactItemViews = LayoutInflater.from(context)
                .inflate(R.layout.item_contact, parent, false);
        return new ContactItemViewsHolder(contactItemViews);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactItemViewsHolder holder, int position) {
        // Fetch the contact at the given position
        Contact currentContact = contacts.get(position);

        // Update the contact item's views
        setContactItemViewHolderFromContact(holder, currentContact);

        // When the contact item is clicked - navigate to the the contact's contactDetailsFragment
        holder.itemView.setOnClickListener(view -> {
            NavController navController = Navigation.findNavController(view);
            PhonebookFragmentDirections.ActionPhonebookFragmentToContactDetailsFragment action = PhonebookFragmentDirections.actionPhonebookFragmentToContactDetailsFragment(position);
            navController.navigate(action);
        });
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public void setContacts(List<Contact> contacts){
        this.contacts = contacts;
        notifyDataSetChanged();
    }

    private void setContactItemViewHolderFromContact(@NonNull ContactItemViewsHolder holder, Contact contact) {
        // Display contact in PhonebookActivity
        holder.tvName.setText(contact.getName());
        glideSetImage(context, holder.ivImage, contact.getImagePath());
    }
}
