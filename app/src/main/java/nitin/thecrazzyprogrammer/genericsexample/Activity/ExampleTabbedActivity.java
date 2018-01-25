package nitin.thecrazzyprogrammer.genericsexample.Activity;

import nitin.thecrazzyprogrammer.generics.Activities.BasicFragmentActivity;
import nitin.thecrazzyprogrammer.generics.Fragments.TabbedFragment;
import nitin.thecrazzyprogrammer.genericsexample.Fragment.ExampleTabbedFragment;

/**
 * Created by Nitin Khurana on 1/21/2018.
 */
public class ExampleTabbedActivity extends BasicFragmentActivity<TabbedFragment> {

    @Override
    protected TabbedFragment setFragment() {
        return new ExampleTabbedFragment();
    }

    @Override
    protected String setActivityTitle() {
        return "Example Tabbed Activity";
    }

}
