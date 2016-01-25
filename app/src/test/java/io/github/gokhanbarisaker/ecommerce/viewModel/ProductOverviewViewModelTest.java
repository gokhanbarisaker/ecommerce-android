package io.github.gokhanbarisaker.ecommerce.viewModel;

import org.junit.Assert;
import org.junit.Test;

import io.github.gokhanbarisaker.ecommerce.model.Product;
import io.github.gokhanbarisaker.ecommerce.viewmodel.ProductOverviewViewModel;
import io.github.gokhanbarisaker.ecommerce.viewmodel.ProductOverviewViewModel.Listener;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by gokhanbarisaker on 1/17/16.
 */
public class ProductOverviewViewModelTest {
    @Test
    public void imageUrl() {
        String urlOrigin = "http://static-origin.zalora.com.my/p/jaxon-4980-310333-19.jpg";
        String urlExpected = "http://static-origin.zalora.com.my/p/jaxon-4980-310333-19.jpg";

        Product product = new Product();
        product.setImage(urlOrigin);

        ProductOverviewViewModel viewModel = new ProductOverviewViewModel(null, product);
        Assert.assertEquals("View model should provide loadable image URLs", urlExpected, viewModel.getImageUrl());
    }

    @Test
    public void listener() {
        Product product = new Product();
        Listener listener = mock(Listener.class);

        ProductOverviewViewModel viewModel = new ProductOverviewViewModel(listener, product);
        viewModel.onClick(null);
        verify(listener).onProductOverviewClicked(product);
    }
}
