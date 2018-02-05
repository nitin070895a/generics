package nitin.thecrazyprogrammer.generics.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import nitin.thecrazyprogrammer.generics.R;

/**
 * Created by Nitin Khurana on 2/4/2018.
 */
public abstract class SplashScreenActivity extends FullScreenActivity{

    private ImageView splash_background;
    protected int LAUNCH_THRESHOLD = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        splash_background = (ImageView) findViewById(R.id.splash_background);
        int background = setSplashBackground();
        if(background != 0)
            splash_background.setImageResource(background);

        customizeSplashScreen();
    }

    @Override
    public final void onWindowFocusChanged(boolean hasFocus) {

        if (!hasFocus)
            return;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(launchIntent(getIntent()));
                finish();
            }
        }, LAUNCH_THRESHOLD);

        super.onWindowFocusChanged(hasFocus);
    }

    protected void customizeSplashScreen(){

    }

    protected abstract int setSplashBackground();

    protected abstract Intent launchIntent(Intent splashScreenLaunchIntent);
}
