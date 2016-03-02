package org.acme.bottomsheetexample;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

public class MainActivity extends AppCompatActivity {

    private BottomSheetBehavior mBehavior;
    private View mBottomSheet;
    private boolean mToggled = false;

    private enum ScreenState {
        HIDDEN,
        MINIMIZED,
        ONETHIRD,
        FULLSCREEN
    }

    private ScreenState mCurrentState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        CoordinatorLayout coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinator_layout);

        mBottomSheet = coordinatorLayout.findViewById(R.id.bottom_sheet);

        mBehavior = BottomSheetBehavior.from(mBottomSheet);
        mBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                // React to state change
                if (newState == BottomSheetBehavior.STATE_SETTLING) return;

                Log.d("STATE", "Before: " + mCurrentState.name());

                switch (newState) {
                    case BottomSheetBehavior.STATE_COLLAPSED:
                        Log.i("BOTTOM", "Collapsed");
                        if (mCurrentState == ScreenState.HIDDEN) {
                            mCurrentState = ScreenState.MINIMIZED;
                            mBehavior.setPeekHeight((int) (44 * getResources().getDisplayMetrics().density));
                            mBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                        } else if (mCurrentState == ScreenState.MINIMIZED) {
                            mBehavior.setPeekHeight((int) (400 * getResources().getDisplayMetrics().density));

                            CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) mBottomSheet.getLayoutParams();

                        } else if (mCurrentState == ScreenState.FULLSCREEN) {
                            mBehavior.setPeekHeight((int) (44 * getResources().getDisplayMetrics().density));
                            mCurrentState = ScreenState.ONETHIRD;
                        } else if (mCurrentState == ScreenState.ONETHIRD) {
                            mBehavior.setPeekHeight((int) (400 * getResources().getDisplayMetrics().density));
                            mCurrentState = ScreenState.MINIMIZED;
                        }
                        break;
                    case BottomSheetBehavior.STATE_DRAGGING:
                        Log.i("BOTTOM", "Dragging");
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED:
                        Log.i("BOTTOM", "Expanded");
                        mCurrentState = ScreenState.FULLSCREEN;
                        break;
                    case BottomSheetBehavior.STATE_HIDDEN:
                        Log.i("BOTTOM", "Hidden");
                        break;
                 }

                Log.d("STATE", "After: " + mCurrentState.name());
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                // React to dragging events
            }
        });

        //mBehavior.setPeekHeight((int) (44 * getResources().getDisplayMetrics().density));
        mBehavior.setHideable(false);
        mBottomSheet.post(new Runnable() {
            @Override
            public void run() {
                mCurrentState = MainActivity.ScreenState.HIDDEN;
                mBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            if (mBehavior != null) {
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
