package nitin.thecrazyprogrammer.generics.Models;

import nitin.thecrazyprogrammer.generics.R;

/**
 * Created by Nitin Khurana on 2/4/2018.
 */
public class IntroScreenStyler {

    public enum IntroScreenType{
        INTRO_SCREEN_WITH_TITLE_AND_IMAGE,
        INTRO_SCREEN_WITH_TITLE_IMAGE_AND_DESC,
        INTRO_SCREEN_WITH_DESC_AND_IMAGE
    }

    public String finishText = "Launch App";
    public String skipText = "Skip";
    public String nextText = "Next";

    public int textColor = R.color.white;
    public int separatorColor = R.color.white;

    public int dotColor = R.color.white;
    public int dotHighLightColor = R.color.white;

    public IntroScreenType introScreenType = IntroScreenType.INTRO_SCREEN_WITH_TITLE_IMAGE_AND_DESC;
}
