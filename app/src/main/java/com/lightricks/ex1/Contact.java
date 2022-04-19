package com.lightricks.ex1;

import java.util.Locale;

public class Contact {
    private final String name;
    private final String email;
    private final String number;
    private final String imageName;

    public Contact(String name,
                   String email,
                   String number) {
        this.name = name;
        this.email = email;
        this.number = number;

        // Constant.. No need to calculate twice..
        this.imageName = name.replace(' ', '_').toLowerCase(Locale.ROOT);
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

    /**
     * Image name is induced from the contact name, as follows:
     * name      = "Raz Karl"
     * imageName = "raz_karl"
     * Contact images are saved in src/main/res/raw/<contact_name>.<jpg|jpeg|png|...>
     * @return the name of the image resource that corresponds to this contact.
     */
    public String getImagePath() {
        return imageName;
    }
}
