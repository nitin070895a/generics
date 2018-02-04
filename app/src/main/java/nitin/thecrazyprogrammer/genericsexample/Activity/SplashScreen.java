package nitin.thecrazyprogrammer.genericsexample.Activity;

import android.content.Intent;

import nitin.thecrazyprogrammer.generics.Activities.SplashScreenActivity;
import nitin.thecrazyprogrammer.genericsexample.R;

/**
 * Created by Nitin Khurana on 2/4/2018.
 */
public class SplashScreen extends SplashScreenActivity{

    @Override
    protected int setSplashBackground() {
        return R.drawable.splash_back;
    }

    @Override
    protected Intent launchIntent(Intent splashScreenLaunchIntent) {
        return new Intent(SplashScreen.this, HomeScreen.class);
    }

    @Override
    protected void customizeSplashScreen() {
        LAUNCH_THRESHOLD = 500;
    }
}
