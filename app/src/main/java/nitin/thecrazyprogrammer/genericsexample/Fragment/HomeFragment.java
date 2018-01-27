package nitin.thecrazyprogrammer.genericsexample.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import nitin.thecrazyprogrammer.generics.Fragments.BasicFragment;
import nitin.thecrazyprogrammer.genericsexample.Activity.ExampleActivity;
import nitin.thecrazyprogrammer.genericsexample.Activity.ExampleCollapsingToolbarActivity;
import nitin.thecrazyprogrammer.genericsexample.Activity.ExampleFragmentActivity;
import nitin.thecrazyprogrammer.genericsexample.Activity.ExampleLoadingActivity;
import nitin.thecrazyprogrammer.genericsexample.Activity.ExampleRecyclerViewActivity;
import nitin.thecrazyprogrammer.genericsexample.Activity.ExampleTabbedActivity;
import nitin.thecrazyprogrammer.genericsexample.Activity.ExampleWebViewActivity;
import nitin.thecrazyprogrammer.genericsexample.Activity.HomeScreen;
import nitin.thecrazyprogrammer.genericsexample.R;

/**
 * Created by Nitin Khurana on 1/28/2018.
 */
public class HomeFragment extends BasicFragment{

    LinearLayout root;

    @Override
    protected int setLayout() {
        return R.layout.example_fragment;
    }

    @Override
    protected void onCreateView(View view) {
        root = (LinearLayout) view.findViewById(R.id.content);

        makeButton("Basic Activity", ExampleActivity.class);
        makeButton("Loading Activity", ExampleLoadingActivity.class);
        makeButton("Basic Fragment Activity", ExampleFragmentActivity.class);
        makeButton("Basic RecyclerView Activity", ExampleRecyclerViewActivity.class);
        makeButton("Basic WebView Activity", ExampleWebViewActivity.class);
        makeButton("Tabbed Activity", ExampleTabbedActivity.class);
        makeButton("Collapsing Toolbar Activity", ExampleCollapsingToolbarActivity.class);
        makeButton("Nav Drawer Activity", HomeScreen.class);
    }

    private void makeButton(String title, final Class activity){

        Button button = new Button(getContext());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(20, 20, 20, 20);
        button.setLayoutParams(params);

        button.setText(title);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), activity));
                if(activity.getSimpleName().equals("HomeScreen"))
                    ((Activity)getContext()).finish();
            }
        });

        root.addView(button);
    }

}
