package io.github.gokhanbarisaker.ecommerce.view;

import android.content.Context;
import android.databinding.BindingMethod;
import android.databinding.BindingMethods;
import android.util.AttributeSet;

/**
 * Created by gokhanbarisaker on 11/23/15.
 */
@BindingMethods(@BindingMethod(type = android.support.v4.widget.SwipeRefreshLayout.class, attribute = "app:onRefresh", method = "setOnRefreshListener"))
public class SwipeRefreshLayout extends android.support.v4.widget.SwipeRefreshLayout {

    public SwipeRefreshLayout(Context context) {
        super(context);
    }

    public SwipeRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
}
