package com.lightricks.ex1;

import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

public class ContactDetailsActivity extends AppCompatActivity {
    private TextView tvName, tvEmail, tvNumber;
    private ImageView ivImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);
        findActivityViews();
        setActivityViewsFromIntent(getIntent());
    }

    /**
     * Returns an intent to start the ContactDetailsActivity for the given contact.
     * @param intenderContext - Context from which the intent will be sent.
     * @param contact - Contact for which to start a ContactDetailsActivity.
     * @return - an Intent that starts this activity for the given contact.
     */
    public static Intent getIntent(Context intenderContext, Contact contact){
        Intent intent = new Intent(intenderContext, ContactDetailsActivity.class);
        intent.putExtra("name", contact.getName());
        intent.putExtra("email", contact.getEmail());
        intent.putExtra("number", contact.getNumber());
        intent.putExtra("imageId", contact.getImageId(intenderContext));
        return intent;
    }

    private void findActivityViews() {
        // RKARL TODO: Validate Ids were found, handle exceptions
        tvName = findViewById(R.id.tvContactDetailsEmail);
        tvEmail = findViewById(R.id.tvContactDetailsNumber);
        tvNumber = findViewById(R.id.tvContactDetailsName);
        ivImage = findViewById(R.id.ivContactDetailsImage);
    }

    private void setActivityViewsFromIntent(Intent intent) {
        // Extract contact details from intent parameters
        String name = intent.getStringExtra("name");
        String email = intent.getStringExtra("email");
        String number = intent.getStringExtra("number");
        int imageId = intent.getIntExtra("imageId", -1); // RKARL TODO: When is defaultValue used?

        tvName.setText(name);
        tvEmail.setText(email);
        tvNumber.setText(number);
        ivImage.setImageResource(imageId);  // RKARL TODO: Fetch matching image, or use a default 'No image found' image.
    }
}
