package nitin.thecrazyprogrammer.genericsexample.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import nitin.thecrazyprogrammer.genericsexample.R;
import nitin.thecrazyprogrammer.generics.Activities.BasicActivity;
import nitin.thecrazyprogrammer.genericsexample.R;

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
        makeButton("Loading Activity", ExampleLoadingActivity.class);
        makeButton("Basic Fragment Activity", ExampleFragmentActivity.class);
        makeButton("Basic RecyclerView Activity", ExampleRecyclerViewActivity.class);
        makeButton("Basic WebView Activity", ExampleWebViewActivity.class);
        makeButton("Tabbed Activity", ExampleTabbedActivity.class);
        makeButton("Collapsing Toolbar Activity", ExampleCollapsingToolbarActivity.class);
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

    @Override
    protected int setMenuResource() {
        return R.menu.example_menu;
    }
}
