package nitin.thecrazyprogrammer.generics.Activities;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import java.util.ArrayList;

import nitin.thecrazyprogrammer.generics.Adapters.IntroScreenAdapter;
import nitin.thecrazyprogrammer.generics.Models.IntroScreenData;
import nitin.thecrazyprogrammer.generics.Models.IntroScreenStyler;
import nitin.thecrazyprogrammer.generics.R;
import nitin.thecrazyprogrammer.generics.Tools.DotMaker;

/**
 * Created by Nitin Khurana on 2/4/2018.
 */
public abstract class IntroScreenActivity extends FullScreenActivity implements ViewPager.OnPageChangeListener{

    private ViewPager viewPager;
    private FrameLayout bottomView;
    private Button skip, next;
    private View separator;
    private LinearLayout dots_ll;

    private DotMaker dotMaker;

    private ArrayList<IntroScreenData> introScreenDatas = new ArrayList<>();
    private int totalPages = 0;
    private int currentPage = 0;

    private IntroScreenStyler introScreenStyler = new IntroScreenStyler();

    @Override
    protected final void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intro_screen);

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        bottomView = (FrameLayout) findViewById(R.id.bottomView);
        customizeIntroScreen(introScreenStyler);

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        bottomView.addView(inflater.inflate(R.layout.intro_bottom_view, null));

        skip = (Button) findViewById(R.id.skip);
        next = (Button) findViewById(R.id.next);
        separator = findViewById(R.id.separator);
        dots_ll = (LinearLayout) findViewById(R.id.dots_ll);

        skip.setText(introScreenStyler.skipText);
        next.setText(introScreenStyler.nextText);
        skip.setTextColor(getResources().getColor(introScreenStyler.textColor));
        next.setTextColor(getResources().getColor(introScreenStyler.textColor));
        separator.setBackgroundColor(getResources().getColor(introScreenStyler.separatorColor));

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSkip();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentPage == totalPages - 1)
                    onFinished();
                else{
                    viewPager.setCurrentItem(currentPage + 1);
                    onNext();
                }
            }
        });

        introScreenDatas = setIntroScreens();
        totalPages = introScreenDatas.size();

        dotMaker = new DotMaker(this, totalPages);
        dotMaker.setDot_tint(introScreenStyler.dotColor);
        dotMaker.setHighlight_dot_tint(introScreenStyler.dotHighLightColor);
        dotMaker.makeDots(dots_ll);

        viewPager.setAdapter(new IntroScreenAdapter(this, introScreenDatas, introScreenStyler.introScreenType));
        viewPager.addOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        dotMaker.bigDot(position, dots_ll);
    }

    @Override
    public void onPageSelected(int position) {
        currentPage = position;
        if(currentPage == totalPages - 1){
            next.setText(introScreenStyler.finishText);
            skip.setVisibility(View.INVISIBLE);
        }
        else{
            skip.setVisibility(View.VISIBLE);
            next.setText(introScreenStyler.nextText);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    protected void customizeIntroScreen(IntroScreenStyler introScreenStyler){

    }

    protected abstract ArrayList<IntroScreenData> setIntroScreens();

    protected abstract void onSkip();

    protected abstract void onNext();

    protected abstract void onFinished();
}