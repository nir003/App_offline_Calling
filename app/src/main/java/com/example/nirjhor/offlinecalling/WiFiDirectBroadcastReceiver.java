package com.example.nirjhor.offlinecalling;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.p2p.WifiP2pManager;
import android.widget.Toast;
import android.net.NetworkInfo;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pManager.Channel;
import android.net.wifi.p2p.WifiP2pManager.PeerListListener;

/**
 * Created by nirjhor on 3/26/2018.
 */

public class WiFiDirectBroadcastReceiver extends BroadcastReceiver {

    private WifiP2pManager mManager;
    private Channel mChannel;
    private IpTest mActivity;

    /*myCode*/
    private PeerListListener myPeerListListener;
    /*myCode*/

    public WiFiDirectBroadcastReceiver(WifiP2pManager mManager, Channel mChannel, IpTest mActivity) {
        this.mManager = mManager;
        this.mChannel = mChannel;
        this.mActivity = mActivity;
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        String action = intent.getAction();

        if (WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION.equals(action))
        {
            //do something when
            int state = intent.getIntExtra(WifiP2pManager.EXTRA_WIFI_STATE,-1);
            if (state == WifiP2pManager.WIFI_P2P_STATE_ENABLED)
            {
               // mActivity.setIsWifiP2pEnabled(true);
                Toast.makeText(context, "WiFi is ON", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(context, "WiFi is OFF", Toast.LENGTH_SHORT).show();
                /*My Code*/
                //mActivity.clearpeers();
                /*My Code*/
            }
        } else if (WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION.equals(action)) {
            //do something when
            /*My code*/
            //mManager.requestPeers(mChannel,myPeerListListener);
            /*My code*/
            if(mManager!=null)
            {
                mManager.requestPeers(mChannel,mActivity.peerListListener);
            }
        } else if (WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION.equals(action)) {
            //do something when
        } else if (WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION.equals(action)) {
            //do something when
        }


    }



}
