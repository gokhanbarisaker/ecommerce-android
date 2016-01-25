package io.github.gokhanbarisaker.ecommerce.viewmodel;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;

import java.util.List;

import io.github.gokhanbarisaker.ecommerce.model.Catalog;
import io.github.gokhanbarisaker.ecommerce.model.Cleanable;
import io.github.gokhanbarisaker.ecommerce.model.Product;
import io.github.gokhanbarisaker.ecommerce.utility.CatalogRepository;
import io.github.gokhanbarisaker.ecommerce.utility.EspressoIdlingResource;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;

/**
 * Created by gokhanbarisaker on 12/11/15.
 */
public class CatalogViewModel implements Cleanable {
    // == Variables ================================================================================

    private static final String BUNDLE_PRODUCTS = "main.products";

    public ObservableArrayList<Product> products = new ObservableArrayList<>();
    public ObservableField<Status> status = new ObservableField<>(Status.MINT_FRESH);
    private Subscription productSubscription;
    private CatalogRepository repository;


    // == Constructors =============================================================================

    public CatalogViewModel(CatalogRepository repository) {
        this.repository = repository;
    }


    // == Callbacks ================================================================================

    public void onRefresh() {
        loadProducts(true);
    }


    // == Tools ====================================================================================

    public void loadProducts(final boolean refresh) {
        // If there already is ongoing load attempt
        if (productSubscription != null && !productSubscription.isUnsubscribed()) {
            return;
        }

        // App is busy until further notice
        EspressoIdlingResource.increment();

        // TODO: Optimize this with in-memory cache of products
        Observable<Catalog> observable = repository.fetchProducts();

        productSubscription = observable.subscribe(new Subscriber<Catalog>() {
            @Override
            public void onStart() {
                setStatus(refresh ? Status.REFRESHING : Status.LOADING);
            }

            @Override
            public void onNext(Catalog catalog) {
                setProducts(catalog.getItems());
            }

            @Override
            public void onCompleted() {
                setStatus(Status.LOADED);

                EspressoIdlingResource.decrement(); // Set app as idle
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                setStatus(Status.FAILED);

                EspressoIdlingResource.decrement(); // Set app as idle
            }
        });
    }

    @Override
    public void clean() {
        if (productSubscription != null) {
            productSubscription.unsubscribe();
        }
    }


    // == Accessors ================================================================================

    private void setProducts(List<Product> products) {
        this.products.clear();
        this.products.addAll(products);
    }

    public void setStatus(Status status) {
        this.status.set(status);
    }


    // == Minions ==================================================================================

    public enum Status {
        MINT_FRESH,
        LOADING,
        LOADED,
        FAILED,
        REFRESHING;
    }
}
