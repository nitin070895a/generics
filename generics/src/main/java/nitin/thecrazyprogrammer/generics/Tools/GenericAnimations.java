package nitin.thecrazyprogrammer.generics.Tools;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.Interpolator;
import android.view.animation.OvershootInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.Transformation;
import android.view.animation.TranslateAnimation;
import android.widget.RelativeLayout;
import android.widget.TextView;

import nitin.thecrazyprogrammer.generics.R;

public class GenericAnimations {

    // for changing texts in Resource Intro Fragment
    float count = 0;
    Context context;

    public GenericAnimations(Context context){
        this.context = context;
    }

    /**
     * Roatate the view using ObjectAnimator
     *
     * @param view         the view to be rotated
     * @param duration     the duration of the rotation
     * @param repeat       no of times the animation will be repeated (Animation.INFINITE = forever, 0 = for once, 1 = twice and so on)
     * @param interpolator the type of physics the view will be treated with
     * @param from         the degree from which to start the rotation (same for both X and Y axis)
     * @param to           the final position of the view in degrees (same for both X and Y axis)
     */
    public void objectAnimator(View view, int duration, int repeat, Interpolator interpolator,
                               float from, float to) {

        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "rotationY", from, to);
        animator.setRepeatCount(repeat);
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(view, "rotationX", from, to);
        animator1.setRepeatCount(repeat);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(animator, animator1);
        animatorSet.setDuration(duration);
        animatorSet.setInterpolator(interpolator);

