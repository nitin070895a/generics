package nitin.thecrazyprogrammer.generics.Activities;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.util.Pair;
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
    private ImageView logo;

    private LinearLayout ll_buttons;

    private ArrayList<AboutButton> buttons = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_activity);

        root = (LinearLayout) findViewById(R.id.root);
        title = (TextView) findViewById(R.id.title);
        version = (TextView) findViewById(R.id.version);
        logo = (ImageView) findViewById(R.id.logo);
        ll_buttons = (LinearLayout) findViewById(R.id.buttons);

        title.setText(setAppName());
        version.setText("Version " + setVersion());
        logo.setImageResource(setLogo());
        root.setBackgroundColor(getResources().getColor(setBackgroundColor()));

        buttons = setButtons();
        for(AboutButton aboutButton : buttons)
            makeButton(aboutButton.getName(), aboutButton.getActivity());
    }

    private void makeButton(String title, final Class activity){

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
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AboutActivity.this, activity));
            }
        });

        ll_buttons.addView(button);
    }

    protected int setBackgroundColor(){
        return R.color.white;
    }

    protected abstract String setAppName();

    protected abstract String setVersion();

    protected abstract int setLogo();

    protected abstract ArrayList<AboutButton> setButtons();

    public class AboutButton{

        private String name;
        private Class activity;

        public AboutButton(String name, Class activity){

            this.name = name;
            this.activity = activity;
        }

        public Class getActivity() {
            return activity;
        }

        public String getName() {
            return name;
        }
    }
}
