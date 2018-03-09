package nitin.thecrazyprogrammer.generics.Dialogs;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.animation.Interpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import nitin.thecrazyprogrammer.generics.R;
import nitin.thecrazyprogrammer.generics.Tools.IsTimeExpired;

/**
 * Created by Nitin Khurana on 8/30/2017.
 * <p>
 * The name is self explanatory this popup lets users to rate our app
 * This popup is smart if the user has already rated our app it won't show up
 * Further we can set a time interval after which the popup will be shown
 * <p>
 * It's not like we are forcing the user to rate our app, the popup will appear if he hasn't rated our app and the time interval is expired
 * By default the time interval is 5 days or 120:00:00 hrs but it can be changed from the setter method
 * <p>
 * Usage eg : new RateUsPopup(this,null or 24:00:00).show();
 */
public class RateUsPopup extends AlertDialog implements View.OnClickListener {

    public static final String HAS_USER_GIVEN_REVIEW = "has_user_given_review";
    public static final String RATE_US_POPUP_TIMESTAMP = "rate_us_popup_timestamp";

    private final String DEF_INTERVAL = "120:00:00";

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private AlertDialog dialog;
    private IsTimeExpired isTimeExpired;

    // show the popup or not
    private boolean show;

    private ImageView cross;
    private ImageView hand, stars;
    private Button feedback, rate;
    private TextView later, title;

    private Context context;
    private RateUsPopupListener rateUsPopupListener;

    private String str_title;

    /**
     * @param context                Context of your activity
     * @param title                  The title of the rate us
     * @param cancelable             Dialog cancel on touch outside
     * @param RATE_US_POPUP_INTERVAL Interval(String : hh:mm:ss) if known null otherwise
     * @param pref_name              The pref name where value of timestamp and already rated bool will be stored
     * @param rateUsPopupListener    Callbacks to any click events on the listener
     */
    public RateUsPopup(@NonNull final Context context, String title, boolean cancelable, final String RATE_US_POPUP_INTERVAL, String pref_name, RateUsPopupListener rateUsPopupListener) {

        super(context);
        this.context = context;
        this.rateUsPopupListener = rateUsPopupListener;
        this.str_title = title;

        sharedPreferences = context.getSharedPreferences(pref_name, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        isTimeExpired = new IsTimeExpired(context, pref_name);

        show = !sharedPreferences.getBoolean(HAS_USER_GIVEN_REVIEW, false)
                &&
                isTimeExpired.isThisTheTimeToShow(RATE_US_POPUP_TIMESTAMP, RATE_US_POPUP_INTERVAL == null ? DEF_INTERVAL : RATE_US_POPUP_INTERVAL);

        //show = true;
        if (show) {

            dialog = new AlertDialog.Builder(context)
                    .setCancelable(cancelable)
                    .setView(R.layout.rate_us_popup)
                    .create();

        }
    }

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.rate){
            dialog.dismiss();
            editor.putBoolean(HAS_USER_GIVEN_REVIEW, true).commit();

            //Play store link
            final String appPackageName = context.getPackageName();
            try {
                context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
            } catch (android.content.ActivityNotFoundException e) {
                context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
            }

            rateUsPopupListener.onRate();
        }

        if(v.getId() == R.id.cross){
            dialog.dismiss();
            rateUsPopupListener.onCrossed();
        }

        if(v.getId() == R.id.later){
            dialog.dismiss();
            rateUsPopupListener.onLater();
        }

        if(v.getId() == R.id.feedback){
            dialog.dismiss();
            rateUsPopupListener.onFeedback();
        }

    }

    @Override
    public void show() {

        if (show) {

            dialog.show();
            rate = (Button) dialog.findViewById(R.id.rate);
            feedback = (Button) dialog.findViewById(R.id.feedback);

            cross = (ImageView) dialog.findViewById(R.id.cross);
            stars = (ImageView) dialog.findViewById(R.id.imageView);
            hand = (ImageView) dialog.findViewById(R.id.hand);
            title = (TextView) dialog.findViewById(R.id.title);
            later = (TextView) dialog.findViewById(R.id.later);

            if(str_title != null)
                title.setText(str_title);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    try {

                        hand.setVisibility(View.VISIBLE);
                        translateAnimator(hand, 1000, 0, new LinearOutSlowInInterpolator(), 0, 0, stars.getX() + stars.getWidth() - hand.getWidth() , 0, 0);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }, 3000);

            rate.setOnClickListener(this);
            feedback.setOnClickListener(this);
            later.setOnClickListener(this);
            cross.setOnClickListener(this);
        }
    }

    private void translateAnimator(View view, int duration, int repeat, Interpolator interpolator, int offset,
                                   float fromx, float tox, float fromy, float toy) {

        TranslateAnimation animation = new TranslateAnimation(fromx, tox, fromy, toy);
        animation.setDuration(duration);
        animation.setRepeatCount(repeat);
        animation.setStartOffset(offset);
        animation.setFillAfter(true);
        animation.setInterpolator(interpolator);
        view.startAnimation(animation);
    }

    public interface RateUsPopupListener{

        void onRate();
        void onCrossed();
        void onFeedback();
        void onLater();
    }
}
