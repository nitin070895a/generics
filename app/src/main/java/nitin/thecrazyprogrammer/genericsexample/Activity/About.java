package nitin.thecrazyprogrammer.genericsexample.Activity;

import java.util.ArrayList;

import nitin.thecrazyprogrammer.generics.Activities.AboutActivity;
import nitin.thecrazyprogrammer.generics.Activities.CollapsingToolbarActivity;
import nitin.thecrazyprogrammer.genericsexample.BuildConfig;
import nitin.thecrazyprogrammer.genericsexample.R;

/**
 * Created by Nitin Khurana on 2/4/2018.
 */
public class About extends AboutActivity{

    @Override
    protected String setAppName() {
        return getString(R.string.app_name);
    }

    @Override
    protected String setVersion() {
        return BuildConfig.VERSION_NAME;
    }

    @Override
    protected int setLogo() {
        return R.drawable.android;
    }

    @Override
    protected ArrayList<AboutButton> setButtons() {

        ArrayList<AboutButton> buttons = new ArrayList<>();
        buttons.add(new AboutButton("Licenses", ExampleRecyclerViewActivity.class));

        return buttons;
    }
}
