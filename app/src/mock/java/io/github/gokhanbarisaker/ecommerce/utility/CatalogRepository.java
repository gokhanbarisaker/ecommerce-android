package io.github.gokhanbarisaker.ecommerce.utility;

import io.github.gokhanbarisaker.ecommerce.model.Catalog;
import io.github.gokhanbarisaker.ecommerce.network.CatalogService;
import rx.Observable;

/**
 * Created by gokhanbarisaker on 11/21/15.
 */
public class CatalogRepository {

    // == Variables ================================================================================

    public static Catalog catalog = Catalog.random();
    private CatalogService service;


    // == Constructor ==============================================================================

    public CatalogRepository(CatalogService service) {
    }


    // == Tools ====================================================================================

    public Observable<Catalog> fetchProducts() {
        return Observable.just(catalog);
    }
}
