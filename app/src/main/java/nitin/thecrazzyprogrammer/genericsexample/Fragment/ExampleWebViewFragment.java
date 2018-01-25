package nitin.thecrazzyprogrammer.genericsexample.Fragment;

import nitin.thecrazzyprogrammer.generics.Fragments.BasicWebViewFragment;

/**
 * Created by Ntin Khurana on 1/19/2018.
 */
public class ExampleWebViewFragment extends BasicWebViewFragment {

    @Override
    protected String setURL() {
        return "www.google.com";
    }

    @Override
    protected String setHtmlData() {
        return null;
    }
}
