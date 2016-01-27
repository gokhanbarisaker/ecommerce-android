package io.github.gokhanbarisaker.ecommerce.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;

import com.gokhanbarisaker.osapis.utility.DeviceUtilities;

import io.github.gokhanbarisaker.ecommerce.Application;
import io.github.gokhanbarisaker.ecommerce.R;
import io.github.gokhanbarisaker.ecommerce.databinding.ActivityCatalogBinding;
import io.github.gokhanbarisaker.ecommerce.model.Product;
import io.github.gokhanbarisaker.ecommerce.network.CatalogService;
import io.github.gokhanbarisaker.ecommerce.utility.CatalogRepository;
import io.github.gokhanbarisaker.ecommerce.view.TransparentDividerItemDecoration;
import io.github.gokhanbarisaker.ecommerce.viewmodel.CatalogViewModel;

public class CatalogActivity extends AppCompatActivity implements CatalogViewModel.Listener {

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
        binding.recyclerview.setLayoutManager(new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false));
        int dividerThickness = Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1.f, DeviceUtilities.getCurrentDevice().getDisplay().getMetrics()));
        RecyclerView.ItemDecoration decoration = new TransparentDividerItemDecoration(dividerThickness, dividerThickness);
        binding.recyclerview.addItemDecoration(decoration);
        binding.recyclerview.setHasFixedSize(true);

        binding.setActivity(this);
        binding.setViewModel(new CatalogViewModel(new CatalogRepository(Application.retrofit.create(CatalogService.class)), this));
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
    public void onProductOverviewClicked(Product product, Object clickee) {
        View view = (View) clickee;
        Context context = view.getContext();
        Intent intent = new Intent(context, ProductActivity.class);
        intent.putExtra(ProductActivity.BUNDLE_PRODUCT, product);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            String transitionName, sharedElementName;
            transitionName = sharedElementName = context.getResources().getString(R.string.transition_product);
            ;

            view.setTransitionName(transitionName);
            //contextCompat???
            ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) context, view, sharedElementName);
            context.startActivity(intent, options.toBundle());
        } else {
            context.startActivity(intent);
        }
    }
}