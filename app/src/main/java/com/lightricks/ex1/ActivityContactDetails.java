package com.lightricks.ex1;

import static com.lightricks.ex1.Common.glideSetImage;

import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityContactDetails extends AppCompatActivity {
    private TextView tvName, tvEmail, tvNumber;
    private ImageView ivImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);
        bindViews();
        setViewsFromIntent(getIntent());
    }

    /**
     * Returns an intent to start the ContactDetailsActivity for the given contact.
     *
     * @param intenderContext - Context from which the intent will be sent.
     * @param contact         - Contact for which to start a ContactDetailsActivity.
     * @return - an Intent that starts this activity for the given contact.
     */
    public static Intent getIntent(Context intenderContext, Contact contact) {
        Intent intent = new Intent(intenderContext, ActivityContactDetails.class);
        intent.putExtra("name", contact.getName());
        intent.putExtra("email", contact.getEmail());
        intent.putExtra("number", contact.getNumber());
        intent.putExtra("imageName", contact.getImageName());
        return intent;
    }

    private void setViewsFromIntent(Intent intent) {
        // Extract contact details from intent parameters
        String name = intent.getStringExtra("name");
        String email = intent.getStringExtra("email");
        String number = intent.getStringExtra("number");
        String imageName = intent.getStringExtra("imageName");

        tvName.setText(name);
        tvEmail.setText(email);
        tvNumber.setText(number);
        glideSetImage(this, ivImage, imageName);
    }

    private void bindViews() {
        // RKARL TODO: Validate Ids were found, handle exceptions
        tvName = findViewById(R.id.tvContactDetailsName);
        tvEmail = findViewById(R.id.tvContactDetailsEmail);
        tvNumber = findViewById(R.id.tvContactDetailsNumber);
        ivImage = findViewById(R.id.ivContactDetailsImage);
    }
}
