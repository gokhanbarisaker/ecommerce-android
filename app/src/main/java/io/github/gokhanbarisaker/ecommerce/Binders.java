package io.github.gokhanbarisaker.ecommerce;

import android.databinding.BindingAdapter;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;

import com.squareup.picasso.Callback;

import java.util.List;

import io.github.gokhanbarisaker.ecommerce.adapter.ProductOverviewAdapter;
import io.github.gokhanbarisaker.ecommerce.model.Product;

/**
 * Created by gokhanbarisaker on 12/13/15.
 */
public class Binders {
    @BindingAdapter({"bind:productOverviewItems"})
    public static void setProductOverviewItems(RecyclerView recyclerView, List<Product> products) {
        setProductOverviewItems(recyclerView, products, null);
    }

    @BindingAdapter({"bind:productOverviewItems", "bind:productOverviewListener"})
    public static void setProductOverviewItems(RecyclerView recyclerView, List<Product> products, ProductOverviewAdapter.Listener listener) {

        ProductOverviewAdapter productOverviewAdapter = (ProductOverviewAdapter) recyclerView.getAdapter();

        if (productOverviewAdapter == null) {
            productOverviewAdapter = new ProductOverviewAdapter(products, listener);
            recyclerView.setAdapter(productOverviewAdapter);
        } else {
            productOverviewAdapter.setProducts(products);
            productOverviewAdapter.notifyDataSetChanged();
        }
    }

    // TODO: Add placeholder support
    // TODO: Alternative source support
    @BindingAdapter({"bind:imageUrl", "bind:paletteAsyncListener"})
    public static void setImageUrl(final ImageView imageView, String url, final Palette.PaletteAsyncListener paletteAsyncListener) {
        Log.e("Image url", "has been called with " + url);
        Application.picasso.load(url).config(Bitmap.Config.RGB_565).noFade().into(imageView, new Callback() {
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
    @BindingAdapter({"bind:imageUrl"})
    public static void setImageUrl(final ImageView imageView, String url) {
        Log.d("Image url", "has been called with " + url);
        Application.picasso.load(url).config(Bitmap.Config.RGB_565).noFade().into(imageView);
    }
}
