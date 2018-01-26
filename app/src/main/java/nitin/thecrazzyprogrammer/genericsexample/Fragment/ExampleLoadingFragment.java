package nitin.thecrazzyprogrammer.genericsexample.Fragment;

import nitin.thecrazzyprogrammer.generics.Fragments.LoadingFragment;
import nitin.thecrazzyprogrammer.genericsexample.R;

/**
 * Created by Nitin Khruana on 1/21/2018.
 */
public class ExampleLoadingFragment extends LoadingFragment {

    @Override
    protected void loadOrReload() {

        loadComplete(false, "Hello World!");
    }

    @Override
    protected int setInnerLayout() {
        return R.layout.example_fragment;
    }
}
