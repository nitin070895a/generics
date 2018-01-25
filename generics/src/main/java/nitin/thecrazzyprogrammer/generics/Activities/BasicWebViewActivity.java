package nitin.thecrazzyprogrammer.generics.Activities;


import nitin.thecrazzyprogrammer.generics.Fragments.BasicWebViewFragment;

/**
 * Created by Nitin Khurana on 7/21/2017.
 *
 * Common activity to be extended in the app where you want a simple activity with just a toolbar and a webview to show some web content
 * Simply extend this activity where ever you want to have only a webview in the activity
 *
 * if you want to have a fragment not an activity use {@link BasicWebViewFragment} instead
 */
public abstract class BasicWebViewActivity extends BasicFragmentActivity<BasicWebViewFragment>{

    static String url;
    static String html_data;

    @Override
    protected BasicWebViewFragment setFragment() {

        url = setURL();
        html_data = setHtmlData();
        return new ThisFragment();

    }

    /**
     * Sets the url to be loaded in the webview
     * @return The url to load
     */
    protected abstract String setURL();

    protected abstract String setHtmlData();

    public static class ThisFragment extends BasicWebViewFragment{

        @Override
        protected String setURL() {
            return url;
        }

        @Override
        protected String setHtmlData() {
            return html_data;
        }
    }
}
