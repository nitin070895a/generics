package nitin.thecrazyprogrammer.generics.Fragments;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import nitin.thecrazyprogrammer.generics.R;

/**
 * Created by Nitin Khurana on 1/19/2018.
 *
 * <p>The fragment to be extended where you want only one recycler view in your fragment</p>
 * <br>
 * Features :
 *<ol>
 *     <li>No need to create an .xml file</li>
 *      <li>Now you only need to create the adapter for the recycler view and everything is handled by this class</li>
 *</ol>
 */
public abstract class BasicRecyclerViewFragment<AdapterType extends RecyclerView.Adapter> extends LoadingFragment{

    private RecyclerView recyclerView;
    private AdapterType adapter;

    @Override
    protected void onCreateView(View view) {
        super.onCreateView(view);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);

        load();
    }

    @Override
    protected int setInnerLayout() {
        return R.layout.recyclerview;
    }

    /**
     * Applies the adapter to the recyclerview
     */
    public void setAdapter(AdapterType adapter){

        this.adapter = adapter;
        recyclerView.setAdapter(this.adapter);
    }

    /**
     * Refresh the data in the recyclerview
     */
    public void refresh(){
        adapter.notifyDataSetChanged();
    }

    /**
     * Gets the recyclerView of the activity
     * @return the recyclerView
     */
    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    /**
     * @return Gives the fragment that is set in the recycler view
     */
    public AdapterType getAdapter() {
        return adapter;
    }

}
