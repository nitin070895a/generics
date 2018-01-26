package nitin.thecrazzyprogrammer.genericsexample.Activity;

import nitin.thecrazzyprogrammer.generics.Activities.BasicFragmentActivity;
import nitin.thecrazzyprogrammer.generics.Fragments.BasicFragment;
import nitin.thecrazzyprogrammer.genericsexample.Fragment.ExampleLoadingFragment;

/**
 * Created by Nitin Khurana on 1/26/2018.
 */
public class ExampleLoadingActivity extends BasicFragmentActivity{

    @Override
    protected BasicFragment setFragment() {
        return new ExampleLoadingFragment();
    }

    @Override
    protected String setActivityTitle() {
        return "Example Loading Activity";
    }
}
