package nitin.thecrazzyprogrammer.genericsexample.Fragment;

import android.view.View;

import nitin.thecrazzyprogrammer.generics.Fragments.BasicFragment;
import nitin.thecrazzyprogrammer.genericsexample.R;

/**
 * Created by Nitin Khurana on 1/19/2018.
 */
public class ExampleBasicFragment extends BasicFragment {

    @Override
    protected int setLayout() {
        return R.layout.example_fragment;
    }

    @Override
    protected void onCreateView(View view) {

    }
}
