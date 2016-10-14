package demos.android.com.craneo.demowearableapplication;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.wearable.MessageApi;
import com.google.android.gms.wearable.Node;
import com.google.android.gms.wearable.NodeApi;
import com.google.android.gms.wearable.Wearable;

public class ConnectDevicesActivity extends Activity
        implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    public static final String WEARABLE_MAIN = "WearableMain";

    private Node mNode;
    private GoogleApiClient googleApiClient;
    private static final String WEAR_PATH = "/from-wear";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect_devices);

        //Initialize googleApiClient
        googleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Wearable.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
    }

    public void buttonClickHandler(View view){
        Button button = (Button) view;
        String city = button.getText().toString();
        senMessage(city);
    }

    private void senMessage(String city) {
        if (mNode != null && googleApiClient != null){
            Wearable.MessageApi.sendMessage(
                    googleApiClient, mNode.getId(), WEAR_PATH, city.getBytes())
                    .setResultCallback(new ResultCallback<MessageApi.SendMessageResult>() {
                        @Override
                        public void onResult(@NonNull MessageApi.SendMessageResult sendMessageResult) {
                            if(!sendMessageResult.getStatus().isSuccess()){
                                Log.d(WEARABLE_MAIN, "Failed message... "+
                                        sendMessageResult.getStatus().getStatusCode());
                            }else{
                                Log.d(WEARABLE_MAIN, "Message succeded...");
                            }
                        }
                    });
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Wearable.NodeApi.getConnectedNodes(googleApiClient)
                .setResultCallback(new ResultCallback<NodeApi.GetConnectedNodesResult>() {
                    @Override
                    public void onResult(@NonNull NodeApi.GetConnectedNodesResult nodes) {
                        for (Node node : nodes.getNodes()){
                            if (node != null && node.isNearby()){
                                mNode = node;
                                Log.d(WEARABLE_MAIN, "Connected to "+node.getDisplayName());
                            }
                        }
                        if (mNode == null){
                            Log.d(WEARABLE_MAIN, "Not connected ");
                        }
                    }
                });
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    protected void onStart() {
        super.onStart();
        googleApiClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        googleApiClient.disconnect();
    }
}
