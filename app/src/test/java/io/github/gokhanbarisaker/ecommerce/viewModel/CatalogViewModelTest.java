package io.github.gokhanbarisaker.ecommerce.viewModel;

import org.junit.Test;

import java.util.Collections;
import java.util.List;

import io.github.gokhanbarisaker.ecommerce.model.Catalog;
import io.github.gokhanbarisaker.ecommerce.model.Product;
import io.github.gokhanbarisaker.ecommerce.utility.CatalogRepository;
import io.github.gokhanbarisaker.ecommerce.viewmodel.CatalogViewModel;
import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.Subject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by gokhanbarisaker on 1/17/16.
 */
public class CatalogViewModelTest {
    @Test
    public void loadProductsSuccess() {
        CatalogRepository repository = mock(CatalogRepository.class);
        Subject<Catalog, Catalog> subject = PublishSubject.create();
        List<Product> products = Collections.EMPTY_LIST;
        Catalog catalog = new Catalog();
        catalog.setItems(products);

        when(repository.fetchProducts()).thenReturn(subject.asObservable());
        CatalogViewModel viewModel = new CatalogViewModel(repository);

        // Start loading...
        viewModel.loadProducts(false);

        verify(repository).fetchProducts();
        assertEquals("CatalogViewModel should update it's state to 'LOADING' after load requested", CatalogViewModel.Status.LOADING, viewModel.status.get());

        // Simulate successful loading...
        subject.onNext(catalog);
        subject.onCompleted();

        assertEquals("CatalogViewModel should update it's state to 'LOADED' after load requested", CatalogViewModel.Status.LOADED, viewModel.status.get());
        assertEquals("CatalogViewModel should update its products with freshly loaded catalog items", products.size(), viewModel.products.size());
        assertTrue("CatalogViewModel should update its products with freshly loaded catalog items", products.containsAll(viewModel.products));
    }

    @Test
    public void loadProductsFailure() {
        CatalogRepository repository = mock(CatalogRepository.class);
        Subject<Catalog, Catalog> subject = PublishSubject.create();

        when(repository.fetchProducts()).thenReturn(subject.asObservable());
        CatalogViewModel viewModel = new CatalogViewModel(repository);

        // Start loading...
        viewModel.loadProducts(false);

        verify(repository).fetchProducts();
        assertEquals("CatalogViewModel should update it's state to 'LOADING' after load requested", CatalogViewModel.Status.LOADING, viewModel.status.get());

        // Simulate error...
        subject.onError(new Exception("Poison pill!"));

        assertEquals("CatalogViewModel should update it's state to 'LOADED' after load requested", CatalogViewModel.Status.FAILED, viewModel.status.get());
    }

    @Test
    public void onRefresh() {
        CatalogRepository repository = mock(CatalogRepository.class);
        when(repository.fetchProducts()).thenReturn(Observable.<Catalog>empty());

        CatalogViewModel viewModel = spy(new CatalogViewModel(repository));

        viewModel.onRefresh();
        verify(viewModel).loadProducts(true);
    }
}
