package nitin.thecrazzyprogrammer.genericsexample.Activity;

import nitin.thecrazzyprogrammer.generics.Activities.BasicFragmentActivity;
import nitin.thecrazzyprogrammer.genericsexample.Fragment.ExampleBasicFragment;

/**
 * Created by Nitin Khurana on 1/19/2018.
 */
public class ExampleFragmentActivity extends BasicFragmentActivity<ExampleBasicFragment> {

    @Override
    protected ExampleBasicFragment setFragment() {
        return new ExampleBasicFragment();
    }

    @Override
    protected String setActivityTitle() {
        return "Example Fragment Activity";
    }
}


