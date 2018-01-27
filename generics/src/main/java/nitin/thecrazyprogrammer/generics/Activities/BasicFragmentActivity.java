package nitin.thecrazyprogrammer.generics.Activities;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import nitin.thecrazyprogrammer.generics.Fragments.BasicFragment;
import nitin.thecrazyprogrammer.generics.Fragments.TabbedFragment;
import nitin.thecrazyprogrammer.generics.R;


/**
 * Created by Nitin Khurana on 1/19/2018.
 *<br>
 * <p>This activity should be used where you have only one fragment in your activity
 * You can extend this activity to get the following features :
 *</p>
 * <ol>
 *     <li>Toolbar</li>
 *     <li>Back press code</li>
 *     <li>No need to create an xml file for your fragment activity</li>
 * </ol>
 */
public abstract class BasicFragmentActivity<FragmentType extends BasicFragment> extends BasicActivity{

    private FragmentType fragment;
    public static final int WAIT_DELAY = 50;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fragment = setFragment();

        if(fragment instanceof TabbedFragment)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                getToolbar().setElevation(0);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment, fragment).commit();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                onFragmentReady();
            }
        }, WAIT_DELAY);
    }

    @Override
    protected int setLayout() {
        return R.layout.basic_fragment_activity;
    }

    /**
     * Gets called when fragment is attached to the activity and is ready to be used
     */
    protected void onFragmentReady(){

    }

    /**
     * Sets the fragment to be inflated in the activity root
     * @return the only fragment in the activity
     */
    protected abstract FragmentType setFragment();

    /**
     * Gives the fragment that is currently inflated in the fragment activity
     * @return the only fragment in the fragment activity
     */
    public FragmentType getFragment() {
        return fragment;
    }

}
