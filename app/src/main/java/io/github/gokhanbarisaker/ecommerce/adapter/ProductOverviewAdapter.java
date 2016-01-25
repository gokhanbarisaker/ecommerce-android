package io.github.gokhanbarisaker.ecommerce.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

import io.github.gokhanbarisaker.ecommerce.fragment.ProductOverviewFragment;
import io.github.gokhanbarisaker.ecommerce.model.Product;

/**
 * Created by gokhanbarisaker on 11/21/15.
 */
public class ProductOverviewAdapter extends FragmentStatePagerAdapter {

    // == Variables ================================================================================

    private List<Product> products;


    // == Constructor ==============================================================================

    public ProductOverviewAdapter(FragmentManager fm, List<Product> products) {
        super(fm);

        this.products = products;
    }


    // == Accessors ================================================================================

    @Override
    public Fragment getItem(int position) {
        return ProductOverviewFragment.newInstance(products.get(position));
    }

    @Override
    public int getCount() {
        return products.size();
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
