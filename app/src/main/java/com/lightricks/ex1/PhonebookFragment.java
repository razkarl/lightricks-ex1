package com.lightricks.ex1;

import static com.lightricks.ex1.Common.hasReadContactsPermission;
import static com.lightricks.ex1.Common.requestReadContactsPermission;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class PhonebookFragment extends Fragment {

    private PhonebookAdapter adapter;
    private ContactsViewModel viewModel;

    public PhonebookFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        validateReadContactPermissions();

        // Initialize recycler view adapter
        adapter = new PhonebookAdapter();

        // Create the phonebook ViewModel
        viewModel = new ViewModelProvider(getActivity()).get(ContactsViewModel.class);
    }

    @Override
    public void onResume() {
        super.onResume();
        validateReadContactPermissions();
        viewModel.loadPhoneContacts(getContext());
    }

    private void validateReadContactPermissions() {
        if (!hasReadContactsPermission(getContext())) {
            requestReadContactsPermission(getActivity());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragment = inflater.inflate(R.layout.fragment_phonebook, container, false);

        // Initialize phonebook recycler view
        RecyclerView recyclerView = fragment.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        // Observe phone contacts and update the adapter upon changes
        viewModel.getLiveContacts().observe(getViewLifecycleOwner(), adapter::setContacts);

        return fragment;
    }
}