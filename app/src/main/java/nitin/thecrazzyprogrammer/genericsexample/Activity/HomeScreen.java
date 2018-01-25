package nitin.thecrazzyprogrammer.genericsexample.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import nitin.thecrazzyprogrammer.generics.Activities.BasicActivity;
import nitin.thecrazzyprogrammer.genericsexample.R;

/**
 * Created by Nitin Khurana on 1/25/2018.
 */
public class HomeScreen extends BasicActivity{

    Button button;

    @Override
    protected String setActivityTitle() {
        return "Home Screen";
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(HomeScreen.this, ExampleTabbedActivity.class));
            }
        });
    }

    @Override
    protected int setLayout() {
        return R.layout.home_screen;
    }
}
