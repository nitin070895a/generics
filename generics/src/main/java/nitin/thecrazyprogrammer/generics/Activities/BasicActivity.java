package nitin.thecrazyprogrammer.generics.Activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import nitin.thecrazyprogrammer.generics.R;

/**
 * Created by Nitin Khurana on 1/18/2018.
 *<br>
 * <p>
 * The Activity to be extend in every activity instead of Appcompat Activity
 *<br>
 * Now you don't need to create a toolbar instance in your activities. It handles it by itself.
 * </p>
 * <p>if you want to customise the toolbar you can override {@link #customiseToolbar()} method</p>
 *<br>
 * It handles :
 *<br>
 * <ol>
 * <li>Back press from tool bar</li>
 * <li>Finds and initialize the toolbar</li>
 * <li>Sets title for the toolbar which you can change from the child activity of this class</li>
 * </ol>
 *<br>
 * <b>Note</b> : SetContentView should not be used
 *<br>
 * <b>Note</b> : Toolbar's id should always be 'toolbar' in your .xml file
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

    /**
     * Displays log with the Activity tag in Log.e()
     * @param msg The message to be logged
     */
    protected void showLog(String msg){

        Log.e(TAG, "" + msg);
    }

    /**
     * Displays log with the Activity tag in Log.e()
     * @param msg The message to be logged
     */
    protected void showLogE(String msg){

        Log.e(TAG, "" + msg);
    }

    /**
     * Displays log with the Activity tag in Log.i()
     * @param msg The message to be logged
     */
    protected void showLogI(String msg){

        Log.i(TAG, "" + msg);
    }

    /**
     * Displays log with the Activity tag in Log.d()
     * @param msg The message to be logged
     */
    protected void showLogD(String msg){

        Log.d(TAG, "" + msg);
    }

    /**
     * Displays log with the Activity tag in Log.v()
     * @param msg The message to be logged
     */
    protected void showLogV(String msg){

        Log.v(TAG, "" + msg);
    }

    /**
     * Displays log with the Activity tag in Log.w()
     * @param msg The message to be logged
     */
    protected void showLogW(String msg){

        Log.w(TAG, "" + msg);
    }
}
