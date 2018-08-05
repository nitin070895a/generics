package nitin.thecrazyprogrammer.generics.Fragments;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import nitin.thecrazyprogrammer.generics.R;

/**
 * Created by Nitin on 31/07/18.
 */
public abstract class BasicRecyclerViewWithVisibleLoadingFragment<AdapterType extends RecyclerView.Adapter> extends VisibleLoadingFragment {

    private RecyclerView recyclerView;
    private AdapterType adapter;

    @Override
    protected void onCreateView(View view) {
        super.onCreateView(view);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);

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