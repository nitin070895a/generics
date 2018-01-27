package nitin.thecrazyprogrammer.genericsexample.Activity;

import nitin.thecrazyprogrammer.generics.Activities.BasicFragmentActivity;
import nitin.thecrazyprogrammer.generics.Fragments.TabbedFragment;
import nitin.thecrazyprogrammer.genericsexample.Fragment.ExampleTabbedFragment;

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
