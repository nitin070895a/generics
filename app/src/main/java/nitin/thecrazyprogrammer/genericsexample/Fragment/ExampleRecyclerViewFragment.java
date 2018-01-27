package nitin.thecrazyprogrammer.genericsexample.Fragment;

import nitin.thecrazyprogrammer.generics.Adapters.CardViewRecyclerAdapter;
import nitin.thecrazyprogrammer.generics.Fragments.BasicRecyclerViewFragment;

/**
 * Created by Nitin Khurana on 1/19/2018.
 */
public class ExampleRecyclerViewFragment extends BasicRecyclerViewFragment<CardViewRecyclerAdapter> {

    String[] titles = {"title A", "title B", "title A", "title B", "title A", "title B", "title A", "title B", "title A", "title B"};
    String[] desc = {"desc A", "desc B", "desc A", "desc B", "desc A", "desc B", "desc A", "desc B", "desc A", "desc B"};
    @Override
    protected void loadOrReload() {

        whenLoadingIsCompleteCallThisMethod(true);
    }

    private void whenLoadingIsCompleteCallThisMethod(boolean success){
        loadComplete(success);
        setAdapter(new CardViewRecyclerAdapter(titles, desc));
    }
}
