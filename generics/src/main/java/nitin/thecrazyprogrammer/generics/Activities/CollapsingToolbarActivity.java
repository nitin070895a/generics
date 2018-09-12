package nitin.thecrazyprogrammer.generics.Activities;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import nitin.thecrazyprogrammer.generics.R;

/**
 * Created by Nitin Khurana on 1/27/2018.
 */
public abstract class CollapsingToolbarActivity extends BasicActivity implements AppBarLayout.OnOffsetChangedListener {

    private AppBarLayout appBar;
    private CollapsingToolbarLayout collapsingToolbar;
    private NestedScrollView nestedScrollView;

    private FrameLayout collapsingHeader;

    @Override
    protected int setLayout() {
        return R.layout.collapsing_activity;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        appBar = (AppBarLayout) findViewById(R.id.appBar);
        collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbar);
        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView);

        collapsingHeader = (FrameLayout) findViewById(R.id.collapsingHeader);
        LayoutInflater.from(this).inflate(setCollapsingHeaderLayout(), collapsingHeader);

        appBar.addOnOffsetChangedListener(this);
        customizeCollapsingHeader(collapsingHeader);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment, setFragmentBelowCollapsingToolbar()).commit();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        onFragmentReady();
                    }
                }, BasicFragmentActivity.WAIT_DELAY);
            }
        }, BasicFragmentActivity.WAIT_DELAY);
    }

    @Override
    public final void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

        if (Math.abs(verticalOffset) == appBarLayout.getTotalScrollRange()) {
            // Collapsed
            onCollapse();
        }
        else if (verticalOffset == 0) {
            // Expanded
            onExapand();
        }
    }

    @Override
    protected void customiseToolbar() {
        super.customiseToolbar();

        getToolbar().setBackgroundColor(getResources().getColor(R.color.transparent));
        CollapsingToolbarLayout.LayoutParams params = (CollapsingToolbarLayout.LayoutParams) getToolbar().getLayoutParams();
        params.setCollapseMode(CollapsingToolbarLayout.LayoutParams.COLLAPSE_MODE_PIN);
        getToolbar().setLayoutParams(params);
        getToolbar().requestLayout();
    }

    protected void onCollapse(){

    }

    protected void onExapand(){

    }

    protected void onFragmentReady(){

    }

    protected abstract int setCollapsingHeaderLayout();

    protected abstract void customizeCollapsingHeader(final FrameLayout root);

    protected abstract Fragment setFragmentBelowCollapsingToolbar();

    public CollapsingToolbarLayout getCollapsingToolbar() {
        return collapsingToolbar;
    }

    public NestedScrollView getNestedScrollView() {
        return nestedScrollView;
    }
}
