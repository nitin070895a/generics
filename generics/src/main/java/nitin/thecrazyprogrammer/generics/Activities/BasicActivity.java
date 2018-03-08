package nitin.thecrazyprogrammer.generics.Activities;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

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
 * <li>Gives snackbar functionality</li>
 * <li>Gives log methods</li>
 * </ol>
 *<br>
 * <b>Note</b> : SetContentView should not be used
 *<br>
 * <b>Note</b> : Toolbar's id should always be 'toolbar' in your .xml file
 */
public abstract class BasicActivity extends AppCompatActivity{

    private String TAG = this.getClass().getSimpleName();
    private Toolbar toolbar;
    private AppBarLayout appBar;
    private Snackbar snackbar;

    protected boolean doubleBackToExit = false;
    private boolean doubleBackToExitPressedOnce = false;

    private String activityTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayout());

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        appBar = (AppBarLayout) findViewById(R.id.appBar);
        setSupportActionBar(toolbar);
        activityTitle = setActivityTitle();
        getSupportActionBar().setTitle(activityTitle);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        int menu_res = setMenuResource();
        if(menu_res != 0)
            getMenuInflater().inflate(menu_res, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onBackPressed() {

        if(!doubleBackToExit){
            super.onBackPressed();
            return;
        }

        if (doubleBackToExitPressedOnce){
            super.onBackPressed();
            return;
        }

        doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Press BACK again to exit.", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 1000);

    }

    /**
     * If you want to add a menu to your activity override this method
     * @return the resource id of the menu.xml file (return 0 for no menu)
     */
    protected int setMenuResource(){
        return 0;
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
     * Gets the toolbar of the activity
     * @return toolbar of the activity
     */
    protected Toolbar getToolbar() {
        return toolbar;
    }

    /**
     * Gets the AppBarLayout of the activity
     * @return AppBarLayout of the activity
     */
    protected AppBarLayout getAppBar() {
        return appBar;
    }

    public String getActivityTitle() {
        return activityTitle;
    }

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

    private void setSnackTextColor(int color){

        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(color);
    }

    /**
     * Displays a snack bar at the bottom of the screen
     * @param message the message to be shown in the snackbar
     * @param duration the duration in which the snackbar will be visible
     */
    public void showSnackBar(String message, int duration){

        snackbar = Snackbar.make(findViewById(android.R.id.content), activityTitle, duration);
        snackbar.setText(message);
        setSnackTextColor(Color.WHITE);
        snackbar.show();
    }

    /**
     * Displays a snack bar at the bottom of the screen
     * @param message the message to be shown in the snackbar
     * @param color the color of the text in the snackbar
     * @param duration the duration in which the snackbar will be visible
     */
    public void showSnackBar(String message, int color, int duration){

        snackbar = Snackbar.make(findViewById(android.R.id.content), activityTitle, duration);
        snackbar.setText(message);
        setSnackTextColor(color);
        snackbar.show();
    }

    /**
     * Displays a snack bar at the bottom of the screen
     * @param message the message to be shown in the snackbar
     * @param actionText the text of the action
     * @param action the task to be performed on clicking the action
     * @param duration the duration in which the snackbar will be visible
     */
    public void showSnackBar(String message, String actionText, View.OnClickListener action, int duration){

        snackbar = Snackbar.make(findViewById(android.R.id.content), activityTitle, duration);
        snackbar.setText(message);
        setSnackTextColor(Color.WHITE);
        snackbar.setActionTextColor(Color.WHITE);
        snackbar.setAction(actionText, action);
        snackbar.show();
    }

    /**
     * Displays a snack bar at the bottom of the screen
     * @param message the message to be shown in the snackbar
     * @param actionText the text of the action
     * @param action the task to be performed on clicking the action
     * @param textColor the color of the text in the snackbar
     * @param actionColor the color of the action text in the snackbar
     * @param duration the duration in which the snackbar will be visible
     */
    public void showSnackBar(String message, String actionText, View.OnClickListener action, int textColor, int actionColor, int duration){

        snackbar = Snackbar.make(findViewById(android.R.id.content), activityTitle, duration);
        snackbar.setText(message);
        snackbar.setAction(actionText, action);
        setSnackTextColor(textColor);
        snackbar.setActionTextColor(actionColor);
        snackbar.show();
    }

}
