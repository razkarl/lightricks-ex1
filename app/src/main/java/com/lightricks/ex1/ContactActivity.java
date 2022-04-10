package com.lightricks.ex1;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;

public class ContactActivity extends AppCompatActivity {
    private String contactName, contactEmail, contactNumber;
    private TextView nameTV, emailTV, numberTV;
    private ImageView imageIV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_details_activity);

        contactName = getIntent().getStringExtra("name");
        contactEmail = getIntent().getStringExtra("email");
        contactNumber = getIntent().getStringExtra("number");

        // initializing our views.
        nameTV = findViewById(R.id.NameTV);
        emailTV = findViewById(R.id.EmailTV);
        numberTV = findViewById(R.id.NumberTV);
        nameTV.setText(contactName);
        emailTV.setText(contactEmail);
        numberTV.setText(contactNumber);

        // Attempt to initialize the image
        String imageName = contactName.replace(' ', '_');
        int hasImage = getResources().getIdentifier(imageName, "drawable", getPackageName());
        // RKARL TODO: Fetch matching image, or use a default 'No image found' image.
    }
}
