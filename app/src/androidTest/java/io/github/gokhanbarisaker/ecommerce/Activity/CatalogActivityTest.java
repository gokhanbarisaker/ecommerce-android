package io.github.gokhanbarisaker.ecommerce.activity;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.github.gokhanbarisaker.ecommerce.R;
import io.github.gokhanbarisaker.ecommerce.utility.EspressoIdlingResource;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;


/**
 * Created by gokhanbarisaker on 11/21/15.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class CatalogActivityTest {
    // == Variables ================================================================================

    @Rule
    public ActivityTestRule<CatalogActivity> activityRule = new ActivityTestRule<>(CatalogActivity.class);


    // == Test cases ===============================================================================

    @Test
    public void testLoadSuccess() {
        onView(withId(R.id.viewpager)).check(matches(isDisplayed()));
        EspressoIdlingResource.lockFor(2000);
        onView(allOf(withId(R.id.button), isDisplayed()));
    }

    @Test
    public void testLoadError() {
        Assert.assertTrue("Implement that", false);
    }

//    @Test
//    public void testProductOverviewClick() {
//        EspressoIdlingResource.lockFor(2000);
//        onView(allOf(withId(R.id.button), isDisplayed())).perform(click());
//
//        Product product = CatalogRepository.catalog.getItems().get(0);
//        onView(withId(R.id.name_textview)).check(matches(withText(product.getName())));
//    }
}
