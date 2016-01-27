package io.github.gokhanbarisaker.ecommerce.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import io.github.gokhanbarisaker.ecommerce.Application;
import io.github.gokhanbarisaker.ecommerce.databinding.ItemProductOverviewBinding;
import io.github.gokhanbarisaker.ecommerce.model.Product;
import io.github.gokhanbarisaker.ecommerce.viewmodel.ProductOverviewViewModel;


/**
 * Created by gokhanbarisaker on 11/21/15.
 */
public class ProductOverviewAdapter extends RecyclerView.Adapter<ProductOverviewAdapter.ViewHolder> implements ProductOverviewViewModel.Listener {

    // == Variables ================================================================================

    private List<Product> products;
    private Listener listener;


    // == Constructor ==============================================================================

    public ProductOverviewAdapter(List<Product> products, Listener listener) {
        this.products = products;
        this.listener = listener;
    }


    // == Callbacks ========================================================================

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemProductOverviewBinding binding = ItemProductOverviewBinding.inflate(inflater, parent, false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Product product = products.get(position);
        holder.binding.setViewModel(new ProductOverviewViewModel(this, product));
    }

    @Override
    public void onViewRecycled(ViewHolder holder) {
        super.onViewRecycled(holder);

        Application.picasso.cancelRequest(holder.binding.imageview);
    }

    @Override
    public void onProductOverviewClicked(Product product) {
        if (listener != null) {
            listener.onProductOverviewClicked(product);
        }
    }


    // == Accessors ================================================================================

    @Override
    public int getItemCount() {
        return products.size();
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    // == Minions ==================================================================================

    public interface Listener {
        void onProductOverviewClicked(Product product);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ItemProductOverviewBinding binding;

        public ViewHolder(ItemProductOverviewBinding binding) {
            super(binding.getRoot());

            this.binding = binding;
        }
    }

    // ==

//    private void loadPalette() {
//        // Asynchronous
//        final BitmapDrawable drawable = (BitmapDrawable) binding.imageview.getDrawable();
//        Palette.from(drawable.getBitmap()).generate(new Palette.PaletteAsyncListener() {
//            public void onGenerated(Palette p) {
//                // Use generated instance
//                int mutedLight = p.getLightMutedColor(getResources().getColor(R.color.muted_light));
//                int mutedDark = p.getDarkMutedColor(getResources().getColor(R.color.muted_dark));
//                int vibrant = p.getVibrantColor(getResources().getColor(R.color.vibrant));
//                int vibrantLight = p.getVibrantColor(getResources().getColor(R.color.vibrant_light));
//
//                binding.textview.setBackgroundColor(mutedLight);
//                binding.textview.setTextColor(mutedDark);
//
//                binding.button.setTextColor(vibrantLight);
//                GradientDrawable buttonDrawable = (GradientDrawable) binding.button.getBackground();
//                buttonDrawable.setColor(vibrant);
//            }
//        });
//    }
}
