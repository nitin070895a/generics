package nitin.thecrazzyprogrammer.generics.Fragments;

import android.os.Handler;

/**
 * Created by Nitn Khurana on 1/22/2018.
 *
 * <p>Only loads when visible to user
 * Should be used for fragments fed to Tabbed Fragment or Fragments in a tab layout using FragmentPagerAdapter in general
 * </p>
 */
public abstract class VisibleLoadingFragment extends LoadingFragment{

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if(isVisibleToUser && !isLoaded){

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    load();
                }
            }, 50);
        }
    }

    @Override
    protected final void load() {
        if(!isVisible())
            return;
        super.load();
    }
}
