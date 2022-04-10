package com.lightricks.ex1;

import android.content.Context;

import java.util.Locale;

public class Contact {
    private String name;
    private String email;
    private String number;

    public Contact(String name,
                   String email,
                   String number) {
        this.name = name;
        this.email = email;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public String getEmail() {
        return email;
    }

    public int getImageId(Context context) {
        String imageName = name.replace(' ', '_').toLowerCase(Locale.ROOT);
        int imageId = context.getResources().getIdentifier(imageName, "raw", context.getPackageName());
        if (0 == imageId) { // No such resource found
            // RKARL TODO: Handle errors (what happens if the "default" image resource does not exist?)
            // RKARL TODO: Cache the default image instead of fetching it every time
            imageId = context.getResources().getIdentifier("default_image", "raw", context.getPackageName());
        }
        return imageId;
    }
}
