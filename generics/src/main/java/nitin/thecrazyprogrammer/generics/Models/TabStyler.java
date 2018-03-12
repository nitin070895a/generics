package nitin.thecrazyprogrammer.generics.Models;

import android.content.Context;

import nitin.thecrazyprogrammer.common.R;
import nitin.thecrazyprogrammer.common.ThemeHelper;

/**
 * Created by Nitin Khurana on 1/24/2018.
 */
public class TabStyler {

    public enum TabsGravity{
        TABS_GRAVITY_TOP,
        TABS_GRAVITY_BOTTOM
    }

    public enum TabsStyle{
        TAB_STYLE_SIMPLE,
        TAB_STYLE_IMAGE_ONLY,
        TAB_STYLE_IMAGE_WITH_TEXT,
        TAB_STYLE_DIY

    }

    public TabsGravity tabGravity = TabsGravity.TABS_GRAVITY_TOP;
    public TabsStyle tabsStyle = TabsStyle.TAB_STYLE_SIMPLE;

    public int tab_layout_background = R.color.colorPrimary;
    public int tab_indicator_color = R.color.colorAccent;
    public int tab_text_color = R.color.white;
    public int tab_image_tint = R.color.white;
    public int tab_text_highlight_color = R.color.colorAccent;
    public int tab_image_highlight_tint = R.color.colorAccent;

    public float tab_highlight_scale = 1.2f;

    public TabStyler(Context context){
        ThemeHelper themeHelper = new ThemeHelper();

        tab_layout_background = themeHelper.fetchPrimaryColor(context);
        tab_indicator_color = themeHelper.fetchAccentColor(context);
        tab_text_color = context.getResources().getColor(R.color.white);
        tab_image_tint = context.getResources().getColor(R.color.white);
        tab_text_highlight_color = themeHelper.fetchAccentColor(context);
        tab_image_highlight_tint = themeHelper.fetchAccentColor(context);
    }

}
