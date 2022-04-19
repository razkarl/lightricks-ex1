package com.lightricks.ex1;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class ContactDetailsFragmentTest {
    /**
     * When a user clicks on a contact item
     * The matching details show up
     */
    @Before
    public void setup(){
        ActivityScenario.launch(MainActivity.class);
    }

    @Test
    public void contactItemClicked_MatchingDetailsShowUp(){
        Espresso.onView(withText("Raz Karl")).perform(click());
        Espresso.onView(withId(R.id.tvContactDetailsName)).check(matches(withText("Raz Karl")));
    }
}
