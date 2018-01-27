package nitin.thecrazyprogrammer.generics.Fragments;

import android.os.Handler;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import nitin.thecrazyprogrammer.common.NonAvailabilityHolder;
import nitin.thecrazyprogrammer.generics.R;

/**
 * Created by Nitin Khurana on 1/21/2018.
 *
 * This fragment includes a loading mechanism and a non availability screen and a progress bar, all integrated
 * Extend this fragment if you want to have some data that needs to be fetched from a backend server
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
        nonAvailabilityHolder.setButton(getContext().getString(nitin.thecrazyprogrammer.common.R.string.retry), new View.OnClickListener() {
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

    /**
     * Should be called when API response or loading stuff is completed and when the progress bar should be removed
     * Removes progress bar, show a non availability screen based on the boolean pass
     * @param success unset if have either no data to show or there was a problem getting the data like no internet connection
     */
    protected void loadComplete(boolean success){

        isLoaded = true;
        progressBar.setVisibility(View.GONE);
        nonAvailabilityHolder.setVisibility(success ? View.GONE : View.VISIBLE);
        layout.setVisibility(success ? View.VISIBLE : View.INVISIBLE);
    }

    /**
     * Should be called when API response or loading stuff is completed and when the progress bar should be removed
     * Removes progress bar, show a non availability screen based on the boolean pass
     * @param success unset if have either no data to show or there was a problem getting the data like no internet connection
     * @param message The message to be shown in non availability screen
     */
    protected void loadComplete(boolean success, String message){
        loadComplete(success);
        nonAvailabilityHolder.setMessage(message);
    }

    /**
     * Should be called when API response or loading stuff is completed and when the progress bar should be removed
     * Removes progress bar, show a non availability screen based on the boolean pass
     * @param success unset if have either no data to show or there was a problem getting the data like no internet connection
     * @param message The message to be shown in non availability screen
     * @param retry_available unset if you don't want to show a retry button
     */
    protected void loadComplete(boolean success, String message, boolean retry_available){
        loadComplete(success, message);
        nonAvailabilityHolder.setButtonVisibility(retry_available ? View.VISIBLE : View.GONE);
    }

    /**
     * All loading should be done in this method and after the loading is
     * completed {@link #loadComplete(boolean)} or other varients of this method should be called
     */
    protected abstract void loadOrReload();

    /**
     * @return the layout to be loaded in the fragment
     */
    protected abstract int setInnerLayout();

    /**
     * @return Gives the NonAvailability screen of this fragment from that you can do whole bunch of stuff like changing the image etc.
     */
    protected NonAvailabilityHolder getNonAvailabilityHolder() {
        return nonAvailabilityHolder;
    }
}