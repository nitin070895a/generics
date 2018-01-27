package nitin.thecrazyprogrammer.genericsexample.Activity;

import android.support.v4.app.Fragment;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import nitin.thecrazyprogrammer.generics.Activities.CollapsingToolbarActivity;
import nitin.thecrazyprogrammer.genericsexample.Fragment.ExampleRecyclerViewFragment;
import nitin.thecrazyprogrammer.genericsexample.R;

/**
 * Created by Nitin Khurana on 1/27/2018.
 */
public class ExampleCollapsingToolbarActivity extends CollapsingToolbarActivity {

    @Override
    protected int setCollapsingHeaderLayout() {
        return R.layout.example_collapsing_header;
    }

    @Override
    protected void customizeCollapsingHeader(FrameLayout root) {

        ((TextView) root.findViewById(R.id.text)).setText("Hello World!");
        ((ImageView) root.findViewById(R.id.image)).setImageResource(R.drawable.cloud_new);
    }

    @Override
    protected Fragment setFragmentBelowCollapsingToolbar() {
        return new ExampleRecyclerViewFragment();
    }

    @Override
    protected String setActivityTitle() {
        return "Example Collapsing Toolbar Activity";
    }
}
