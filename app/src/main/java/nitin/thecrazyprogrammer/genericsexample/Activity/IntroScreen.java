package nitin.thecrazyprogrammer.genericsexample.Activity;

import android.content.Intent;

import java.util.ArrayList;

import nitin.thecrazyprogrammer.generics.Activities.IntroScreenActivity;
import nitin.thecrazyprogrammer.generics.Models.IntroScreenData;
import nitin.thecrazyprogrammer.generics.Models.IntroScreenStyler;
import nitin.thecrazyprogrammer.genericsexample.R;

/**
 * Created by Nitin Khurana on 2/4/2018.
 */
public class IntroScreen extends IntroScreenActivity{

    @Override
    protected ArrayList<IntroScreenData> setIntroScreens() {

        ArrayList<IntroScreenData> introScreenDatas = new ArrayList<>();

        introScreenDatas.add(new IntroScreenData(R.drawable.android, "Sample Title", "This is a sample Description", getResources().getColor(R.color.amber)));
        introScreenDatas.add(new IntroScreenData(R.drawable.android, "Sample Title", "This is a sample Description", getResources().getColor(R.color.teal)));
        introScreenDatas.add(new IntroScreenData(R.drawable.android, "Sample Title", "This is a sample Description", getResources().getColor(R.color.brown)));
        introScreenDatas.add(new IntroScreenData(R.drawable.android, "Sample Title", "This is a sample Description", getResources().getColor(R.color.deep_orange)));

        return introScreenDatas;
    }

    @Override
    protected void customizeIntroScreen(IntroScreenStyler introScreenStyler) {

        introScreenStyler.finishText = "Let's Go";
        introScreenStyler.introScreenType = IntroScreenStyler.IntroScreenType.INTRO_SCREEN_WITH_TITLE_IMAGE_AND_DESC;
    }

    @Override
    protected void onSkip() {
        onFinished();
    }

    @Override
    protected void onNext() {

    }

    @Override
    protected void onFinished() {

        startActivity(new Intent(this, HomeScreen.class));
        finish();
    }
}
