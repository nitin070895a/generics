package nitin.thecrazzyprogrammer.genericsexample.Fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import nitin.thecrazzyprogrammer.generics.Fragments.TabbedFragment;
import nitin.thecrazzyprogrammer.generics.Models.TabData;
import nitin.thecrazzyprogrammer.genericsexample.R;

/**
 * Created by Nitin Khurana on 1/21/2018.
 */
public class ExampleTabbedFragment extends TabbedFragment {

    ArrayList<TabData> tabs = new ArrayList<>();

    @Override
    protected void loadOrReload() {

        for(int i=1; i<=3; i++)
            tabs.add(new TabData("Tab " + i, new ExampleVisibleLoadingFragment()));

        setUpTabs(tabs); // if you want to initialize after an API call then create an API call request here and call this method in OnPost of the API Request
    }

    @Override
    protected void setStyleProperties() {

    }

    @Override
    protected View customTabLayout(TabData tab) {

        View ct = View.inflate(getContext(), nitin.thecrazzyprogrammer.generics.R.layout.item_tab_with_image_and_text, null);
        ImageView icon1 = (ImageView) ct.findViewById(nitin.thecrazzyprogrammer.generics.R.id.icon);
        TextView title = (TextView) ct.findViewById(nitin.thecrazzyprogrammer.generics.R.id.title);
        icon1.setImageResource(tab.getIcon());
        title.setText(tab.getTitle());

        return ct;
    }

    @Override
    protected void normalTabDesign(int i) {
        super.normalTabDesign(i);

        TabLayout tabLayout = getTabLayout();

        ((ImageView) (tabLayout.getTabAt(i).getCustomView().findViewById(R.id.icon))).setColorFilter(ContextCompat.getColor(getContext(),tabStyler.tab_image_tint));
        ((TextView) (tabLayout.getTabAt(i).getCustomView().findViewById(R.id.title))).setTextColor(getResources().getColor(tabStyler.tab_text_color));
        tabLayout.getTabAt(i).getCustomView().findViewById(R.id.icon).setScaleY(1.0f);
        tabLayout.getTabAt(i).getCustomView().findViewById(R.id.icon).setScaleX(1.0f);
    }

    @Override
    protected void highlightTabDesign(int i) {
        super.highlightTabDesign(i);

        TabLayout tabLayout = getTabLayout();

        ((ImageView) (tabLayout.getTabAt(i).getCustomView().findViewById( nitin.thecrazzyprogrammer.generics.R.id.icon))).setColorFilter(ContextCompat.getColor(getContext(), tabStyler.tab_image_highlight_tint));
        ((TextView) (tabLayout.getTabAt(i).getCustomView().findViewById( nitin.thecrazzyprogrammer.generics.R.id.title))).setTextColor(getResources().getColor(tabStyler.tab_text_highlight_color));
        tabLayout.getTabAt(i).getCustomView().findViewById( nitin.thecrazzyprogrammer.generics.R.id.icon).setScaleY(tabStyler.tab_highlight_scale);
        tabLayout.getTabAt(i).getCustomView().findViewById( nitin.thecrazzyprogrammer.generics.R.id.icon).setScaleX(tabStyler.tab_highlight_scale);

    }
}
