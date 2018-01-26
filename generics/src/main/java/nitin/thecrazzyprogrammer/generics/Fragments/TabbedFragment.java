package nitin.thecrazzyprogrammer.generics.Fragments;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import java.util.ArrayList;

import nitin.thecrazzyprogrammer.generics.Adapters.TabLayoutAdapter;
import nitin.thecrazzyprogrammer.generics.Models.TabData;
import nitin.thecrazzyprogrammer.generics.Models.TabStyler;
import nitin.thecrazzyprogrammer.generics.R;
import nitin.thecrazzyprogrammer.generics.Models.TabStyler.TabsGravity;
import nitin.thecrazzyprogrammer.generics.Models.TabStyler.TabsStyle;

/**
 * Created by Nitin Khurana on 1/16/2018.
 *
 * This class gives a tabbed activity with dynamic tabs and loading mechanism
 * Also you get a progress bar and a non availability screen with the same
 *
 * You can also do whole bunch of customization with the tabs like coloring, gravity and styling
 * to do the customization use {@link #setStyleProperties()} methods using the {@link #tabStyler} instance
 *
 * You also get some pre-made fab styles like simple, icon only, icon with text or you can set the custom
 * tab design using DIY styling and overriding these methods
 * {@link #customTabLayout(TabData)} to set the layout for a tab
 * {@link #normalTabDesign(int)} to set the style in normal state
 * {@link #highlightTabDesign(int)} to set the style in highlighted state
 *
 * Bonus : you also get a fab button but it is hidden by default you can enable it using {@link #showFab()}
 * and customize using {@link #setFab(int, View.OnClickListener)}
 */
public abstract class TabbedFragment extends LoadingFragment{

    private TabLayout tabLayout;
    private ViewPager viewPager;

    private FloatingActionButton fab;
    protected TabStyler tabStyler = new TabStyler();

    @Override
    protected int setInnerLayout() {
        return R.layout.tabbed_fragment;
    }

    @Override
    protected void onCreateView(View view) {
        super.onCreateView(view);

        viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        tabLayout = (TabLayout) view.findViewById(R.id.tabLayout);
        setStyleProperties();

        tabLayout.setBackgroundColor(getResources().getColor(tabStyler.tab_layout_background));
        tabLayout.setTabTextColors(getResources().getColor(tabStyler.tab_text_color), getResources().getColor(tabStyler.tab_text_highlight_color));
        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(tabStyler.tab_indicator_color));

        fab = (FloatingActionButton) view.findViewById(R.id.fab);

