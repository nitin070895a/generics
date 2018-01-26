package nitin.thecrazzyprogrammer.genericsexample.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import nitin.thecrazzyprogrammer.generics.Activities.BasicActivity;
import nitin.thecrazzyprogrammer.genericsexample.R;

/**
 * Created by Nitin Khurana on 1/25/2018.
 */
public class HomeScreen extends BasicActivity{

    LinearLayout root;

    @Override
    protected String setActivityTitle() {
        return "Home Screen";
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        root = (LinearLayout) findViewById(R.id.content);

        makeButton("Basic Activity", ExampleActivity.class);
        makeButton("Example Loading Activity", ExampleLoadingActivity.class);
        makeButton("Fragment Activity", ExampleFragmentActivity.class);
        makeButton("RecyclerView Activity", ExampleRecyclerViewActivity.class);
        makeButton("WebView Activity", ExampleWebViewActivity.class);
        makeButton("Tabbed Activity", ExampleTabbedActivity.class);
    }

    @Override
    protected int setLayout() {
        return R.layout.example_activity;
    }

    private void makeButton(String title, final Class activity){

        Button button = new Button(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(20, 20, 20, 20);
        button.setLayoutParams(params);

        button.setText(title);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeScreen.this, activity));
            }
        });

        root.addView(button);
    }
}
