package io.github.gokhanbarisaker.ecommerce.view;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

/**
 * Created by gokhanbarisaker on 2/1/15.
 */
public class PaintDividerItemDecoration extends DividerItemDecoration {
    final private Paint paint;
    final private int dividerPadding;

    public PaintDividerItemDecoration(int dividerThicknessEdge, int dividerThicknessInterior, int color, int dividerPadding) {
        // TODO: We might require seperate paint for edges, for it has different stoke thickness
        this(dividerThicknessEdge, dividerThicknessInterior, dividerPadding, new PaintBuilder()
                .setColor(color)
                .setStrokeWidth(dividerThicknessInterior)
                .build());
    }

    public PaintDividerItemDecoration(int dividerThicknessEdge, int dividerThicknessInterior, int dividerPadding, Paint paint) {
        super(dividerThicknessEdge, dividerThicknessInterior);
        this.paint = paint;
        this.dividerPadding = dividerPadding;
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        long timestamp = System.currentTimeMillis();

        super.onDrawOver(c, parent, state);
        drawLinearDividers(c, parent);

        Log.d("Benchmark", "onDrawOver @" + (System.currentTimeMillis() - timestamp));
    }

    private void drawLinearDividers(Canvas canvas, RecyclerView recyclerView) {
        View child;
        final int quantity = recyclerView.getChildCount();
        final int lastItemIndex = quantity - 1;

        int horizontalTop;
        int horizontalLeft;
        int horizontalRight;
        int horizontalBottom;

        int verticalTop;
        int verticalLeft;
        int verticalRight;
        int verticalBottom;

        // If recycler-view contains at least one item
        if (quantity > 0) {
            child = recyclerView.getChildAt(0);


            // Horizontal

            horizontalTop = child.getTop() - getDividerThicknessEdgeHalf();
            horizontalLeft = child.getLeft() - getDividerThicknessEdgeHalf() + dividerPadding;
            horizontalRight = child.getRight() + getDividerThicknessEdgeHalf() - dividerPadding;
            horizontalBottom = child.getBottom() + getDividerThicknessEdgeHalf();

            // Draw top divider, if requested
            if (getEdgeDividers().contains(Divider.TOP)) {
                // Top
                canvas.drawLine(horizontalLeft, horizontalTop, horizontalRight, horizontalTop, paint);
            }

            // Draw bottom divider, if requested
            if (getEdgeDividers().contains(Divider.BOTTOM)) {
                // Bottom
                canvas.drawLine(horizontalLeft, horizontalBottom, horizontalRight, horizontalBottom, paint);
            }
        }

        for (int i = 1; i < lastItemIndex; i++) {
            child = recyclerView.getChildAt(i);

            horizontalTop = child.getTop() - getDividerThicknessInteriorHalf();
            horizontalLeft = child.getLeft() - getDividerThicknessInteriorHalf() + dividerPadding;
            horizontalRight = child.getRight() + getDividerThicknessInteriorHalf() - dividerPadding;
            horizontalBottom = child.getBottom() + getDividerThicknessInteriorHalf();

            // Top
            if (getInteriorDividers().contains(Divider.TOP)) {
                canvas.drawLine(horizontalLeft, horizontalTop, horizontalRight, horizontalTop, paint);
            }
            // Bottom
            if (getInteriorDividers().contains(Divider.BOTTOM)) {
                canvas.drawLine(horizontalLeft, horizontalBottom, horizontalRight, horizontalBottom, paint);
            }

            verticalTop = child.getTop() - getDividerThicknessInteriorHalf();
            verticalLeft = child.getLeft() - getDividerThicknessInteriorHalf();
            verticalRight = child.getRight() + getDividerThicknessInteriorHalf();
            verticalBottom = child.getBottom() + getDividerThicknessInteriorHalf();

            // Left
            if (getInteriorDividers().contains(Divider.LEFT)) {
                canvas.drawLine(verticalLeft, verticalTop, verticalLeft, verticalBottom, paint);
            }
            // Right
            if (getInteriorDividers().contains(Divider.RIGHT)) {
                canvas.drawLine(verticalRight, verticalTop, verticalRight, verticalBottom, paint);
            }
        }
    }


    // == Minions ========================================

    public static class PaintBuilder {
        private int color = Color.BLACK;
        private int strokeWidth;
        private Paint.Join strokeJoin = Paint.Join.ROUND;
        private Paint.Style style = Paint.Style.STROKE;
        private boolean antiAlias = true;
        private boolean dither = false;

        public PaintBuilder setColor(int color) {
            this.color = color;

            return this;
        }

        public PaintBuilder setStrokeWidth(int strokeWidth) {
            this.strokeWidth = strokeWidth;

            return this;
        }

        public Paint build() {
            Paint paint = new Paint();
            paint.setColor(color);
            paint.setStrokeWidth(strokeWidth);
            paint.setStrokeJoin(strokeJoin);
            paint.setStyle(style);
            paint.setAntiAlias(antiAlias);
            paint.setDither(dither);

            return paint;
        }
    }
}
