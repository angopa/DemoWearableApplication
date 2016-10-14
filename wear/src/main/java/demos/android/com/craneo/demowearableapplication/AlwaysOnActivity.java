package demos.android.com.craneo.demowearableapplication;

import android.app.Activity;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class AlwaysOnActivity extends WearableActivity {

    private TextView textView;
    private RelativeLayout layoutContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_always_on);

        setAmbientEnabled();

        textView = (TextView) findViewById(R.id.textView);
        layoutContainer = (RelativeLayout) findViewById(R.id.layoutContainer);
        

    }



    @Override
    public void onEnterAmbient(Bundle ambientDetails){
        super.onEnterAmbient(ambientDetails);

        layoutContainer.setBackgroundResource(android.R.color.black);
        textView.setTextColor(Color.WHITE);
        textView.getPaint().setAntiAlias(true);
    }

    @Override
    public void onExitAmbient() {
        super.onExitAmbient();
        layoutContainer.setBackgroundResource(android.R.color.holo_blue_light);
        textView.setTextColor(Color.BLACK);
        textView.getPaint().setAntiAlias(false);
    }
}
