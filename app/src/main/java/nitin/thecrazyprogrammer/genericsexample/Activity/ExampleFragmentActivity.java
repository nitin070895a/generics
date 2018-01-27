package nitin.thecrazyprogrammer.genericsexample.Activity;

import nitin.thecrazyprogrammer.generics.Activities.BasicFragmentActivity;
import nitin.thecrazyprogrammer.genericsexample.Fragment.ExampleBasicFragment;

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


