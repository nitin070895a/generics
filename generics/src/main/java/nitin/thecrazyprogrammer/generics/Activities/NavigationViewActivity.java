package nitin.thecrazyprogrammer.generics.Activities;

import android.animation.ObjectAnimator;
import android.animation.StateListAnimator;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.view.View;

import nitin.thecrazyprogrammer.generics.Fragments.TabbedFragment;
import nitin.thecrazyprogrammer.generics.R;

/**
 * Created by Nitin Khurana on 1/27/2018.
 */
public abstract class NavigationViewActivity extends BasicActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    FloatingActionButton fab;

    Fragment fragment;
    Fragment landingFragment;

    protected boolean backToLandingFragment = false;

    @Override
    protected final int setLayout() {
        return R.layout.nav_drawer_activity;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        navigationView = (NavigationView) findViewById(R.id.navigationView);
        fab = (FloatingActionButton) findViewById(R.id.fab);

        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, getToolbar(), R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            @Override
            public void onDrawerClosed(View drawerView) {
            }

            @Override
            public void onDrawerOpened(View drawerView) {
            }
        };
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        navigationView.inflateMenu(setNavMenu());

        if(setNavHeaderView() != 0)
            customizeNavHeader(navigationView.inflateHeaderView(setNavHeaderView()));

        landingFragment = setLandingFragment();
        openFragment(null, landingFragment);
    }

    @Override
    public void onBackPressed() {

        if(closeDrawer())
            return;

        if(!backToLandingFragment){
            super.onBackPressed();
            return;
        }

        if(fragment.getHost().equals(landingFragment.getHost())){
            super.onBackPressed();
            return;
        }
        else
            openFragment(getActivityTitle().toString(), landingFragment);

    }

    @Override
    public final boolean onNavigationItemSelected(@NonNull MenuItem item) {
        item.setCheckable(true);
        navigationView.setCheckedItem(item.getItemId());
        return onNavItemSelected(item);
    }

    protected void openActivity(Intent intent, boolean closeNavDrawer){
        startActivity(intent);
        if (closeNavDrawer)
            closeDrawer();
    }

    protected void openFragment(String title, Fragment fragment){

        this.fragment = fragment;

        try {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment, fragment).commit();
        } catch (Exception e) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment, fragment).commitAllowingStateLoss();
        }

        if (title != null)
            getToolbar().setTitle(title);


        if(fragment instanceof TabbedFragment){

            getAppBar().setTargetElevation(0);
        }
        else{
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                StateListAnimator stateListAnimator = new StateListAnimator();
                stateListAnimator.addState(new int[0], ObjectAnimator.ofFloat(getAppBar(), "elevation", getResources().getDimensionPixelOffset(R.dimen.appbar_elevation)));
                getAppBar().setStateListAnimator(stateListAnimator);
            }
        }


        closeDrawer();
    }

    protected boolean closeDrawer(){

        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        }

        return false;
    }

    /**
     * Shows the fab button with an animation
     */
    protected void showFab(){
        fab.show();
    }

    /**
     * Hides the fab button with an animation
     */
    protected void hideFab(){
        fab.hide();
    }

    /**
     * Sets and enables the fab button
     * @param icon the icon to be shown in the fab
     * @param onClickListener the action to be done on clicking the fab button
     */
    protected void setFab(int icon, View.OnClickListener onClickListener){
        if(icon != 0)
            fab.setImageResource(icon);
        fab.setVisibility(View.VISIBLE);
        fab.setOnClickListener(onClickListener);
    }

    public NavigationView getNavigationView() {
        return navigationView;
    }

    protected int setNavHeaderView(){
        return 0;
    }

    protected void customizeNavHeader(View header){

    }

    protected abstract int setNavMenu();

    protected abstract Fragment setLandingFragment();

    protected abstract boolean onNavItemSelected(MenuItem item);

    public Fragment getFragment() {
        return fragment;
    }

}
