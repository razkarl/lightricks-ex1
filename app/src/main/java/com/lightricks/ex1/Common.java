package com.lightricks.ex1;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.widget.ImageView;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;

public class Common {

    private static int defaultImageId = -1;

    public static void requestReadContactsPermission(Activity activity) {
        ActivityCompat.requestPermissions(activity,
                new String[]{Manifest.permission.READ_CONTACTS}, 100);
    }

    public static boolean hasReadContactsPermission(Context context) {
        return ContextCompat.checkSelfPermission(context,
                Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED;
    }

    /**
     * Sets the image given by imageName to the view given by ivImage.
     *
     * @param context   - Context from which to take resources.
     * @param ivImage   - ImageView to display the given image.
     * @param imageName - Name of the image file inside the res/raw directory.
     */
    public static void glideSetImage(Context context, ImageView ivImage, String imageName) {
        int imageId = getImageId(context, imageName);
        if (imageId == 0){
            // Init defultImageId for the first time
            if (defaultImageId == -1){
                defaultImageId = getImageId(context, "default_image");
            }
            // Validate defaultImageId
            if (defaultImageId == 0){
                // Oh no! "default_image.jpeg" is missing!
                return;
            }
            imageId = defaultImageId;
        }
        Glide.with(context)
                .load(imageId)
                .into(ivImage);
    }

    private static int getImageId(Context context, String imageName) {
        return context.getResources().getIdentifier(imageName, "raw", BuildConfig.APPLICATION_ID);
    }
}