        applyGravity(tabStyler.tabGravity);

    }

    /**
     * Use this method after loading is completed to set the tablayout
     * @param tabs the array list of tabs
     */
    protected void setUpTabs(final ArrayList<TabData> tabs){

        loadComplete(true);

        viewPager.setOffscreenPageLimit(tabs.size() - 1);
        viewPager.setAdapter(new TabLayoutAdapter(((AppCompatActivity)getContext()).getSupportFragmentManager(), tabs));

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                setTabColors(tab.getPosition(), tabs);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                setTabColors(position, tabs);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        for(int i=0; i<tabs.size(); i++)
            setTabLayout(tabs.get(i), i);

        if(tabs.size() > 0)
            setTabColors(0, tabs);
    }

    private void applyGravity(TabStyler.TabsGravity tabsGravity){

        if(tabsGravity == null)
            return;

        switch (tabsGravity){
            case TABS_GRAVITY_TOP:
                // by default gravity bottom in xml file
                break;
            case TABS_GRAVITY_BOTTOM:

                RelativeLayout.LayoutParams tabLayoutLayoutParams = (RelativeLayout.LayoutParams) tabLayout.getLayoutParams();
                tabLayoutLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
                tabLayout.setLayoutParams(tabLayoutLayoutParams);

                RelativeLayout.LayoutParams viewPagerLayoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                viewPagerLayoutParams.addRule(RelativeLayout.ABOVE, R.id.tabLayout);
                viewPager.setLayoutParams(viewPagerLayoutParams);

                break;
        }
    }

    /**
     * Shows the fab button with an animation
     */
    protected void showFab(){
        fab.show();
    }

    /**
     * Hides the fab button with an animation
     */
    protected void hideFab(){
        fab.hide();
    }

    /**
     * Sets and enables the fab button
     * @param icon the icon to be shown in the fab
     * @param onClickListener the action to be done on clicking the fab button
     */
    protected void setFab(int icon, View.OnClickListener onClickListener){
        if(icon != 0)
            fab.setImageResource(icon);
        fab.setVisibility(View.VISIBLE);
        fab.setOnClickListener(onClickListener);
    }

    /**
     * Customize the tab using {@link #tabStyler} instance
     * <br>
     * you can change : colors, gravity (top, bottom), style (normal, icon only, icom with text, diy)
     * <br>
     *  you can set the custom tab design using DIY styling and overriding these methods
     * {@link #customTabLayout(TabData)} to set the layout for a tab
     * {@link #normalTabDesign(int)} to set the style in normal state
     * {@link #highlightTabDesign(int)} to set the style in highlighted state
     */
    protected abstract void setStyleProperties();

    /**
     * Override this method when using DIY styling ignore otherwise
     * This method will be called for each tab
     * @param data the tab data for each tab
     * @return the customView to be set to the tab
     */
    protected View customTabLayout(TabData data){

        return null;
    }

    private void setTabLayout(TabData tab, int position){

        TabStyler.TabsStyle tabsStyle = tabStyler.tabsStyle;

        if(tabsStyle == null || tabsStyle == TabStyler.TabsStyle.TAB_STYLE_SIMPLE)
            return;

        View custom_tab = null;

        switch (tabsStyle){
            case TAB_STYLE_IMAGE_ONLY:

                View ci = View.inflate(getContext(), R.layout.item_tab_with_image, null);
                ImageView icon = (ImageView) ci.findViewById(R.id.icon);
                icon.setImageResource(tab.getIcon());

                custom_tab = ci;

                break;

            case TAB_STYLE_IMAGE_WITH_TEXT:

                View ct = View.inflate(getContext(), R.layout.item_tab_with_image_and_text, null);
                ImageView icon1 = (ImageView) ct.findViewById(R.id.icon);
                TextView title = (TextView) ct.findViewById(R.id.title);
                icon1.setImageResource(tab.getIcon());
                title.setText(tab.getTitle());

                custom_tab = ct;

                break;

            case TAB_STYLE_DIY:

                custom_tab = customTabLayout(tab);

                break;
        }

        if(custom_tab == null)
            return;

        custom_tab.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        tabLayout.getTabAt(position).setCustomView(custom_tab);

    }

    /**
     * Override this method when using DIY styling ignore otherwise
     * This method will be called for each tab
     * <br>
     * This is how a normal tab would look like
     * @param i the tab position
     */
    protected void normalTabDesign(int i){

        ((ImageView) (tabLayout.getTabAt(i).getCustomView().findViewById(R.id.icon))).setColorFilter(ContextCompat.getColor(getContext(),tabStyler.tab_image_tint));
        if(tabStyler.tabsStyle == TabsStyle.TAB_STYLE_IMAGE_WITH_TEXT)
            ((TextView) (tabLayout.getTabAt(i).getCustomView().findViewById(R.id.title))).setTextColor(getResources().getColor(tabStyler.tab_text_color));
        tabLayout.getTabAt(i).getCustomView().findViewById(R.id.icon).setScaleY(1.0f);
        tabLayout.getTabAt(i).getCustomView().findViewById(R.id.icon).setScaleX(1.0f);
    }

    /**
     * Override this method when using DIY styling ignore otherwise
     * <br>
     * This is how a highlighted tab would look like
     * @param i the tab number to be highlighted
     */
    protected void highlightTabDesign(int i){

        ((ImageView) (tabLayout.getTabAt(i).getCustomView().findViewById(R.id.icon))).setColorFilter(ContextCompat.getColor(getContext(), tabStyler.tab_image_highlight_tint));
        if(tabStyler.tabsStyle == TabsStyle.TAB_STYLE_IMAGE_WITH_TEXT)
            ((TextView) (tabLayout.getTabAt(i).getCustomView().findViewById(R.id.title))).setTextColor(getResources().getColor(tabStyler.tab_text_highlight_color));
        tabLayout.getTabAt(i).getCustomView().findViewById(R.id.icon).setScaleY(tabStyler.tab_highlight_scale);
        tabLayout.getTabAt(i).getCustomView().findViewById(R.id.icon).setScaleX(tabStyler.tab_highlight_scale);
    }

    private void setTabColors(int position, ArrayList<TabData> tabs){

        if(tabStyler.tabsStyle == null || tabStyler.tabsStyle == TabsStyle.TAB_STYLE_SIMPLE)
            return;

        for (int i = 0; i < tabs.size(); i++) {
            if (i == position)
               highlightTabDesign(i);
            else
               normalTabDesign(i);
        }
    }

    /**
     * Gets the tab layout of the fragment / activity
     * @return the tablayout
     */
    public TabLayout getTabLayout() {
        return tabLayout;
    }

}
