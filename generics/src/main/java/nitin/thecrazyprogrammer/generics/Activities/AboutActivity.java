package nitin.thecrazyprogrammer.generics.Activities;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import nitin.thecrazyprogrammer.generics.R;

/**
 * Created by Nitin Khurana on 2/4/2018.
 */
public abstract class AboutActivity extends FullScreenActivity{

    private LinearLayout root;
    private TextView title;
    private TextView version;
    protected ImageView logo;

    private LinearLayout ll_buttons;

    private ArrayList<AboutButton> buttons = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Colorify.colorify(this);
        setContentView(R.layout.about_activity);

        translucentStatusBar = true;
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//
//            getWindow().setNavigationBarColor(Color.TRANSPARENT);
//
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//
//                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
//
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//
//                    if (Colorify.isLightTheme(this)) {
//                        // If the theme has white background, make nav button theme lite
//                        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
//                    }
//                }
//            }
//        }

        root = (LinearLayout) findViewById(R.id.root);
        title = (TextView) findViewById(R.id.title);
        version = (TextView) findViewById(R.id.version);
        logo = (ImageView) findViewById(R.id.logo);
        ll_buttons = (LinearLayout) findViewById(R.id.buttons);

        title.setText(setAppName());
        version.setText("Version " + setVersion());
        logo.setImageResource(setLogo());
        int backColor = setBackgroundColor();
        if(backColor != 0)
            root.setBackgroundColor(setBackgroundColor());

        buttons = setButtons();
        for(AboutButton aboutButton : buttons)
            makeButton(aboutButton.getName(), aboutButton.getOnClickListener());
    }

    private void makeButton(String title, View.OnClickListener onClickListener){

        Button button = new Button(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(5, 5, 5, 5);
        button.setLayoutParams(params);

        int[] attrs = new int[]{R.attr.selectableItemBackground, R.attr.colorAccent};
        TypedArray typedArray = obtainStyledAttributes(attrs);
        button.setBackgroundResource(typedArray.getResourceId(0, R.color.transparent));
        button.setTextColor(getResources().getColor(typedArray.getResourceId(1, R.color.colorAccent)));
        button.setTextSize(18);
        typedArray.recycle();

        button.setText(title);
        button.setOnClickListener(onClickListener);

        ll_buttons.addView(button);
    }

    protected int setBackgroundColor(){
        return 0;
    }

    protected abstract String setAppName();

    protected abstract String setVersion();

    protected abstract int setLogo();

    protected abstract ArrayList<AboutButton> setButtons();

    public class AboutButton{

        private String name;
        private View.OnClickListener onClickListener;

        public AboutButton(String name, View.OnClickListener onClickListener){

            this.name = name;
            this.onClickListener = onClickListener;
        }

        public View.OnClickListener getOnClickListener() {
            return onClickListener;
        }

        public String getName() {
            return name;
        }
    }
}
