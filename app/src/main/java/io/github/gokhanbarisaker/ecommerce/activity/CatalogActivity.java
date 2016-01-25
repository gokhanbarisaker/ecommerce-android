package io.github.gokhanbarisaker.ecommerce.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import io.github.gokhanbarisaker.ecommerce.Application;
import io.github.gokhanbarisaker.ecommerce.R;
import io.github.gokhanbarisaker.ecommerce.databinding.ActivityCatalogBinding;
import io.github.gokhanbarisaker.ecommerce.fragment.ProductOverviewFragment;
import io.github.gokhanbarisaker.ecommerce.model.Product;
import io.github.gokhanbarisaker.ecommerce.network.CatalogService;
import io.github.gokhanbarisaker.ecommerce.utility.CatalogRepository;
import io.github.gokhanbarisaker.ecommerce.viewmodel.CatalogViewModel;

public class CatalogActivity extends AppCompatActivity implements ProductOverviewFragment.OnProductOverviewInteractionListener {

    // == Variables ================================================================================

    private ActivityCatalogBinding binding;


    // == Activity lifecycle callbacks =============================================================

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // TODO: Bind listeners from DB
        // TODO: Set initial values from databinding
        // TODO: Restore state
        //
        binding = DataBindingUtil.setContentView(this, R.layout.activity_catalog);

//        if (savedInstanceState != null) {
//            products = savedInstanceState.getParcelableArrayList(BUNDLE_PRODUCTS);
//        }

        binding.progressview.setVisibility(View.GONE);

        binding.setActivity(this);
        binding.setViewModel(new CatalogViewModel(new CatalogRepository(Application.retrofit.create(CatalogService.class))));
        binding.getViewModel().loadProducts(false);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

//        binding.getViewModel().onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        binding.getViewModel().clean();
    }


    // == External callbacks =======================================================================

    @Override
    public void onProductOverviewClick(Product product) {
        Intent intent = new Intent(this, ProductActivity.class);
        intent.putExtra(ProductActivity.BUNDLE_PRODUCT, product);

        startActivity(intent);
    }
}