package io.github.gokhanbarisaker.ecommerce.utility;

import android.util.Log;

import io.github.gokhanbarisaker.ecommerce.Application;
import io.github.gokhanbarisaker.ecommerce.model.Catalog;
import io.github.gokhanbarisaker.ecommerce.network.CatalogService;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by gokhanbarisaker on 11/21/15.
 */
public class CatalogRepository {

    // == Variables ================================================================================

    private CatalogService service;


    // == Constructor ==============================================================================

    public CatalogRepository(CatalogService service) {
        this.service = service;
    }


    // == Tools ====================================================================================

    public Observable<Catalog> fetchProducts() {
        Log.e("Products", "Heating up observable for fetch operation!..");

        CatalogService service = Application.retrofit.create(CatalogService.class);

        return service.fetchCatalog()
                .subscribeOn(Application.SCHEDULER_BACKGROUND)
                .observeOn(AndroidSchedulers.mainThread());
    }
}
