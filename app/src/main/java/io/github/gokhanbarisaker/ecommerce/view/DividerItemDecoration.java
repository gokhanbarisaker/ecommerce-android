package io.github.gokhanbarisaker.ecommerce.view;

import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;

import org.apache.commons.lang3.NotImplementedException;

import java.util.EnumSet;

/**
 * Created by gokhanbarisaker on 1/13/15.
 * <p/>
 * TODO: Implement for both vertical and horizontal direction. Currently only tested on Vertical
 */
public abstract class DividerItemDecoration extends RecyclerView.ItemDecoration {
    private EnumSet<Divider> edgeDividers = EnumSet.allOf(Divider.class);
    private EnumSet<Divider> interiorDividers = EnumSet.allOf(Divider.class);
    // Divider thickness in pixel
    private int dividerThicknessEdge;
    private int dividerThicknessInterior;
    // Divider thickness half in pixel
    private int dividerThicknessEdgeHalf;
    private int dividerThicknessInteriorHalf;
    public DividerItemDecoration(int dividerThicknessEdge, int dividerThicknessInterior) {
        setDividerThicknessEdge(dividerThicknessEdge);
        setDividerThicknessInterior(dividerThicknessInterior);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        final long timestamp = System.currentTimeMillis();

        super.getItemOffsets(outRect, view, parent, state);

        boolean topEdge = false;
        boolean leftEdge = false;
        boolean rightEdge = false;
        boolean bottomEdge = false;

        // For this is a view with adapter that feeds unknown amount of subviews,
        // knowing exact location is not possible.
        // The trick is, we assume edge row items in recycler-view will always be invisible
        // && false-positive edges will never be visible.
        final int position = ((RecyclerView.LayoutParams) view.getLayoutParams()).getViewLayoutPosition();
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();

        if (layoutManager instanceof StaggeredGridLayoutManager) {
            StaggeredGridLayoutManager staggeredGridLayoutManager = (StaggeredGridLayoutManager) layoutManager;

            // Get total item
            int itemCount = parent.getAdapter().getItemCount();


            StaggeredGridLayoutManager.LayoutParams layoutParams = (StaggeredGridLayoutManager.LayoutParams) view.getLayoutParams();

            int span = staggeredGridLayoutManager.getSpanCount();
            int spanIndex = layoutParams.getSpanIndex();

            topEdge = (position <= span);
            leftEdge = (layoutParams.isFullSpan() || spanIndex == 0);
            rightEdge = (layoutParams.isFullSpan() || spanIndex == (span - 1));
            bottomEdge = ((itemCount - span) <= position); // ???: Not sure of this one
        } else if (layoutManager instanceof GridLayoutManager) {
            GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;

            // Get column quantity
            int spanCount = gridLayoutManager.getSpanCount();
            // Get total item
            int itemCount = parent.getAdapter().getItemCount();

            // Append spans prior to position for accurate check
            int spanSize = gridLayoutManager.getSpanSizeLookup().getSpanSize(position);
            int positionSpanned = 0;

            for (int i = 0; i <= position; i++) {
                positionSpanned += gridLayoutManager.getSpanSizeLookup().getSpanSize(i);
            }

            topEdge = (positionSpanned <= spanCount);
            leftEdge = (((positionSpanned - spanSize) % spanCount) == 0);
            rightEdge = ((positionSpanned % spanCount) == 0);
            // TODO: Implement bottom edge detection!!!
            bottomEdge = false;
        } else if (layoutManager instanceof LinearLayoutManager) {
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;

            if (linearLayoutManager.getOrientation() == LinearLayoutManager.VERTICAL) {
                topEdge = (position == 0);
                leftEdge = true;
                rightEdge = true;
                bottomEdge = (position == (parent.getAdapter().getItemCount() - 1));
            } else {
                topEdge = true;
                leftEdge = (position == 0);
                rightEdge = (position == (parent.getAdapter().getItemCount() - 1));
                bottomEdge = true;
            }
        } else {
            throw new NotImplementedException("Unable to access span data. Please implement on " + this.getClass().getSimpleName());
        }

        if (topEdge) {
            if (edgeDividers.contains(Divider.TOP)) {
                outRect.top = dividerThicknessEdge;
            }
        } else {
            if (interiorDividers.contains(Divider.TOP)) {
                outRect.top = dividerThicknessInteriorHalf;
            }
        }

        if (leftEdge) {
            if (edgeDividers.contains(Divider.LEFT)) {
                outRect.left = dividerThicknessEdge;
            }
        } else {
            if (interiorDividers.contains(Divider.LEFT)) {
                outRect.left = dividerThicknessInteriorHalf;
            }
        }

        if (rightEdge) {
            if (edgeDividers.contains(Divider.RIGHT)) {
                outRect.right = dividerThicknessEdge;
            }
        } else {
            if (interiorDividers.contains(Divider.RIGHT)) {
                outRect.right = dividerThicknessInteriorHalf;
            }
        }

        if (bottomEdge) {
            if (edgeDividers.contains(Divider.BOTTOM)) {
                outRect.bottom = dividerThicknessEdge;
            }
        } else {
            if (interiorDividers.contains(Divider.BOTTOM)) {
                outRect.bottom = dividerThicknessInteriorHalf;
            }
        }


        Log.d("Benchmark", "getItemOffsets @" + (System.currentTimeMillis() - timestamp));
    }

    public EnumSet<Divider> getEdgeDividers() {
        return edgeDividers;
    }

    public void setEdgeDividers(EnumSet<Divider> edgeDividers) {
        this.edgeDividers = edgeDividers;
    }

    public EnumSet<Divider> getInteriorDividers() {
        return interiorDividers;
    }

    public void setInteriorDividers(EnumSet<Divider> interiorDividers) {
        this.interiorDividers = interiorDividers;
    }

    public int getDividerThicknessEdge() {
        return dividerThicknessEdge;
    }

    public void setDividerThicknessEdge(int dividerThicknessEdge) {
        this.dividerThicknessEdge = dividerThicknessEdge;
        this.dividerThicknessEdgeHalf = (int) ((dividerThicknessEdge * .5f) + .5f);
    }

    public int getDividerThicknessEdgeHalf() {
        return dividerThicknessEdgeHalf;
    }

    public int getDividerThicknessInterior() {
        return dividerThicknessInterior;
    }

    public void setDividerThicknessInterior(int dividerThicknessInterior) {
        this.dividerThicknessInterior = dividerThicknessInterior;
        this.dividerThicknessInteriorHalf = (int) ((dividerThicknessInterior * .5f) + .5f);
    }

    public int getDividerThicknessInteriorHalf() {
        return dividerThicknessInteriorHalf;
    }

    public enum Divider {
        TOP,
        LEFT,
        RIGHT,
        BOTTOM
    }
}
