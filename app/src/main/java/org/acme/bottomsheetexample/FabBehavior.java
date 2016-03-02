package org.acme.bottomsheetexample;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by neal on 2016-03-01.
 */
public class FabBehavior extends FloatingActionButton.Behavior {

    public FabBehavior() {
        super();
    }

    public FabBehavior(Context context, AttributeSet attrs) {
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, FloatingActionButton child, View dependency) {
        boolean dependsOn = super.layoutDependsOn(parent, child, dependency);
        return dependsOn;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, FloatingActionButton child, View dependency) {
        boolean changed = super.onDependentViewChanged(parent, child, dependency);
        return changed;
    }

    @Override
    public boolean onLayoutChild(CoordinatorLayout parent, FloatingActionButton child, int layoutDirection) {
        boolean layoutChild = super.onLayoutChild(parent, child, layoutDirection);
        return layoutChild;
    }
}
