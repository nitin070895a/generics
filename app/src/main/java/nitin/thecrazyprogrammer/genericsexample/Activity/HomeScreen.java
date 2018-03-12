package nitin.thecrazyprogrammer.genericsexample.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import nitin.thecrazyprogrammer.generics.Activities.NavigationViewActivity;
import nitin.thecrazyprogrammer.generics.Dialogs.RateUsPopup;
import nitin.thecrazyprogrammer.genericsexample.Fragment.ExampleBasicFragment;
import nitin.thecrazyprogrammer.genericsexample.Fragment.ExampleLoadingFragment;
import nitin.thecrazyprogrammer.genericsexample.Fragment.ExampleRecyclerViewFragment;
import nitin.thecrazyprogrammer.genericsexample.Fragment.ExampleTabbedFragment;
import nitin.thecrazyprogrammer.genericsexample.Fragment.ExampleWebViewFragment;
import nitin.thecrazyprogrammer.genericsexample.Fragment.HomeFragment;
import nitin.thecrazyprogrammer.genericsexample.R;

/**
 * Created by Nitin Khurana on 1/25/2018.
 */
public class HomeScreen extends NavigationViewActivity{

    @Override
    protected String setActivityTitle() {
        return "Example Nav Drawer Activity";
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        doubleBackToExit = true;
        backToLandingFragment = true;

        new RateUsPopup(this, null, true, "00:00:01", "RATE_US", new RateUsPopup.RateUsPopupListener() {

            @Override
            public void onRate() {

            }

            @Override
            public void onCrossed() {

            }

            @Override
            public void onFeedback() {

            }

            @Override
            public void onLater() {

            }
        }).show();

    }

    @Override
    protected int setNavMenu() {
        return R.menu.example_nav_menu;
    }

    @Override
    protected int setNavHeaderView() {
        return R.layout.exmaple_nav_header;
    }

    @Override
    protected void customizeNavHeader(View header) {
        ((ImageView) header.findViewById(R.id.image)).setImageResource(R.drawable.android);
    }

    @Override
    protected Fragment setLandingFragment() {
        return new HomeFragment();
    }

    @Override
    protected int setMenuResource() {
        return R.menu.example_menu;
    }

    @Override
    public boolean onNavItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

            case R.id.fHome : openFragment(item.getTitle().toString(), this.getLandingFragment()); break;
            case R.id.bFragment : openFragment(item.getTitle().toString(), new ExampleBasicFragment()); break;
            case R.id.lFragment : openFragment(item.getTitle().toString(), new ExampleLoadingFragment()); break;
            case R.id.rFragment : openFragment(item.getTitle().toString(), new ExampleRecyclerViewFragment()); break;
            case R.id.wFragment : openFragment(item.getTitle().toString(), new ExampleWebViewFragment()); break;
            case R.id.tFragment : openFragment(item.getTitle().toString(), new ExampleTabbedFragment()); break;
            case R.id.cActivity : openActivity(new Intent(this, ExampleCollapsingToolbarActivity.class), false); break;
            case R.id.aboutActivity : openActivity(new Intent(this, About.class), false); break;

            default: return false;
        }

        return true;
    }
}
