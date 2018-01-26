package nitin.thecrazzyprogrammer.generics.Models;

import android.os.Bundle;

import nitin.thecrazzyprogrammer.generics.Fragments.LoadingFragment;

/**
 * Created by Nitin Khurana on 1/21/2018.
 */
public class TabData {

    private String title;
    private LoadingFragment fragment;

    private int icon;

    private int layout; // for diy tabs

    private Bundle arguments; // if you intend to use same fragment for all tabs

    public TabData(){

    }

    public TabData(String title, LoadingFragment fragment){
        this.title = title;
        this.fragment = fragment;
    }

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public int getLayout() {
        return layout;
    }

    public void setLayout(int layout) {
        this.layout = layout;
    }

    public Bundle getArguments() {
        return arguments;
    }

    public void setArguments(Bundle arguments) {
        this.arguments = arguments;
    }

    public LoadingFragment getFragment() {
        return fragment;
    }

    public void setFragment(LoadingFragment fragment) {
        this.fragment = fragment;
    }
}
