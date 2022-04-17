package com.lightricks.ex1;

import static com.lightricks.ex1.Common.glideSetImage;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ContactDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContactDetailsFragment extends Fragment {

    private ContactsViewModel viewModel;
    private Contact contact;

    public ContactDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(getActivity()).get(ContactsViewModel.class);
        ContactDetailsFragmentArgs args = ContactDetailsFragmentArgs.fromBundle(getArguments());
        int position = args.getContactPosition();
        contact = Objects.requireNonNull(viewModel.getLiveContacts().getValue()).get(position);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_contact_details, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView name = view.findViewById(R.id.tvContactDetailsName);
        TextView email = view.findViewById(R.id.tvContactDetailsEmail);
        TextView number = view.findViewById(R.id.tvContactDetailsNumber);
        ImageView image = view.findViewById(R.id.ivContactDetailsImage);

        name.setText(contact.getName());
        email.setText(contact.getEmail());
        number.setText(contact.getNumber());
        glideSetImage(getContext(), image, contact.getImageName());
    }
}