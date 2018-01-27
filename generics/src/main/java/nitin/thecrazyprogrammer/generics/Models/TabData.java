package nitin.thecrazyprogrammer.generics.Models;

import android.os.Bundle;

import nitin.thecrazyprogrammer.generics.Fragments.LoadingFragment;

/**
 * Created by Nitin Khurana on 1/21/2018.
 *<p>
 *     Represents a TAB in a tabbed activity.
 *     <br> Should create an ArrayList of this Model to set tabs in an Tabbed activity
 *</p>
 */
public class TabData {

    private String title;
    private LoadingFragment fragment;

    private int icon;

    private int layout; // for diy tabs

    private Bundle arguments; // if you intend to use same fragment for all tabs

    /**
     *<p>
     *     Represents a TAB in a tabbed activity.
     *     <br> Should create an ArrayList of this Model to set tabs in an Tabbed activity
     *</p>
     */
    public TabData(){

    }

    /**
     *<p>
     *     Represents a TAB in a tabbed activity.
     *     <br> Should create an ArrayList of this Model to set tabs in an Tabbed activity
     *</p>
     * @param title The title of the tab
     * @param fragment The fragment in the tab
     */
    public TabData(String title, LoadingFragment fragment){
        this.title = title;
        this.fragment = fragment;
    }

    /**
     *<p>
     *     Represents a TAB in a tabbed activity.
     *     <br> Should create an ArrayList of this Model to set tabs in an Tabbed activity
     *</p>
     * @param title the title of the tab
     * @param icon the icon of the tab
     * @param fragment the fragment to be loaded
     */
    public TabData(String title, int icon, LoadingFragment fragment){
        this.title = title;
        this.icon = icon;
        this.fragment = fragment;
    }

    public TabData(TabData tabData){
        this.title = tabData.getTitle();
        this.icon = tabData.getIcon();
        this.layout = tabData.getLayout();
        this.arguments = tabData.getArguments();
        this.fragment = tabData.getFragment();
    }

    /**
     * @return the title of the tab
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to be set to the tab
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the icon of the tab
     */
    public int getIcon() {
        return icon;
    }

    /**
     * @param icon The icon to be set to the tab
     */
    public void setIcon(int icon) {
        this.icon = icon;
    }

    /**
     * @return the layout of the tab only if you have specified it using {@link #setLayout(int)} method otherwise null
     */
    public int getLayout() {
        return layout;
    }

    /**
     * If you want to style your tabs yourself i.e specified {@link TabStyler.TabStyle.DIY } use this to set the layout of the tab
     * @param layout the layout to be set to the tab
     */
    public void setLayout(int layout) {
        this.layout = layout;
    }

    /**
     * @return The bundle arguments of the fragment
     */
    public Bundle getArguments() {
        return arguments;
    }

    /**
     * Sets the bundle arguments of the fragment
     * @param arguments the bundle arguments
     */
    public void setArguments(Bundle arguments) {
        this.arguments = arguments;
    }

    /**
     * @return the fragment of the tab
     */
    public LoadingFragment getFragment() {
        return fragment;
    }

    /**
     * @param fragment The fragment to be loaded in the tab
     */
    public void setFragment(LoadingFragment fragment) {
        this.fragment = fragment;
    }
}
