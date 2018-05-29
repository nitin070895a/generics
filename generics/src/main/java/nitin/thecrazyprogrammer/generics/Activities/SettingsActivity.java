package nitin.thecrazyprogrammer.generics.Activities;

import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.widget.LinearLayout;
import java.util.ArrayList;
import java.util.List;

import nitin.thecrazyprogrammer.generics.R;

/**
 * Created by Nitin on 18/04/18.
 */
public abstract class SettingsActivity extends AppCompatPreferenceActivity {

    public Toolbar toolbar;
    private LinearLayout root;
    protected AppBarLayout appBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        root = (LinearLayout)findViewById(android.R.id.list).getParent().getParent().getParent();

        appBar = (AppBarLayout) LayoutInflater.from(this).inflate(R.layout.settings_toolbar, root, false);
        //Colorify.colorifyAppBar(appBar);
        toolbar = appBar.findViewById(R.id.toolbar);
//        toolbar.setPopupTheme(Colorify.getPopupTheme());
        toolbar.setPopupTheme(R.style.AppTheme_PopupOverlay);

        root.addView(appBar, 0);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(setTitle());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        getSupportActionBar().setTitle(setTitle());
        super.onBackPressed();
    }

    public void onBuildHeaders(List<Header> target) {
        loadHeadersFromResource(setHeaderFile(), target);
    }

    protected boolean isValidFragment(String fragmentName) {

        boolean valid = PreferenceFragment.class.getName().equals(fragmentName);

        for (String fragmentNamePassed : setFragmentNames())
            valid = valid || fragmentNamePassed.equals(fragmentName);

        return valid;
    }
    @Override
    public boolean onIsMultiPane() {
        return (getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_XLARGE;
    }

    public abstract String setTitle();
    public abstract int setHeaderFile();
    public abstract ArrayList<String> setFragmentNames();

}
