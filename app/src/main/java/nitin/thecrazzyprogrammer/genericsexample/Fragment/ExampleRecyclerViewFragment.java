package nitin.thecrazzyprogrammer.genericsexample.Fragment;

import nitin.thecrazzyprogrammer.generics.Adapters.LicenseAdapter;
import nitin.thecrazzyprogrammer.generics.Fragments.BasicRecyclerViewFragment;

/**
 * Created by Nitin Khurana on 1/19/2018.
 */
public class ExampleRecyclerViewFragment extends BasicRecyclerViewFragment<LicenseAdapter> {

    @Override
    protected void loadOrReload() {

    }

    private void whenLoadingIsCompleteCallThisMethod(){
        setAdapter(new LicenseAdapter(null, null));
    }
}
