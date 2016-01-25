package io.github.gokhanbarisaker.ecommerce.viewmodel;

import io.github.gokhanbarisaker.ecommerce.model.Product;

/**
 * Created by gokhanbarisaker on 1/10/16.
 */
public class ProductOverviewViewModel {
    // == Variables ================================================================================

    private Listener listener;
    private Product product;


    // == Constructors =============================================================================

    // TODO: Replace product with repository for abstracting model load logic
    public ProductOverviewViewModel(Listener listener, Product product) {
        this.listener = listener;
        this.product = product;
    }


    // == Callbacks ================================================================================

    public void onClick(Object view) {
        Listener listener = this.listener;

        if (listener != null) {
            listener.onProductOverviewClicked(product);
        }
    }


    // == Accessors ================================================================================

    public String getImageUrl() {
        return product.getImage();
    }

    public String getName() {
        return product.getName();
    }


    // == Minions ==================================================================================

    public interface Listener {
        void onProductOverviewClicked(Product product);
    }
}
