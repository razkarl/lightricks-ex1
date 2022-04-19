package com.lightricks.ex1;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class ContactsViewModel extends ViewModel {

    private final MutableLiveData<List<Contact>> contacts;

    public LiveData<List<Contact>> getLiveContacts() {
        return contacts;
    }

    public ContactsViewModel() {
        contacts = new MutableLiveData<>();
    }

    public void loadPhoneContacts(Context context){
        contacts.setValue(ContactsRepository.getInstance().getPhoneContacts(context));
    }
}
