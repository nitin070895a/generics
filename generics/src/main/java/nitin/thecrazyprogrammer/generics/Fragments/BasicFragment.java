package nitin.thecrazyprogrammer.generics.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Nitin Khurana on 1/19/2018.
 *<p>
 * The Fragment to be extended in every Fragment instead of Android's Fragment
 *</p>
 * <br>
 * It gives :
 *<br>
 * <ol>
 *     <li>Reliable Context</li>
 *     <li>Class specific Log methods</li>
 * </ol>
 *<br>
 * <b>Note : </b>{@link #onCreateView(View)} is optional in the child activities
 * <b>Note : </b>{@link #onCreateView(LayoutInflater, ViewGroup, Bundle)} should not be overriden in child activities
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
     * Reload the current fragment,
     * If the parent is activity pass {@link Fragment#getFragmentManager()},
     * if the parent is fragment pass {@link Fragment#getChildFragmentManager()}
     * @param fragmentManager the activity of fragment's fragment manager
     */
    public void reload(FragmentManager fragmentManager){
        fragmentManager.beginTransaction().detach(this).attach(this).commit();
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

    protected LayoutInflater getLInflater() {
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

    /**
     * Displays log with the Activity tag in Log.e()
     * @param msg The message to be logged
     */
    protected void showLog(String msg){

        Log.e(TAG, "" + msg);
    }

    /**
     * Displays log with the Activity tag in Log.e()
     * @param msg The message to be logged
     */
    protected void showLogE(String msg){

        Log.e(TAG, "" + msg);
    }

    /**
     * Displays log with the Activity tag in Log.i()
     * @param msg The message to be logged
     */
    protected void showLogI(String msg){

        Log.i(TAG, "" + msg);
    }

    /**
     * Displays log with the Activity tag in Log.d()
     * @param msg The message to be logged
     */
    protected void showLogD(String msg){

        Log.d(TAG, "" + msg);
    }

    /**
     * Displays log with the Activity tag in Log.v()
     * @param msg The message to be logged
     */
    protected void showLogV(String msg){

        Log.v(TAG, "" + msg);
    }

    /**
     * Displays log with the Activity tag in Log.w()
     * @param msg The message to be logged
     */
    protected void showLogW(String msg){

        Log.w(TAG, "" + msg);
    }
}
