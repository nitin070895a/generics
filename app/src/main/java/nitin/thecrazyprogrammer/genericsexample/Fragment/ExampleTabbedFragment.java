package nitin.thecrazyprogrammer.genericsexample.Fragment;

import android.annotation.SuppressLint;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import nitin.thecrazyprogrammer.generics.Fragments.TabbedFragment;
import nitin.thecrazyprogrammer.generics.Models.TabData;
import nitin.thecrazyprogrammer.generics.Models.TabStyler;
import nitin.thecrazyprogrammer.genericsexample.R;

/**
 * Created by Nitin Khurana on 1/21/2018.
 */
public class ExampleTabbedFragment extends TabbedFragment {

    ArrayList<TabData> tabs = new ArrayList<>();

    @Override
    protected void loadOrReload() {

        for(int i=1; i<=3; i++)
            tabs.add(new TabData("Tab " + i, android.R.drawable.sym_def_app_icon ,new ExampleVisibleLoadingFragment()));

        setUpTabs(tabs); // if you want to initialize after an API call then create an API call request here and call this method in OnPost of the API Request
    }

    @SuppressLint("ResourceAsColor")
    @Override
    protected void setStyleProperties() {

        tabStyler.tab_highlight_scale = 1.2f;
        tabStyler.tabGravity = TabStyler.TabsGravity.TABS_GRAVITY_TOP;
        tabStyler.tabsStyle = TabStyler.TabsStyle.TAB_STYLE_IMAGE_WITH_TEXT;
    }

    // override for only tabStyle.DIY
    @Override
    protected View customTabLayout(TabData tab) {

        if(tabStyler.tabsStyle != TabStyler.TabsStyle.TAB_STYLE_DIY)
            super.customTabLayout(tab);

        View ct = View.inflate(getContext(), nitin.thecrazyprogrammer.generics.R.layout.item_tab_with_image_and_text, null);
        ImageView icon1 = (ImageView) ct.findViewById(nitin.thecrazyprogrammer.generics.R.id.icon);
        TextView title = (TextView) ct.findViewById(nitin.thecrazyprogrammer.generics.R.id.title);
        icon1.setImageResource(tab.getIcon());
        title.setText(tab.getTitle());

        return ct;
    }

    // override for only tabStyle.DIY
    @Override
    protected void normalTabDesign(int i) {
        super.normalTabDesign(i);

        if(tabStyler.tabsStyle != TabStyler.TabsStyle.TAB_STYLE_DIY)
            return;

        TabLayout tabLayout = getTabLayout();

        ((ImageView) (tabLayout.getTabAt(i).getCustomView().findViewById(R.id.icon))).setColorFilter(ContextCompat.getColor(getContext(),tabStyler.tab_image_tint));
        ((TextView) (tabLayout.getTabAt(i).getCustomView().findViewById(R.id.title))).setTextColor(getResources().getColor(tabStyler.tab_text_color));
        tabLayout.getTabAt(i).getCustomView().findViewById(R.id.icon).setScaleY(1.0f);
        tabLayout.getTabAt(i).getCustomView().findViewById(R.id.icon).setScaleX(1.0f);
    }

    // override for only tabStyle.DIY
    @Override
    protected void highlightTabDesign(int i) {
        super.highlightTabDesign(i);

        if(tabStyler.tabsStyle != TabStyler.TabsStyle.TAB_STYLE_DIY)
            return;

        TabLayout tabLayout = getTabLayout();

        ((ImageView) (tabLayout.getTabAt(i).getCustomView().findViewById( nitin.thecrazyprogrammer.generics.R.id.icon))).setColorFilter(ContextCompat.getColor(getContext(), tabStyler.tab_image_highlight_tint));
        ((TextView) (tabLayout.getTabAt(i).getCustomView().findViewById( nitin.thecrazyprogrammer.generics.R.id.title))).setTextColor(getResources().getColor(tabStyler.tab_text_highlight_color));
        tabLayout.getTabAt(i).getCustomView().findViewById( nitin.thecrazyprogrammer.generics.R.id.icon).setScaleY(tabStyler.tab_highlight_scale);
        tabLayout.getTabAt(i).getCustomView().findViewById( nitin.thecrazyprogrammer.generics.R.id.icon).setScaleX(tabStyler.tab_highlight_scale);

    }
}
