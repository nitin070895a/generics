package nitin.thecrazyprogrammer.genericsexample.Activity;

import nitin.thecrazyprogrammer.generics.Activities.BasicActivity;
import nitin.thecrazyprogrammer.genericsexample.R;

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

    @Override
    protected int setMenuResource() {
        return R.menu.example_menu;
    }

}
