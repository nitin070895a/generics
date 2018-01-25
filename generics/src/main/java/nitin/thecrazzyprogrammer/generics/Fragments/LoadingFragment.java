package nitin.thecrazzyprogrammer.generics.Fragments;

import android.os.Handler;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import nitin.thecrazzyprogrammer.generics.R;
import nitin.thecrazzyprogrammer.generics.Views.NonAvailabilityHolder;

/**
 * Created by Nitin Khurana on 1/21/2018.
 */
public abstract class LoadingFragment extends BasicFragment{

    private final int LOADING_MIN_THRESHOLD = 0;

    private ProgressBar progressBar;
    private FrameLayout layout;
    private NonAvailabilityHolder nonAvailabilityHolder;

    protected boolean isLoaded = false;

    @Override
    protected int setLayout() {
        return R.layout.loading_fragment;
    }

    @Override
    protected void onCreateView(View view) {

        progressBar = (ProgressBar) view.findViewById(R.id.progress_bar);
        layout = (FrameLayout) view.findViewById(R.id.layout);

        View innerView = getLayoutInflater().inflate(setInnerLayout(), null);
        layout.addView(innerView);

        nonAvailabilityHolder = new NonAvailabilityHolder(getContext(), view);
        nonAvailabilityHolder.setVisibility(View.GONE);
        nonAvailabilityHolder.setImage(R.drawable.cloud_new);
        nonAvailabilityHolder.setButton(getContext().getString(nitin.thecrazzyprogrammer.common.R.string.retry), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                load();
            }
        });
        nonAvailabilityHolder.setMessage(getContext().getString(R.string.no_internet));

        load();
    }

    /**
     * Gives the progress bar of the recycler view
     * @return the progress bar for loading purposes
     */
    public ProgressBar getProgressBar() {
        return progressBar;
    }

    private void showLoading(){

        layout.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);
        nonAvailabilityHolder.setVisibility(View.GONE);
    }

    protected void load(){
        isLoaded = false;
        showLoading();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                loadOrReload();
            }
        }, LOADING_MIN_THRESHOLD);
    }

    protected void loadComplete(boolean success){

        isLoaded = true;
        progressBar.setVisibility(View.GONE);
        nonAvailabilityHolder.setVisibility(success ? View.GONE : View.VISIBLE);
        layout.setVisibility(success ? View.VISIBLE : View.INVISIBLE);
    }

    protected void loadComplete(boolean success, String message){
        loadComplete(success);
        nonAvailabilityHolder.setMessage(message);
    }

    protected void loadComplete(boolean success, String message, boolean retry_available){
        loadComplete(success, message);
        nonAvailabilityHolder.setButtonVisibility(retry_available ? View.VISIBLE : View.GONE);
    }

    protected abstract void loadOrReload();

    protected abstract int setInnerLayout();

}