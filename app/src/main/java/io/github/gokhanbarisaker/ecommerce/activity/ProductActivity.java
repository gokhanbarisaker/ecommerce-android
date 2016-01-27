package io.github.gokhanbarisaker.ecommerce.activity;

import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import io.github.gokhanbarisaker.ecommerce.R;
import io.github.gokhanbarisaker.ecommerce.databinding.ActivityProductBinding;
import io.github.gokhanbarisaker.ecommerce.model.Product;
import io.github.gokhanbarisaker.ecommerce.viewmodel.ProductViewModel;

public class ProductActivity extends AppCompatActivity {

    // == Variables ================================================================================

    public static final String BUNDLE_PRODUCT = "product";

    private ActivityProductBinding binding;


    // == Activity lifecycle callbacks =============================================================

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_product);

        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            binding.imageview.setTransitionName(getResources().getString(R.string.transition_product));
        }

        Product product = getIntent().getParcelableExtra(BUNDLE_PRODUCT);
        binding.setViewModel(new ProductViewModel(product));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean handled = false;

        switch (item.getItemId()) {
            case android.R.id.home: {
                finish();
                handled = true;

                break;
            }
            default: {
                handled = super.onOptionsItemSelected(item);
                break;
            }
        }

        return handled;
    }


    // == Tools ====================================================================================

//    private void loadPalette () {
//        // Asynchronous
//        BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
//        Palette.from(drawable.getBitmap()).generate(new Palette.PaletteAsyncListener() {
//            public void onGenerated(Palette p) {
//                // Use generated instance
//                int muted = p.getMutedColor(getResources().getColor(R.color.muted));
//                int mutedLight = p.getLightMutedColor(getResources().getColor(R.color.muted_light));
//                int mutedDark = p.getDarkMutedColor(getResources().getColor(R.color.muted_dark));
//                int vibrant = p.getVibrantColor(getResources().getColor(R.color.vibrant));
//                int vibrantLight = p.getVibrantColor(getResources().getColor(R.color.vibrant_light));
//
//                toolbar.setBackgroundColor(muted);
//                toolbar.setTitleTextColor(vibrantLight);
//
//                nameTextView.setBackgroundColor(mutedLight);
//                nameTextView.setTextColor(mutedDark);
//                priceCurrentTextView.setBackgroundColor(mutedLight);
//                priceCurrentTextView.setTextColor(mutedDark);
//                priceOriginalTextView.setBackgroundColor(mutedLight);
//                priceOriginalTextView.setTextColor(vibrant);
//                brandTextView.setBackgroundColor(mutedLight);
//                brandTextView.setTextColor(mutedDark);
//            }
//        });
//    }
}
