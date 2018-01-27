package nitin.thecrazyprogrammer.genericsexample.Fragment;

import nitin.thecrazyprogrammer.generics.Fragments.BasicWebViewFragment;

/**
 * Created by Ntin Khurana on 1/19/2018.
 */
public class ExampleWebViewFragment extends BasicWebViewFragment {

    @Override
    protected String setURL() {
        return "www.google.com";
    }

    // use this if you have html already and you don't want to load anything from the web (return null from setUrl())
    @Override
    protected String setHtmlData() {
        return "<p>This is a webview example</p>";
    }

}
