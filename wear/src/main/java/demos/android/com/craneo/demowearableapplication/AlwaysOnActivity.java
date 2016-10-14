package demos.android.com.craneo.demowearableapplication;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

public class AlwaysOnActivity extends WearableActivity {

    private static final int SPEECH_REQUEST_CODE = 1001;
    private TextView textView;
    private RelativeLayout layoutContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_always_on);

        setAmbientEnabled();

        textView = (TextView) findViewById(R.id.textView);
        layoutContainer = (RelativeLayout) findViewById(R.id.layoutContainer);

        layoutContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displaySpeechRecognizer();
            }
        });

    }

    private void displaySpeechRecognizer() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        startActivityForResult(intent, SPEECH_REQUEST_CODE );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == SPEECH_REQUEST_CODE && resultCode == RESULT_OK){
            List<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            String spokenText = result.get(0);

            TextView tv = (TextView) findViewById(R.id.textView);
            tv.setText("You said...\n"+spokenText);
        }
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
