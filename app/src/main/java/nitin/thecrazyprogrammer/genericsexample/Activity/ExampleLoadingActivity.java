package nitin.thecrazyprogrammer.genericsexample.Activity;

import nitin.thecrazyprogrammer.generics.Activities.BasicFragmentActivity;
import nitin.thecrazyprogrammer.generics.Fragments.BasicFragment;
import nitin.thecrazyprogrammer.genericsexample.Fragment.ExampleLoadingFragment;

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
