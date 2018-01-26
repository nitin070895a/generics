package nitin.thecrazzyprogrammer.genericsexample.Activity;

import nitin.thecrazzyprogrammer.generics.Activities.BasicActivity;
import nitin.thecrazzyprogrammer.genericsexample.R;

/**
 * Created by Nitin Khurana on 1/18/2018.
 */
public class ExampleActivity extends BasicActivity {

    @Override
    protected String setActivityTitle() {
        return "Example Basic Activity";
    }

    @Override
    protected int setLayout() {
        return R.layout.example_activity;
    }

}
