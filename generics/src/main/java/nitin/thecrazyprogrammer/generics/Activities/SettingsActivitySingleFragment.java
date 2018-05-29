package nitin.thecrazyprogrammer.generics.Activities;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.ContentFrameLayout;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.widget.LinearLayout;

import nitin.thecrazyprogrammer.generics.Fragments.GenericSettingsFragment;
import nitin.thecrazyprogrammer.generics.R;

/**
 * Created by Nitin on 18/04/18.
 */

public abstract class SettingsActivitySingleFragment extends AppCompatPreferenceActivity{

    public Toolbar toolbar;
    private ContentFrameLayout root;
    protected AppBarLayout appBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //LinearLayout root = (LinearLayout)findViewById(android.R.id.list).getParent().getParent().getParent();
        root = findViewById(android.R.id.content);

        appBar = (AppBarLayout) LayoutInflater.from(this).inflate(R.layout.settings_toolbar, root, false);
        toolbar = appBar.findViewById(R.id.toolbar);
        //Colorify.colorifyAppBar(appBar);
        root.addView(appBar, 0);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(setTitle());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                LinearLayout linearLayout = new LinearLayout(SettingsActivitySingleFragment.this);
                ContentFrameLayout.LayoutParams params =new ContentFrameLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

                params.setMargins(0, appBar.getHeight(),0,0);
                linearLayout.setLayoutParams(params);
                linearLayout.setId(R.id.settings_info_content);

                root.addView(linearLayout);

                getFragmentManager().beginTransaction().replace(R.id.settings_info_content, setFragment()).commit();
            }
        }, 2);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == android.R.id.home){
            onBackPressed();
        }
        return true;
    }

    public abstract String setTitle();
    public abstract GenericSettingsFragment setFragment();

}
