package demos.android.com.craneo.demowearableapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.activity.ConfirmationActivity;
import android.support.wearable.view.DelayedConfirmationView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ConfirmationWearableActivity extends Activity
        implements DelayedConfirmationView.DelayedConfirmationListener{

    private boolean isRunning = false;
    private DelayedConfirmationView delayView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);

        /**
         * Confirmation example
         */
        delayView = (DelayedConfirmationView) findViewById(R.id.delayed_confirm);
        delayView.setListener(this);
        delayView.setTotalTimeMs(3000);

        RelativeLayout container = (RelativeLayout)findViewById(R.id.container);
        container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isRunning){
                    showTimer(true);
                    delayView.start();
                    isRunning = true;
                }
            }
        });
    }

    private void showTimer(boolean show){
        LinearLayout layout = (LinearLayout)findViewById(R.id.linearLayout);
        TextView textView = (TextView)findViewById(R.id.tvStatus);

        if(show){
            layout.setVisibility(View.VISIBLE);
            textView.setVisibility(View.INVISIBLE);
        }else{
            layout.setVisibility(View.INVISIBLE);
            textView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onTimerFinished(View view) {
        showTimer(false);
        isRunning = false;
        showConfirmation(ConfirmationActivity.SUCCESS_ANIMATION, "Confirmed");
    }

    private void showConfirmation(int animation, String message){
        Intent intent = new Intent(this, ConfirmationActivity.class);
        intent.putExtra(ConfirmationActivity.EXTRA_ANIMATION_TYPE, animation);
        intent.putExtra(ConfirmationActivity.EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    @Override
    public void onTimerSelected(View view) {
        showTimer(false);
        isRunning = false;
        delayView.reset();
        showConfirmation(ConfirmationActivity.FAILURE_ANIMATION, "Cancelled");

    }
}
