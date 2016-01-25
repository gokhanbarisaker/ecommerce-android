package io.github.gokhanbarisaker.ecommerce.activity;

import android.content.Intent;
import android.content.res.Resources;
import android.support.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Test;

import io.github.gokhanbarisaker.ecommerce.R;
import io.github.gokhanbarisaker.ecommerce.model.Price;
import io.github.gokhanbarisaker.ecommerce.model.Product;
import io.github.gokhanbarisaker.ecommerce.utility.PriceFormatter;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by gokhanbarisaker on 1/24/16.
 */
public class ProductActivityTest {
    // == Variables ================================================================================

    private ActivityTestRule<ProductActivity> activityRule = new ActivityTestRule<>(ProductActivity.class, true, false);
    private Product product;
    private PriceFormatter formatter;

    @Before
    public void before() {
        product = Product.random();
        formatter = new PriceFormatter();
    }

    @Test
    public void testName() {
        Intent intent = new Intent();
        intent.putExtra(ProductActivity.BUNDLE_PRODUCT, product);
        activityRule.launchActivity(intent);

        onView(withId(R.id.name_textview))
                .check(matches(isDisplayed()))
                .check(matches(withText(product.getName())));
    }

    @Test
    public void testPriceCurrent() {
        Intent intent = new Intent();
        intent.putExtra(ProductActivity.BUNDLE_PRODUCT, product);
        activityRule.launchActivity(intent);

        Price price = product.getPrice();
        Resources resources = activityRule.getActivity().getResources();
        String priceText = resources.getString(R.string.label_price, formatter.apply(price.getCurrent()), price.getCurrency());

        onView(withId(R.id.price_current_textview))
                .check(matches(isDisplayed()))
                .check(matches(withText(priceText)));
    }

    @Test
    public void testPriceOriginal() {
        Intent intent = new Intent();
        intent.putExtra(ProductActivity.BUNDLE_PRODUCT, product);
        activityRule.launchActivity(intent);

        Price price = product.getPrice();
        Resources resources = activityRule.getActivity().getResources();
        String priceText = resources.getString(R.string.label_price, formatter.apply(price.getOriginal()), price.getCurrency());

        onView(withId(R.id.price_original_textview))
                .check(matches(isDisplayed()))
                .check(matches(withText(priceText)));
    }

    @Test
    public void testBrand() {
        Intent intent = new Intent();
        intent.putExtra(ProductActivity.BUNDLE_PRODUCT, product);
        activityRule.launchActivity(intent);

        onView(withId(R.id.brand_textview))
                .check(matches(isDisplayed()))
                .check(matches(withText(product.getBrand())));
    }
}
