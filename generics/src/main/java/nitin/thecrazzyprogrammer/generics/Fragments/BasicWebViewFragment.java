package nitin.thecrazzyprogrammer.generics.Fragments;

import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import nitin.thecrazzyprogrammer.generics.R;

/**
 * Created by Nitin Khurana on 1/19/2018.
 *
 * Common Fragment to be extended in the app where you want a simple fragment with just a webview to show some web content
 * Simply extend this fragment where ever you want to have only a webview in the fragment
 *
 * if you want to have an activity not a fragment use {@link BasicWebViewFragment} instead
 */
public abstract class BasicWebViewFragment extends BasicFragment{

    private ProgressBar progressBar;
    private WebView webView;

    @Override
    protected void onCreateView(View view) {

        progressBar = (ProgressBar) view.findViewById(R.id.progress_bar);
        webView = (WebView) view.findViewById(R.id.webview);

        setWebViewSettings();

        loadWebView(setURL(), setHtmlData());
    }

    @Override
    protected int setLayout() {
        return R.layout.basic_webview_fragment;
    }

    private void loadWebView(String url, String html_data){

        if(url == null && html_data == null)
            throw new NullPointerException();

        if(url == null)
            webView.loadData(html_data, "text/html; charset=utf-8", "UTF-8");
        else
            webView.loadUrl(url);
    }

    /**
     * Sets the webview settings
     */
    protected void setWebViewSettings(){

        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient(){

            @Override
            public void onPageFinished(WebView view, String url) {
                progressBar.setVisibility(View.GONE);
                super.onPageFinished(view, url);
            }
        });

        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setAppCacheEnabled(true);
        webView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
    }

    /**
     * Sets the url to be loaded in the webview
     * @return The url to load
     */
    protected abstract String setURL();

    protected abstract String setHtmlData();
}
