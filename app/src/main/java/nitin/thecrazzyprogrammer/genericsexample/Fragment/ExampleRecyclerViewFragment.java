package nitin.thecrazzyprogrammer.genericsexample.Fragment;

import nitin.thecrazzyprogrammer.generics.Adapters.LicenseAdapter;
import nitin.thecrazzyprogrammer.generics.Fragments.BasicRecyclerViewFragment;

/**
 * Created by Nitin Khurana on 1/19/2018.
 */
public class ExampleRecyclerViewFragment extends BasicRecyclerViewFragment<LicenseAdapter> {

    String[] titles = {"title A", "title B", "title A", "title B", "title A", "title B", "title A", "title B", "title A", "title B"};
    String[] desc = {"title A", "title B", "title A", "title B", "title A", "title B", "title A", "title B", "title A", "title B"};
    @Override
    protected void loadOrReload() {

        whenLoadingIsCompleteCallThisMethod(true);
    }

    private void whenLoadingIsCompleteCallThisMethod(boolean success){
        loadComplete(success);
        setAdapter(new LicenseAdapter(titles, desc));
    }
}
