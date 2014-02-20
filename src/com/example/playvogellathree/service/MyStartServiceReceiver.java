package com.example.playvogellathree.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MyStartServiceReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		Intent service = new Intent(context, LocalWordService.class);
		context.startService(service);
	}

}
