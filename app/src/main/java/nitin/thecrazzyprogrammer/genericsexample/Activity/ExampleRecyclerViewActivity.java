package nitin.thecrazzyprogrammer.genericsexample.Activity;

import nitin.thecrazzyprogrammer.generics.Activities.BasicFragmentActivity;
import nitin.thecrazzyprogrammer.generics.Fragments.BasicRecyclerViewFragment;
import nitin.thecrazzyprogrammer.genericsexample.Fragment.ExampleRecyclerViewFragment;

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
