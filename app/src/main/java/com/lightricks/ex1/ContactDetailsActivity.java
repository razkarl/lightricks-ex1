package com.lightricks.ex1;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ContactDetailsActivity extends AppCompatActivity {
    private String contactName, contactEmail, contactNumber;
    private int imageId;
    private TextView nameTV, emailTV, numberTV;
    private ImageView imageIV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_details_activity);

        contactName = getIntent().getStringExtra("name");
        contactEmail = getIntent().getStringExtra("email");
        contactNumber = getIntent().getStringExtra("number");
        imageId = getIntent().getIntExtra("imageId", -1); // RKARL TODO: When is defaultValue used?

        // RKARL TODO: Validate Ids were found, handle exceptions
        nameTV = findViewById(R.id.ContactDetailsActivity_NameTV);
        emailTV = findViewById(R.id.ContactDetailsActivity_EmailTV);
        numberTV = findViewById(R.id.ContactDetailsActivity_NumberTV);
        imageIV = findViewById(R.id.ContactDetailsActivity_ImageIV);

        nameTV.setText(contactName);
        emailTV.setText(contactEmail);
        numberTV.setText(contactNumber);
        imageIV.setImageResource(imageId);  // RKARL TODO: Fetch matching image, or use a default 'No image found' image.
    }
}
