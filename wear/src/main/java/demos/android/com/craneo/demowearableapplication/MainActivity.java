package demos.android.com.craneo.demowearableapplication;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.wearable.view.ActionPage;
import android.support.wearable.view.CardFragment;
import android.support.wearable.view.WatchViewStub;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionPage actionPage = (ActionPage) findViewById(R.id.action_page);
        actionPage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.d("MainActivity", "Button Click");
            }
        });

        /**
         * We use this lines for card  fragments
         */
//    FragmentManager manager = getFragmentManager();
//    FragmentTransaction transaction = manager.beginTransaction();
//    CardFragment fragment = CardFragment.create(
//            getString(R.string.card_title),
//            getString(R.string.card_description),
//            R.drawable.landon_icon);
//    transaction.add(R.id.frame_layout, fragment);
//        transaction.commit();


    }
}



