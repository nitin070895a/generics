package nitin.thecrazzyprogrammer.generics.Activities;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import nitin.thecrazzyprogrammer.generics.R;

/**
 * Created by Nitin Khurana on 1/18/2018.
 *
 * The Activity to be extend in every activity instead of Appcompat Activity
 *
 * Now you don't need to create a toolbar instance in your activities. It handles
 * it by itself. If you want to customise the toolbar you can override {@link #customiseToolbar()} method
 *
 * It handles :
 *
 * 1. Back press from tool bar
 * 2. Finds and initialize the toolbar
 * 3. Sets title for the toolbar which you can change from the child activity of this class
 *
 * SetContentView should not be used
 *
 *
 * NOTE : Toolbar's id should always be 'toolbar' in your .xml file
 */
public abstract class BasicActivity extends AppCompatActivity{

    private String TAG = this.getClass().getSimpleName();
    private Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayout());

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(setActivityTitle());
        customiseToolbar();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Gets the toolbar of the activity
     * @return toolbar of the activity
     */
    protected Toolbar getToolbar() {
        return toolbar;
    }

    /**
     * Override this method to customise the toolbar in the activity
     */
    protected void customiseToolbar(){

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    /**
     * Gives title to the toolbar
     * @return title of the activity
     */
    protected abstract String setActivityTitle();

    /**
     * The layout to be fed to the setContentView() method
     * @return the layout that you want to inflate in your activity
     */
    protected abstract int setLayout();

    protected void showLog(String msg){

        Log.e(TAG, "" + msg);
    }
}
