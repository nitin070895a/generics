package nitin.thecrazzyprogrammer.genericsexample.Activity;

import nitin.thecrazzyprogrammer.generics.Activities.BasicWebViewActivity;

/**
 * Created by Nitin Khurana on 1/19/2018.
 */
public class ExampleWebViewActivity extends BasicWebViewActivity {

    @Override
    protected String setURL() {
        return "https://www.google.com";
    }

    // use this if you have html already and you don't want to load anything from the web (return null from setUrl())
    @Override
    protected String setHtmlData() {
        return "<p>This is a webview example</p>";
    }

    @Override
    protected String setActivityTitle() {
        return "Example Web View Activity";
    }

}
