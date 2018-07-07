package nitin.thecrazyprogrammer.generics.Adapters;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.ProgressBar;

import java.util.ArrayList;

import nitin.thecrazyprogrammer.generics.Activities.BasicActivity;
import nitin.thecrazyprogrammer.generics.R;

/**
 * Created by Nitin on 29/06/18.
 */
public abstract class DynamicLengthRecyclerViewAdapter<Data, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH>{

    private static final int SNACK_TIME = 500;

    protected Context context;
    protected RecyclerView recyclerView;
    protected ArrayList<Data> data;

    private int loadLimit = 15;
    private MenuItem menuItem;

    public DynamicLengthRecyclerViewAdapter(Context context, ArrayList<Data> data) {

        this.context = context;
        this.data = new ArrayList<>(data);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.recyclerView = recyclerView;
    }

    protected void setMenuItem(MenuItem menuItem) {
        this.menuItem = menuItem;
    }

    protected void setLoadLimit(int loadLimit) {
        this.loadLimit = loadLimit;
    }

    @Override
    public void onBindViewHolder(VH holder, final int position) {

        if (position == loadLimit -1 && data.size() > loadLimit) {

            if (context instanceof BasicActivity)
                ((BasicActivity)context).showSnackBar(context.getString(R.string.loading_more), SNACK_TIME);

            if (menuItem != null) {
                ProgressBar progressBar = new ProgressBar(context, null, android.R.attr.progressBarStyle);
                progressBar.setPadding(15, 15, 15, 15);
                menuItem.setActionView(progressBar);
            }

            // load some more items
            loadLimit += loadLimit;

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    notifyDataSetChanged();
                    if (menuItem != null) menuItem.setActionView(null);
                    recyclerView.smoothScrollToPosition(position + 1);
                }
            }, 500);
        }
    }

    @Override
    public int getItemCount() {
        return data.size() > loadLimit ? loadLimit : data.size();
    }


}
