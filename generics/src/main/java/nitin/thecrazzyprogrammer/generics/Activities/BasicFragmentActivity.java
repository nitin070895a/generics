package nitin.thecrazzyprogrammer.generics.Activities;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;

import nitin.thecrazzyprogrammer.generics.Fragments.BasicFragment;
import nitin.thecrazzyprogrammer.generics.Fragments.TabbedFragment;
import nitin.thecrazzyprogrammer.generics.R;


/**
 * Created by Nitin Khurana on 1/19/2018.
 *
 * This activity should be used where you have only one fragment in your activity
 * You can extend this activity to get the following features :
 *
 * 1. Toolbar
 * 2. No need to override Activity onCreate() method
 * 3. No need to create an xml file for your fragment activity
 */
public abstract class BasicFragmentActivity<FragmentType extends BasicFragment> extends BasicActivity{

    private FragmentType fragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fragment = setFragment();

        if(fragment instanceof TabbedFragment)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                getToolbar().setElevation(0);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment, fragment).commit();
    }

    @Override
    protected int setLayout() {
        return R.layout.basic_fragment_activity;
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
