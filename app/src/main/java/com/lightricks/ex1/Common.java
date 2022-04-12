package com.lightricks.ex1;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class Common {
    /**
     * Sets the image given by imageName to the view given by ivImage.
     *
     * @param context   - Context from which to take resources.
     * @param ivImage   - ImageView to display the given image.
     * @param imageName - Name of the image file inside the res/raw directory.
     */
    public static void glideSetImage(Context context, ImageView ivImage, String imageName) {
        int imageId = getImageId(context, imageName);
        int defaultImageId = getImageId(context, "default_image");
        Glide.with(context)
                .load(imageId)
                .placeholder(defaultImageId)
                .into(ivImage);
    }

    private static int getImageId(Context context, String imageName) {
        return context.getResources().getIdentifier(imageName, "raw", BuildConfig.APPLICATION_ID);
    }
}
