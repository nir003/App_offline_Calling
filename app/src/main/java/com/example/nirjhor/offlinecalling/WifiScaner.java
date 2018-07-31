package com.example.nirjhor.offlinecalling;

import java.util.List;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.util.Log;
import android.widget.Toast;

public class WifiScaner extends BroadcastReceiver {
	
	private static final String TAG="WifiscanReceiver";
	Ip_lister main;
	
	public WifiScaner(Ip_lister main){
		super();
		this.main=main;
	}

	@Override
	public void onReceive(Context arg0, Intent arg1) {
		// TODO Auto-generated method stub
		List<ScanResult>results=main.wifi.getScanResults();
		ScanResult bestsignal =null;
		for (ScanResult result:results){
			if (bestsignal==null || WifiManager.compareSignalLevel(bestsignal.level, result.level)<0)
				bestsignal=result;
		}
		
	String message=String.format("%s networks found.%s is the strongest.",results.size(),bestsignal.SSID);
	Toast.makeText(main, message, 0).show();
	Log.d(TAG, "onReceive() message :" +message);

	}

}
