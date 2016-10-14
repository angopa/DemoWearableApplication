package demos.android.com.craneo.demowearableapplication;

import android.content.Intent;
import android.util.Log;

import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.WearableListenerService;

/**
 * Created by crane on 10/14/2016.
 */

public class ListenerServiceFromWear extends WearableListenerService{

    private static final String WEAR_PATH = "/from-wear";

    @Override
    public void onMessageReceived(MessageEvent messageEvent) {
        super.onMessageReceived(messageEvent);
        Log.d("ListenerServiceFromWear", "Test");
        if (messageEvent.getPath().equals(WEAR_PATH)) {
            String city = new String(messageEvent.getData());

            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra("city", city);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                Intent.FLAG_ACTIVITY_CLEAR_TOP);

            startActivity(intent);

        }
    }
}
