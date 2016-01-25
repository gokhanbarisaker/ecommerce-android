package io.github.gokhanbarisaker.ecommerce;

import android.databinding.BindingAdapter;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.graphics.Palette;
import android.util.Log;
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.viewpagerindicator.PageIndicator;

import java.util.List;

import io.github.gokhanbarisaker.ecommerce.adapter.ProductOverviewAdapter;
import io.github.gokhanbarisaker.ecommerce.model.Product;

/**
 * Created by gokhanbarisaker on 12/13/15.
 */
public class Binders {
    @BindingAdapter({"bind:productOverviewItems", "bind:pageIndicator"})
    public static void setProductOverviewItems(ViewPager viewPager, List<Product> products, PageIndicator pageIndicator) {

        ProductOverviewAdapter productAdapter = (ProductOverviewAdapter) viewPager.getAdapter();

        if (productAdapter == null) {
            FragmentActivity activity = (FragmentActivity) viewPager.getContext();
            FragmentManager fragmentManager = activity.getSupportFragmentManager();
            productAdapter = new ProductOverviewAdapter(fragmentManager, products);
            viewPager.setAdapter(productAdapter);
        } else {
            // TODO: Check (with different dataset, possibly by randomizing given input) if this is going to create (or not) fragment artifacts due to outsmarting fragment performance optimizations of android framework
            productAdapter.setProducts(products);
            productAdapter.notifyDataSetChanged();
        }

        if (pageIndicator != null) {
            pageIndicator.setViewPager(viewPager);
        }
    }

    // TODO: Add placeholder support
    // TODO: Alternative source support
    @BindingAdapter({"app:imageUrl", "app:paletteAsyncListener"})
    public static void setImageUrl(final ImageView imageView, String url, final Palette.PaletteAsyncListener paletteAsyncListener) {
        Log.e("Image url", "has been called with " + url);
        Application.picasso.load(url).into(imageView, new Callback() {
            @Override
            public void onSuccess() {
                BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
                Palette.from(drawable.getBitmap()).generate(paletteAsyncListener);
            }

            @Override
            public void onError() {
            }
        });
    }

    // TODO: Add placeholder support
    // TODO: Alternative source support
    @BindingAdapter({"app:imageUrl"})
    public static void setImageUrl(final ImageView imageView, String url) {
        Log.d("Image url", "has been called with " + url);
        Application.picasso.load(url).into(imageView);
    }
}
