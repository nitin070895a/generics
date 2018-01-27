package nitin.thecrazyprogrammer.genericsexample.Activity;

import nitin.thecrazyprogrammer.generics.Activities.BasicFragmentActivity;
import nitin.thecrazyprogrammer.generics.Fragments.BasicRecyclerViewFragment;
import nitin.thecrazyprogrammer.genericsexample.Fragment.ExampleRecyclerViewFragment;

/**
 * Created by Nitin Khurana on 1/19/2018.
 */
public class ExampleRecyclerViewActivity extends BasicFragmentActivity<BasicRecyclerViewFragment> {

    @Override
    protected String setActivityTitle() {
        return "Basic Recycler View Activity";
    }

    @Override
    protected BasicRecyclerViewFragment setFragment() {
        return new ExampleRecyclerViewFragment();
    }
}
