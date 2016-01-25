package io.github.gokhanbarisaker.ecommerce.fragment;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.graphics.Palette;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.github.gokhanbarisaker.ecommerce.Application;
import io.github.gokhanbarisaker.ecommerce.R;
import io.github.gokhanbarisaker.ecommerce.databinding.FragmentProductOverviewBinding;
import io.github.gokhanbarisaker.ecommerce.model.Product;
import io.github.gokhanbarisaker.ecommerce.viewmodel.ProductOverviewViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnProductOverviewInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProductOverviewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProductOverviewFragment extends Fragment implements ProductOverviewViewModel.Listener {

    // == Variables ================================================================================

    private static final String BUNDLE_PRODUCT = "productoverview.product";

    private Product product;
    private OnProductOverviewInteractionListener listener;
    private FragmentProductOverviewBinding binding;


    // == Constructors & Instance providers ========================================================

    public ProductOverviewFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param product Product instance that will be briefly displayed.
     * @return A new instance of fragment ProductOverviewFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProductOverviewFragment newInstance(Product product) {
        ProductOverviewFragment fragment = new ProductOverviewFragment();
        Bundle args = new Bundle();
        args.putParcelable(BUNDLE_PRODUCT, product);
        fragment.setArguments(args);
        return fragment;
    }


    // == Fragment lifecycle methods ===============================================================

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnProductOverviewInteractionListener) {
            listener = (OnProductOverviewInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnProductOverviewInteractionListener");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            product = getArguments().getParcelable(BUNDLE_PRODUCT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO: Fix image scaling

        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_product_overview, container, false);
        binding.setViewModel(new ProductOverviewViewModel(this, product));

        return binding.getRoot();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Application.picasso.cancelRequest(binding.imageview);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }


    // == External callbacks =======================================================================

    @Override
    public void onProductOverviewClicked(Product product) {
        OnProductOverviewInteractionListener listener = getListener();

        if (listener != null) {
            listener.onProductOverviewClick(product);
        }
    }


    // == Tools ====================================================================================

    private void loadPalette() {
        // Asynchronous
        final BitmapDrawable drawable = (BitmapDrawable) binding.imageview.getDrawable();
        Palette.from(drawable.getBitmap()).generate(new Palette.PaletteAsyncListener() {
            public void onGenerated(Palette p) {
                // Use generated instance
                int mutedLight = p.getLightMutedColor(getResources().getColor(R.color.muted_light));
                int mutedDark = p.getDarkMutedColor(getResources().getColor(R.color.muted_dark));
                int vibrant = p.getVibrantColor(getResources().getColor(R.color.vibrant));
                int vibrantLight = p.getVibrantColor(getResources().getColor(R.color.vibrant_light));

                binding.textview.setBackgroundColor(mutedLight);
                binding.textview.setTextColor(mutedDark);

                binding.button.setTextColor(vibrantLight);
                GradientDrawable buttonDrawable = (GradientDrawable) binding.button.getBackground();
                buttonDrawable.setColor(vibrant);
            }
        });
    }


    // == Accessors ================================================================================

    public OnProductOverviewInteractionListener getListener() {
        return listener;
    }


    // == Minions ==================================================================================

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnProductOverviewInteractionListener {
        void onProductOverviewClick(Product product);
    }
}
