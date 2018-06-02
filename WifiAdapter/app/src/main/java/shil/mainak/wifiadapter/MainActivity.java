package shil.mainak.wifiadapter;

import android.app.Activity;
import android.content.Context;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends Activity {
            Button enableButton,disableButton;
            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);

                enableButton=(Button)findViewById(R.id.button1);
                disableButton=(Button)findViewById(R.id.button2);

                final MainActivity mainActivity = new MainActivity();

                enableButton.setOnClickListener(new View.OnClickListener(){
                    public void onClick(View v){
                        WifiManager wifi = (WifiManager)getApplicationContext().getSystemService(Context.WIFI_SERVICE);
                        wifi.setWifiEnabled(true);

//                        mainActivity.state();
//
//                        int netId = wifiManager.addNetwork(wifiConfig);
//                        wifiManager.disconnect();
//                        wifiManager.enableNetwork(netId, true);
//                        wifiManager.reconnect();

                        String ssid = "electrolock-002";
                        String key = "11112222";

                        mainActivity.ConnectToWiFi(ssid,key,getApplicationContext());
                    }
                });




                disableButton.setOnClickListener(new View.OnClickListener(){
                    public void onClick(View v){
                        WifiManager wifi = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
                        wifi.setWifiEnabled(false);
                    }
                });
            }

//    public void state() {
//
//        String networkSSID = "xyz";
//        String networkPass = "xyzxyzxyz";
//
//        WifiConfiguration conf = new WifiConfiguration();
//        conf.SSID = "\"" + networkSSID + "\"";
//
//        conf.wepKeys[0] = "\"" + networkPass + "\"";
//        conf.wepTxKeyIndex = 0;
//        conf.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
//        conf.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP40);
//
//        conf.preSharedKey = "\""+ networkPass +"\"";
//
//        conf.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
//
//
//            }

    static public void ConnectToWiFi(String ssid,String key,Context ctx) {

        WifiConfiguration wifiConfig = new WifiConfiguration();
        wifiConfig.SSID = String.format("\"%s\"", ssid);
        wifiConfig.preSharedKey = String.format("\"%s\"", key);
        WifiManager wifiManager = (WifiManager) ctx.getSystemService(ctx.WIFI_SERVICE);
        int networkId = wifiManager.getConnectionInfo().getNetworkId();
        wifiManager.removeNetwork(networkId);
        wifiManager.saveConfiguration();
        //remember id
        int netId = wifiManager.addNetwork(wifiConfig);
        wifiManager.disconnect();
        wifiManager.enableNetwork(netId, true);
        wifiManager.reconnect();

        }
    }
