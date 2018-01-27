package nitin.thecrazyprogrammer.genericsexample.Fragment;

import nitin.thecrazyprogrammer.generics.Fragments.VisibleLoadingFragment;
import nitin.thecrazyprogrammer.genericsexample.R;

/**
 * Created by Nitin Khurana on 1/22/2018.
 */
public class ExampleVisibleLoadingFragment extends VisibleLoadingFragment {

    @Override
    protected void loadOrReload() {

        loadComplete(false, "Hello World!");
    }

    @Override
    protected int setInnerLayout() {
        return R.layout.example_fragment;
    }
}
