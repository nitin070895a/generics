package nitin.thecrazyprogrammer.generics.Tools;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import nitin.thecrazyprogrammer.generics.R;

/**
 * Created by Nitin Khurana on 2/4/2018.
 */
public class DotMaker {

    private int totalPages;
    private Context context;

    private int dot_tint = R.color.white;
    private int highlight_dot_tint = R.color.white;

    public DotMaker(Context context, int totalPages){

        this.context = context;
        this.totalPages = totalPages;
    }

    /**
     * Make dots around the viewpager
     * @param dot_ll the linear layout added in your layout file that will contain dots
     */
    public void makeDots(final LinearLayout dot_ll) {

        dot_ll.removeAllViews();

        for (int i = 0; i < totalPages; i++) {

            LinearLayout.LayoutParams params;
            params = new LinearLayout.LayoutParams(15, 15);

            ImageView imageView = new ImageView(context);
            imageView.setLayoutParams(params);
            imageView.setImageResource(R.drawable.dot);
            applyTint(imageView, dot_tint);
            imageView.setId(987 + i);
            dot_ll.addView(imageView);
        }

        if (totalPages > 0)
            bigDot(0, dot_ll);

    }

    /**
     * Highlight dot that is represeting the current page of the viewpager
     * @param n current page no of the viewpager
     * @param dot_ll the linear layout where we have the dots
     */
    public void bigDot(int n, LinearLayout dot_ll) {
        LinearLayout.LayoutParams params;

        for (int i = 0; i < totalPages; i++) {
            ImageView imageView = (ImageView) dot_ll.findViewById(987 + i);
            if (i == n){
                params = new LinearLayout.LayoutParams(25, 25);
                applyTint(imageView, highlight_dot_tint);
            }
            else{
                applyTint(imageView, dot_tint);
                params = new LinearLayout.LayoutParams(15, 15);
            }
            imageView.setLayoutParams(params);
            imageView.setImageResource(R.drawable.dot);
        }
    }

    private void applyTint(ImageView imageView, int color){
        imageView.setColorFilter(ContextCompat.getColor(context, color), android.graphics.PorterDuff.Mode.MULTIPLY);
    }

    public void setDot_tint(int dot_tint) {
        this.dot_tint = dot_tint;
    }

    public void setHighlight_dot_tint(int highlight_dot_tint) {
        this.highlight_dot_tint = highlight_dot_tint;
    }
}
