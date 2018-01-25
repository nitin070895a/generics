package nitin.thecrazzyprogrammer.generics.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Nitin Khurana on 1/19/2018.
 *
 * The Fragment to be extended in every Fragment instead of Android's Fragment
 *
 * It handles :
 *
 * 1. Back press from tool bar
 * 2. Finds and initialize the toolbar
 * 3. Sets title for the toolbar which you can change from the child activity of this class
 *
 * {@link #onCreateView(View)} is optional in the child activities
 * {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)} should not be overriden in child activities
 *
 */
public abstract class BasicFragment extends Fragment{

    private String TAG = this.getClass().getSimpleName();

    private View view;
    private Context context;
    private LayoutInflater layoutInflater;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override
    public final View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(setLayout(), container, false);
        this.layoutInflater = inflater;
        onCreateView(view);
        return view;
    }

    /**
     * Returns the view in the fragment
     * @return the root view of the fragment
     */
    @Nullable
    public View getView() {
        return view;
    }

    /**
     * Returns the Context of the Activity the fragment is attached to
     * @return the context of the fragment
     */
    public Context getContext() {
        return this.context;
    }

    protected LayoutInflater getLayoutInflater() {
        return layoutInflater;
    }

    /**
     * Sets the layout view for the fragment
     * @return the layout file for the fragment
     */
    protected abstract int setLayout();

    /**
     * The method to be used instead of {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)} method
     * @param view
     */
    protected abstract void onCreateView(View view);

    protected void showLog(String msg){

        Log.e(TAG, "" + msg);
    }
}