        animatorSet.start();

    }

    /**
     * Rotate a view using Rotate Animaton
     *
     * @param view         the view to be animated
     * @param duration     the duration of the rotation
     * @param repeat       no of times the animation will be repeated (Animation.INFINITE = forever, 0 = for once, 1 = twice and so on)
     * @param interpolator the type of physics the view will be treated with
     * @param from         the degree from which to start the rotation (same for both X and Y axis)
     * @param to           the final position of the view in degrees (same for both X and Y axis)
     * @param relative_to  rotate with respect to (ABSOLUTE = pixels on screen, SELF = itself, PARENT = its parent)
     * @param pivotx       the pivot of the rotation in X axis
     * @param pivoty       the pivot of the rotation in Y axis
     */
    public void rotateAnimator(View view, int duration, int repeat, Interpolator interpolator,
                               float from, float to, int relative_to, float pivotx, float pivoty) {

        RotateAnimation animation = new RotateAnimation(from, to, relative_to, pivotx, relative_to, pivoty);
        animation.setInterpolator(interpolator);
        animation.setDuration(duration);
        animation.setRepeatCount(repeat);
        view.startAnimation(animation);
    }

    /**
     * Moves one view from one point to other using TranslateAnimation
     *
     * @param view         the view to be translated
     * @param duration     the duration of the translation
     * @param repeat       no of times the animation will be repeated (Animation.INFINITE = forever, 0 = for once, 1 = twice and so on)
     * @param interpolator the type of physics the view will be treated with
     * @param offset       the time after which the animation starts
     * @param fromx        from delta x (position of the starting X point wrt its current X position)
     * @param tox          to final delta x (position of the final X point wrt its current X position)
     * @param fromy        from delta Y (position of the starting Y point wrt its current Y position)
     * @param toy          to final delta Y (position of the final Y point wrt its current Y position)
     */
    public void translateAnimator(View view, int duration, int repeat, Interpolator interpolator, int offset,
                                  float fromx, float tox, float fromy, float toy) {

        TranslateAnimation animation = new TranslateAnimation(fromx, tox, fromy, toy);
        animation.setDuration(duration);
        animation.setRepeatCount(repeat);
        animation.setStartOffset(offset);
        animation.setFillAfter(true);
        animation.setInterpolator(interpolator);
        view.startAnimation(animation);
    }

    /**
     * Scale up or down a view
     *
     * @param view         the view to be scaled
     * @param duration     the duration of the scaling animation
     * @param repeat       no of times the animation will be repeated (Animation.INFINITE = forever, 0 = for once, 1 = twice and so on)
     * @param interpolator the type of physics the view will be treated with
     * @param offset       the time after which the animation starts
     * @param from         from delta x (position of the starting X point wrt its current X position)
     * @param to           to final delta x (position of the final X point wrt its current X position)
     * @param centered     whether to scale from center or not (if true the view is scaled from its center, else scaled from it's top-left)
     * @return the ScaleAnimationObject that can be applied to the view
     * <p>
     * Usage :
     * myView.startAnimation(animationHelper.scaleAnimation(.....));
     */
    public ScaleAnimation scaleAnimation(final View view, int duration, int repeat, Interpolator interpolator, int offset,
                                         float from, float to, boolean centered) {

        ScaleAnimation scaleAnimation;
        if (centered)
            scaleAnimation = new ScaleAnimation(from, to, from, to, Animation.ABSOLUTE, view.getWidth() / 2, Animation.ABSOLUTE, view.getHeight() / 2);
        else
            scaleAnimation = new ScaleAnimation(from, to, from, to);

        scaleAnimation.setDuration(duration);
        scaleAnimation.setStartOffset(offset);
        scaleAnimation.setRepeatCount(repeat);
        scaleAnimation.setFillAfter(true);
        scaleAnimation.setInterpolator(interpolator);

        view.setVisibility(View.INVISIBLE);

        scaleAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                view.setVisibility(View.VISIBLE);

            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


        return scaleAnimation;
    }

    /**
     * Produce a typewriting effect on a textView
     *
     * @param textView the textView to be animated
     * @param duration the duration of the animation
     * @param offset   the time after which the animation starts
     * @param speed    the reverse speed of the writing
     *                 (speed = 10 is faster than speed = 20, lesser the faster)
     */
    public void typeWriterAnimation(final TextView textView, final int duration, int offset, final int speed) {

        final CountDownTimer countDownTimer = new CountDownTimer(duration, speed) {
            @Override
            public void onTick(long millisUntilFinished) {

                String a = textView.getText().toString();
                a += "~";
                textView.setText(a);

            }

            @Override
            public void onFinish() {

            }
        };

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                countDownTimer.start();
            }
        }, offset);

    }

    // for the cardviews of tests, assignment and classes
    public void integerChangeAnimation(final TextView textView, int offset, final int
            step, final String append) {

        final CountDownTimer countDownTimer = new CountDownTimer(800, 80) {
            @Override
            public void onTick(long millisUntilFinished) {

                textView.setText(String.valueOf(Integer.parseInt(textView.getText().toString().substring(0, 2)) + step )
                        + append);
            }

            @Override
            public void onFinish() {

            }
        };

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                countDownTimer.start();
            }
        }, offset);

    }

    // for the total points textview
    public void integerChangeAnimationTotalPoints(final TextView textView, int offset, final int
            step) {

        final CountDownTimer countDownTimer = new CountDownTimer(1000, 40) {
            @Override
            public void onTick(long millisUntilFinished) {

                textView.setText(String.valueOf(Integer.parseInt(textView.getText().toString()) + step));
            }

            @Override
            public void onFinish() {

            }
        };

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                countDownTimer.start();
            }
        }, offset);

    }


    /**
     * Apply fading effects to the view
     *
     * @param view         the view to be animated
     * @param duration     the duration of the ainmation
     * @param repeat       no of times the animation will be repeated (Animation.INFINITE = forever, 0 = for once, 1 = twice and so on)
     * @param interpolator the type of physics the view will be treated with
     * @param from         from alpha (1f = completely opaque, 0f = completely transparent)
     * @param to           to aplha (0f-1f)
     *                     <p>
     *                     eg: for fade IN : (from = 0.0f to 1.0f)
     *                     for fade OUT : (from = 1.0f to 0.0f)
     */
    public void alphaAnimator(View view, int duration, int repeat, Interpolator interpolator,
                              float from, float to) {

        AlphaAnimation animation = new AlphaAnimation(from, to);
        animation.setInterpolator(interpolator);
        animation.setDuration(duration);
        animation.setFillAfter(true);
        animation.setStartOffset(800);
        animation.setRepeatMode(Animation.REVERSE);
        animation.setRepeatCount(repeat);
        view.startAnimation(animation);

    }

    /**
     * Animation Specifically for Animating layout for a resource in Resource Fragment of IntroScreen
     *
     * @param view         the view to be animated
     * @param duration     the duration of the animation
     * @param repeat       no of times the animation will be repeated (Animation.INFINITE = forever, 0 = for once, 1 = twice and so on)
     * @param interpolator the type of physics the view will be treated with
     */
    public void translateAndZoom(final View view, int duration, int repeat, Interpolator interpolator) {

        float x1, x2, x3;

        x1 = ( ((AppCompatActivity)context).getWindowManager().getDefaultDisplay().getWidth() / 2) - (view.getWidth() / 2);
        x2 = 0;
        x3 = -x1;

        int type = Animation.ABSOLUTE;

        TranslateAnimation translateAnimation = new TranslateAnimation(x1, x2, 0, 0);
        translateAnimation.setDuration(duration);
        translateAnimation.setInterpolator(interpolator);
        ScaleAnimation scaleAnimation = new ScaleAnimation(0.2f, 1.0f, 0.2f, 1.0f, ScaleAnimation.RELATIVE_TO_SELF,
                .5f, ScaleAnimation.RELATIVE_TO_SELF, .5f);
        scaleAnimation.setDuration(duration);

        TranslateAnimation translateAnimation1 = new TranslateAnimation(x2, x3, 0, 0);
        translateAnimation1.setDuration(duration);
        translateAnimation1.setStartOffset(duration);
        translateAnimation1.setInterpolator(interpolator);
        ScaleAnimation scaleAnimation1 = new ScaleAnimation(1.0f, 0.2f, 1.0f, 0.2f,
                ScaleAnimation.RELATIVE_TO_SELF, .5f, ScaleAnimation.RELATIVE_TO_SELF, .5f);
        scaleAnimation1.setDuration(duration);
        scaleAnimation1.setStartOffset(duration);

        AnimationSet animationSet = new AnimationSet(true);
        animationSet.addAnimation(translateAnimation);
        animationSet.addAnimation(scaleAnimation);
        animationSet.addAnimation(translateAnimation1);
        animationSet.addAnimation(scaleAnimation1);
        animationSet.setInterpolator(interpolator);
        animationSet.setRepeatMode(Animation.RESTART);
        animationSet.setRepeatCount(repeat);
        animationSet.setFillAfter(false);
        animationSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                Log.e("VIAS", "start");
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                view.startAnimation(animation);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        view.startAnimation(animationSet);
    }

    public void translateAndZoom1(final View view, int duration, int repeat, Interpolator interpolator, int offset, int stayoffset) {

        float y = view.getTop();
        float x2 = 100;
        float x1 = view.getWidth() - x2;
/*
        Path path1 = makeArc(x1,y,x1-1,y,x1-2,y,x2,y,0);
        Path path2 = makeArc(x2,y,x2-1,y,x2-2,y,-x1,y,0);

        final AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(pathAnimation(view,duration,repeat,interpolator,path1,offset,1.0f,0,2,0.0f),
                pathAnimation(view,duration,repeat,interpolator,path2,stayoffset,1.0f,0,1,0.0f)
                );

        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                animation.start();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animatorSet.start();
*/
        view.setScaleX(0.2f);
        view.setScaleY(0.2f);
        view.setX(x1);


        view.animate().setDuration(duration).setStartDelay(offset).setInterpolator(interpolator)
                .scaleX(1.0f).scaleY(1.0f).translationX(x2).start();

        view.animate().setDuration(duration).setStartDelay(offset + 1000).setInterpolator(interpolator)
                .scaleX(0.2f).scaleY(0.2f).translationX(x1).start();
    }

    /**
     * Animation to slide in texts to the screen
     * Texts start from a position based on the parameters passed
     * stays in the center of the screen for (duration) time
     * leaves the screen afterwards
     *
     * @param view         the TextView to be animated
     * @param duration     the duration of the animation
     * @param repeat       no of times the animation will be repeated (Animation.INFINITE = forever, 0 = for once, 1 = twice and so on)
     * @param offset       the time in milliseconds after which the animation will be started
     * @param interpolator the type of physics the view will be treated with
     * @param stay_time    the time the textView will stay on the screen (after the animation ends and the next animation starts)
     * @param left         whether to start from left and end up at right or reverse of that
     *                     if true = text starts from left stays in the middle and endup to the right of the screen
     *                     if false = start from right stays in the middle and end up to the left of the screen
     */
    public void slideTexts(final TextView view, int duration, final int repeat, int offset, Interpolator interpolator, int stay_time,
                           boolean left) {

        float x1, x2, x3;
        x1 = -(view.getLeft() + view.getWidth());
        x2 = 0;
        x3 = view.getRight() + view.getWidth();

        final String titles[] = {
                "Current Affairs",
                "Infographics",
                "Talks and Interviews",
                "Topper's Answer Copy",
                "All India Radio",
                "Value Added Material"
        };

        TranslateAnimation translateAnimation1, translateAnimation2;
        if (left) {
            translateAnimation1 = new TranslateAnimation(x1, x2, 0, 0);
            translateAnimation2 = new TranslateAnimation(x2, x3, 0, 0);
        } else {
            translateAnimation1 = new TranslateAnimation(x3, x2, 0, 0);
            translateAnimation2 = new TranslateAnimation(x2, x1, 0, 0);
        }

        translateAnimation1.setDuration(duration);
        translateAnimation1.setStartOffset(offset);
        translateAnimation1.setFillBefore(false);
        translateAnimation1.setInterpolator(interpolator);
        translateAnimation2.setDuration(duration);
        translateAnimation2.setFillAfter(true);
        translateAnimation2.setStartOffset(stay_time);

        AlphaAnimation alphaAnimation1 = new AlphaAnimation(0.3f, 1f);
        AlphaAnimation alphaAnimation2 = new AlphaAnimation(1f, 0.3f);

        alphaAnimation1.setDuration(duration);
        alphaAnimation1.setStartOffset(offset);
        alphaAnimation2.setDuration(duration);
        alphaAnimation2.setStartOffset(stay_time);

        final AnimationSet animationSet = new AnimationSet(false);
        animationSet.addAnimation(translateAnimation1);
        animationSet.addAnimation(translateAnimation2);
        animationSet.addAnimation(alphaAnimation1);
        animationSet.addAnimation(alphaAnimation2);

        animationSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                view.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                view.setText(titles[(int) count]);
                count += 0.5f;
                if (count > 5)
                    count = 0;
                view.setVisibility(View.INVISIBLE);
                view.startAnimation(animationSet);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        view.startAnimation(animationSet);
    }

    /**
     * Slideshow animation
     * Animate between the slides passed as array of drawables
     *
     * @param slide_duration the time in milliseconds the slide will stay on the screen
     * @param drawables      the array of slides (as drawables)
     * @return returns the AnimationDrawable that can be applied to the view that you want to animate
     * eg : view.setImageDrawable(animationHelper.imageSlideAnimaton(... , ...));
     */
    public AnimationDrawable imageSlideAnimaton(int slide_duration, Drawable[] drawables) {

        AnimationDrawable animationDrawable = new AnimationDrawable();
        for (int i = 0; i < drawables.length; i++)
            animationDrawable.addFrame(drawables[i], slide_duration);


        animationDrawable.setOneShot(false);
        animationDrawable.start();

        return animationDrawable;
    }

    /**
     * Move one view from one point to the other over the path given in the parameters
     * path can be anything : circle,curve,arc,line,rectangle,ellipse ...
     *
     * @param view         the view to be animated
     * @param duration     the duration of the animation
     * @param repeat       no of times the animation will be repeated (Animation.INFINITE = forever, 0 = for once, 1 = twice and so on)
     * @param interpolator the type of physics the view will be treated with
     * @param path         the path over which the view will be moved
     * @param offset       the time after which the animation will start
     *                     <p>
     *                     below : only for circle zoom animation in the Resouce fragment of the Intro Screen
     *                     if don't know give these parameters as :
     *                     <p>
     *                     delta = 1.0f;
     *                     times = 0;
     *                     type =  0;
     *                     deviation = 0.0f;
     * @param delta        the fraction of the animation (eg : 0.2f in circular path will animate to 0 to 72 degrees i.e 1/5 of the circle)
     * @param times        the no of sections after which the animation starts (eg : in cirlcezoomanima... 1 means 72 to 144 degrees)
     * @param type         1 = scale up, 2 = scale down or 0 = no scale
     * @param deviation    the devation in the animation eg. in circlezoomanima... for 67 degree animation deviation would be 0.0695 (5 degrees or 1*5/72)
     * @return the path animator object that will be applied to the animation
     */
    public ValueAnimator pathAnimation(final View view, int duration, int repeat, Interpolator interpolator,
                                       final Path path, int offset,
                                       // the below parameters only for circle zoom animation in the Resource fragment of the Intro Screen
                                       final float delta, final int times, final int type, final float deviation) {

        ValueAnimator pathAnimator = ValueAnimator.ofFloat(-0.0f, 1.0f);
        pathAnimator.setDuration(duration);
        pathAnimator.setRepeatCount(repeat);
        pathAnimator.setRepeatMode(ValueAnimator.RESTART);
        pathAnimator.setInterpolator(interpolator);
        pathAnimator.setStartDelay(offset);
        //view.setVisibility(View.VISIBLE);


        pathAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            float[] point = new float[2];

            PathMeasure pathMeasure = new PathMeasure(path, true);

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                // Gets the animated float fraction
                float val;
                val = (delta * (times - deviation)) + animation.getAnimatedFraction() * delta;

                // Gets the point at the fractional path length

                pathMeasure.getPosTan((pathMeasure.getLength() * val), point, null);
                // Sets view location to the above point
                view.setX(point[0]);
                view.setY(point[1]);

                if (type == 1) {
                    view.setScaleX((float) (1.0f + (delta * 2.5f * animation.getAnimatedFraction())));
                    view.setScaleY((float) (1.0f + (delta * 2.5f * animation.getAnimatedFraction())));
                } else if (type == 2) {
                    view.setScaleX((float) (1.0f + delta - (delta * 1.0f * animation.getAnimatedFraction())));
                    view.setScaleY((float) (1.0f + delta - (delta * 1.0f * animation.getAnimatedFraction())));
                }
            }

        });

        pathAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                //view.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        return pathAnimator;
    }

    /**
     * Make a path between the specified points
     *
     * @param x      the starting point of the path X
     * @param y      the starting point of the path Y
     * @param x2     a point in between the starting point X and ending point X
     * @param y2     a point in between the starting point Y and ending point Y
     * @param x3     a point in between the starting point X and ending point X
     * @param y3     a point in between the starting point Y and ending point Y
     * @param x4     the final point of the path X
     * @param y4     the final point of the path Y
     * @param radius the radius of the arc of the circle (obsolete) ~ can be anything
     * @return the path that will be passed to the pathAnimation(...)
     */
    public Path makeArc(float x, float y, float x2, float y2, float x3, float y3, float x4, float y4, float radius) {
        Path path = new Path();

        path.moveTo(x, y);
        path.cubicTo(x2, y2, x3, y3, x4, y4);
        /*
        final RectF oval = new RectF();
        oval.set(x - radius, y - radius, x + radius, y+ radius);
        Path myPath = new Path();
        myPath.arcTo(oval, startAngle, -(float) sweepAngle, true);
        */
        return path;
    }

    /**
     * Animation specifically for rotating resources in the Resource Fragment of the Intro Screen
     * Makes a circular path based on the parameters passed and apply several path animations to the views passed
     *
     * @param center the view that is in the center (should be invisible and with no size)
     * @param ctx    the context of the Activity where the animation will be applied
     * @param i1     view 1
     * @param i2     view 2
     * @param i3     view 3
     * @param i4     view 4
     * @param i5     view 5
     * @param i6     view 6
     */
    public void circleZoomPauseResumeAnimaton(View center, Context ctx,
                                              RelativeLayout i1, RelativeLayout i2, RelativeLayout i3,
                                              RelativeLayout i4, RelativeLayout i5, RelativeLayout i6,
                                              int offset_passed, int duration) {

        // should always be zero
        int repeat = 0;
        Interpolator interpolator = new OvershootInterpolator();
        interpolator = null;
        // 1/no of views
        float delta = 0.1666f;
        // for anti countering position offset of the view
        float deviation = -0.5f;
        deviation = 0.0f;

        // the time in which the view stays at the top
        int stay_offset = offset_passed;
        // when the circle (all views) begin to animate
        int offset = stay_offset;

        Path circluarPath = new Path();
        circluarPath.addCircle(center.getX() - i1.getWidth() / 2, center.getY() - i1.getHeight() / 2,
                ctx.getResources().getDimensionPixelOffset(R.dimen._110dp), Path.Direction.CW);

        /*
        resourseAnimatePenta(i1,circluarPath,repeat,offset,interpolator);
        resourseAnimatePenta(i5,circluarPath,repeat,offset,interpolator);
        resourseAnimatePenta(i4,circluarPath,repeat,offset,interpolator);
        resourseAnimatePenta(i3,circluarPath,repeat,offset,interpolator);
        resourseAnimatePenta(i2,circluarPath,repeat,offset,interpolator);*/

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                animation.start();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animatorSet.playSequentially(
                pathAnimation(i1, duration, repeat, null, circluarPath, stay_offset, delta, 4, 2, deviation),
                pathAnimation(i1, duration, repeat, null, circluarPath, stay_offset, delta, 5, 0, deviation),
                pathAnimation(i1, duration, repeat, null, circluarPath, stay_offset, delta, 0, 0, deviation),
                pathAnimation(i1, duration, repeat, null, circluarPath, stay_offset, delta, 1, 0, deviation),
                pathAnimation(i1, duration, repeat, null, circluarPath, stay_offset, delta, 2, 0, deviation),
                pathAnimation(i1, duration, repeat, interpolator, circluarPath, offset, delta, 3, 1, deviation)
        );
        animatorSet.start();

        AnimatorSet animatorSet5 = new AnimatorSet();
        animatorSet5.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                animation.start();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animatorSet5.playSequentially(
                pathAnimation(i6, duration, repeat, interpolator, circluarPath, offset, delta, 3, 1, deviation),
                pathAnimation(i6, duration, repeat, null, circluarPath, stay_offset, delta, 4, 2, deviation),
                pathAnimation(i6, duration, repeat, null, circluarPath, stay_offset, delta, 5, 0, deviation),
                pathAnimation(i6, duration, repeat, null, circluarPath, stay_offset, delta, 0, 0, deviation),
                pathAnimation(i6, duration, repeat, null, circluarPath, stay_offset, delta, 1, 0, deviation),
                pathAnimation(i6, duration, repeat, null, circluarPath, stay_offset, delta, 2, 0, deviation)
        );
        animatorSet5.start();

        AnimatorSet animatorSet1 = new AnimatorSet();
        animatorSet1.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                animation.start();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animatorSet1.playSequentially(
                pathAnimation(i5, duration, repeat, null, circluarPath, offset, delta, 2, 0, deviation),
                pathAnimation(i5, duration, repeat, interpolator, circluarPath, stay_offset, delta, 3, 1, deviation),
                pathAnimation(i5, duration, repeat, null, circluarPath, stay_offset, delta, 4, 2, deviation),
                pathAnimation(i5, duration, repeat, null, circluarPath, stay_offset, delta, 5, 0, deviation),
                pathAnimation(i5, duration, repeat, null, circluarPath, stay_offset, delta, 0, 0, deviation),
                pathAnimation(i5, duration, repeat, null, circluarPath, stay_offset, delta, 1, 0, deviation)
        );
        animatorSet1.start();

        AnimatorSet animatorSet2 = new AnimatorSet();
        animatorSet2.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                animation.start();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animatorSet2.playSequentially(
                pathAnimation(i4, duration, repeat, null, circluarPath, offset, delta, 1, 0, deviation),
                pathAnimation(i4, duration, repeat, null, circluarPath, stay_offset, delta, 2, 0, deviation),
                pathAnimation(i4, duration, repeat, interpolator, circluarPath, stay_offset, delta, 3, 1, deviation),
                pathAnimation(i4, duration, repeat, null, circluarPath, stay_offset, delta, 4, 2, deviation),
                pathAnimation(i4, duration, repeat, null, circluarPath, stay_offset, delta, 5, 0, deviation),
                pathAnimation(i4, duration, repeat, null, circluarPath, stay_offset, delta, 0, 0, deviation)
        );
        animatorSet2.start();

        AnimatorSet animatorSet3 = new AnimatorSet();
        animatorSet3.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                animation.start();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animatorSet3.playSequentially(
                pathAnimation(i3, duration, repeat, null, circluarPath, offset, delta, 0, 0, deviation),
                pathAnimation(i3, duration, repeat, null, circluarPath, stay_offset, delta, 1, 0, deviation),
                pathAnimation(i3, duration, repeat, null, circluarPath, stay_offset, delta, 2, 0, deviation),
                pathAnimation(i3, duration, repeat, interpolator, circluarPath, stay_offset, delta, 3, 1, deviation),
                pathAnimation(i3, duration, repeat, null, circluarPath, stay_offset, delta, 4, 2, deviation),
                pathAnimation(i3, duration, repeat, null, circluarPath, offset, delta, 5, 0, deviation)
        );
        animatorSet3.start();

        AnimatorSet animatorSet4 = new AnimatorSet();
        animatorSet4.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                animation.start();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animatorSet4.playSequentially(
                pathAnimation(i2, duration, repeat, null, circluarPath, stay_offset, delta, 5, 0, deviation),
                pathAnimation(i2, duration, repeat, null, circluarPath, stay_offset, delta, 0, 0, deviation),
                pathAnimation(i2, duration, repeat, null, circluarPath, stay_offset, delta, 1, 0, deviation),
                pathAnimation(i2, duration, repeat, null, circluarPath, stay_offset, delta, 2, 0, deviation),
                pathAnimation(i2, duration, repeat, interpolator, circluarPath, stay_offset, delta, 3, 1, deviation),
                pathAnimation(i2, duration, repeat, null, circluarPath, offset, delta, 4, 2, deviation)
        );
        animatorSet4.start();

    }

    public void resourseAnimatePenta(View i1, Path circluarPath, int repeat, int offset, Interpolator interpolator) {

        float delta = 0.2f;
        int duration = 500;
        float deviation = 0.0695f;

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                animation.start();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animatorSet.playSequentially(
                pathAnimation(i1, duration, repeat, interpolator, circluarPath, offset, delta, 3, 1, deviation),
                pathAnimation(i1, duration, repeat, null, circluarPath, duration, delta, 4, 2, deviation),
                pathAnimation(i1, duration, repeat, null, circluarPath, duration, delta, 0, 0, deviation),
                pathAnimation(i1, duration, repeat, null, circluarPath, duration, delta, 1, 0, deviation),
                pathAnimation(i1, duration, repeat, null, circluarPath, duration, delta, 2, 0, deviation)
        );
        animatorSet.start();
    }

    public class RotateAnimator extends Animation {

        private View view;
        private float cx, cy;           // center x,y position of circular path
        private float prevX, prevY;     // previous x,y position of image during animation
        private float r;                // radius of circle
        private float prevDx, prevDy;


        /**
         * @param view - View that will be animated
         * @param r    - radius of circular path
         */
        public RotateAnimator(View view, float r) {
            this.view = view;
            this.r = r;
        }

        @Override
        public boolean willChangeBounds() {
            return true;
        }

        @Override
        public void initialize(int width, int height, int parentWidth, int parentHeight) {
            // calculate position of image center
            int cxImage = width / 2;
            int cyImage = height / 2;
            cx = view.getLeft() + cxImage;
            cy = view.getTop() + cyImage;

            // set previous position to center
            prevX = cx;
            prevY = cy;
        }

        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            if (interpolatedTime == 0) {
                t.getMatrix().setTranslate(prevDx, prevDy);
                return;
            }

            float angleDeg = (interpolatedTime * 360f + 90) % 360;
            float angleRad = (float) Math.toRadians(angleDeg);

            // r = radius, cx and cy = center point, a = angle (radians)
            float x = (float) (cx + r * Math.cos(angleRad));
            float y = (float) (cy + r * Math.sin(angleRad));


            float dx = prevX - x;
            float dy = prevY - y;

            prevX = x;
            prevY = y;

            prevDx = dx;
            prevDy = dy;


            t.getMatrix().setTranslate(dx, dy);
        }
    }
}