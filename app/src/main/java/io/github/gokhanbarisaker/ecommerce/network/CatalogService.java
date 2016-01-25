package io.github.gokhanbarisaker.ecommerce.network;

import io.github.gokhanbarisaker.ecommerce.model.Catalog;
import retrofit.http.GET;
import rx.Observable;

/**
 * Created by gokhanbarisaker on 11/21/15.
 */
public interface CatalogService {
    @GET("/v1/catalog")
    public Observable<Catalog> fetchCatalog();
}
