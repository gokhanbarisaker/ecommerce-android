package io.github.gokhanbarisaker.ecommerce.viewmodel;

import android.content.res.Resources;
import android.support.v7.graphics.Palette;
import android.util.Log;

import io.github.gokhanbarisaker.ecommerce.Application;
import io.github.gokhanbarisaker.ecommerce.R;
import io.github.gokhanbarisaker.ecommerce.model.Price;
import io.github.gokhanbarisaker.ecommerce.model.Product;
import io.github.gokhanbarisaker.ecommerce.utility.PriceFormatter;

/**
 * Created by gokhanbarisaker on 1/20/16.
 */
public class ProductViewModel implements Palette.PaletteAsyncListener {
    public final String name;
    public final String brand;
    public final String imageUrl;
    public final String priceCurrent;
    public final String priceOriginal;
    private final Product product;
    private final PriceFormatter formatter = new PriceFormatter();

    public ProductViewModel(Product product) {
        this.product = product;

        this.name = product.getName();
        this.brand = product.getBrand();

        int imageUrlStartIndex = product.getImage().lastIndexOf("http");
        this.imageUrl = product.getImage().substring(imageUrlStartIndex, product.getImage().length());

        Resources resources = Application.context.getResources();
        Price price = product.getPrice();
        final String priceAmountCurrent = formatter.apply(price.getCurrent());
        final String priceAmountOriginal = formatter.apply(price.getOriginal());
        this.priceCurrent = resources.getString(R.string.label_price, priceAmountCurrent, price.getCurrency());
        this.priceOriginal = resources.getString(R.string.label_price, priceAmountOriginal, price.getCurrency());
    }

    @Override
    public void onGenerated(Palette palette) {
        Log.e("TAGFOO", "Generated: " + palette);

        // TODO: Notify registered observers that palette is updated
    }
}
