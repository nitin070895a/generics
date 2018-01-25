package nitin.thecrazzyprogrammer.genericsexample.Activity;

import nitin.thecrazzyprogrammer.generics.Activities.BasicWebViewActivity;

/**
 * Created by Nitin Khurana on 1/19/2018.
 */
public class ExampleWebViewActivity extends BasicWebViewActivity {

    @Override
    protected String setURL() {
        return "www.google.com";
    }

    @Override
    protected String setHtmlData() {
        return null;
    }

    @Override
    protected String setActivityTitle() {
        return "Example Web View Activity";
    }
}
