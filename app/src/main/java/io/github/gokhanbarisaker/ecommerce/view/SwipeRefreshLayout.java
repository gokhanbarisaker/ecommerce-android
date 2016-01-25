package io.github.gokhanbarisaker.ecommerce.view;

import android.content.Context;
import android.databinding.BindingMethod;
import android.databinding.BindingMethods;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by gokhanbarisaker on 11/23/15.
 */
@BindingMethods(@BindingMethod(type = android.support.v4.widget.SwipeRefreshLayout.class, attribute = "app:onRefresh", method = "setOnRefreshListener"))
public class SwipeRefreshLayout extends android.support.v4.widget.SwipeRefreshLayout {

    // == Variables ================================================================================

    private boolean alteredEnabledState = false;


    // == Constructors =============================================================================

    public SwipeRefreshLayout(Context context) {
        super(context);
    }

    public SwipeRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    // == View callbacks ===========================================================================

    @Override
    public void requestDisallowInterceptTouchEvent(boolean b) {
        if (isEnabled() == b) {
            setEnabled(!b);
            alteredEnabledState = true;
        }

        super.requestDisallowInterceptTouchEvent(b);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getActionMasked()) {
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP: {
                if (alteredEnabledState) {
                    setEnabled(true);
                    alteredEnabledState = false;
                }
            }
        }

        return super.onInterceptTouchEvent(ev);
    }
}
