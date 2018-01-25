package nitin.thecrazzyprogrammer.generics.Fragments;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
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
        tabLayout.setTabTextColors(tabStyler.tab_text_color, tabStyler.tab_text_highlight_color);
        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(tabStyler.tab_indicator_color));

        fab = (FloatingActionButton) view.findViewById(R.id.fab);

        applyGravity(tabStyler.tabGravity);

    }

    protected void setUpTabs(final ArrayList<TabData> tabs){

        loadComplete(true);

        viewPager.setOffscreenPageLimit(tabs.size() - 1);
        viewPager.setAdapter(new TabLayoutAdapter(getActivity().getSupportFragmentManager(), tabs));

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

    protected void showFab(){
        fab.show();
    }

    protected void hideFab(){
        fab.hide();
    }

    protected void setFab(int icon, View.OnClickListener onClickListener){
        if(icon != 0)
            fab.setImageResource(icon);
        fab.setVisibility(View.VISIBLE);
        fab.setOnClickListener(onClickListener);
    }

    protected abstract void setStyleProperties();

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

    protected void normalTabDesign(int i){

        ((ImageView) (tabLayout.getTabAt(i).getCustomView().findViewById(R.id.icon))).setColorFilter(ContextCompat.getColor(getContext(),tabStyler.tab_image_tint));
        if(tabStyler.tabsStyle == TabsStyle.TAB_STYLE_IMAGE_WITH_TEXT)
            ((TextView) (tabLayout.getTabAt(i).getCustomView().findViewById(R.id.title))).setTextColor(getResources().getColor(tabStyler.tab_text_color));
        tabLayout.getTabAt(i).getCustomView().findViewById(R.id.icon).setScaleY(1.0f);
        tabLayout.getTabAt(i).getCustomView().findViewById(R.id.icon).setScaleX(1.0f);
    }

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

    public TabLayout getTabLayout() {
        return tabLayout;
    }

}
