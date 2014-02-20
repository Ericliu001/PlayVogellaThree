package com.example.playvogellathree.service;

import com.example.playvogellathree.R;
import com.example.playvogellathree.R.layout;
import com.example.playvogellathree.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ServiceCommunicationActivity extends Activity {

	private TextView tvStatus;
	private BroadcastReceiver receiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			Bundle bundle = intent.getExtras();
			handleResult(bundle);
			Log.i("eric", "Message received!");
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_service_communication);
		tvStatus = (TextView) findViewById(R.id.tvStatus);
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		LocalBroadcastManager.getInstance(this).registerReceiver(receiver,
				new IntentFilter(DownloadService.NOTIFICATION));
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver);
	}

	public void onClick(View view) {

		Intent intent = new Intent(this, DownloadService.class);
		intent.putExtra(DownloadService.FILENAME, "index.html");
		intent.putExtra(DownloadService.FILE_URL,
				"http://vogella.com/index.html");
		startService(intent);
		tvStatus.setText("Service Started!");
	}

	protected void handleResult(Bundle bundle) {
		// TODO Auto-generated method stub
		if (bundle != null) {
			String string = bundle.getString(DownloadService.FILEPATH);
			int resultCode = bundle.getInt(DownloadService.RESULT);
			if (resultCode == RESULT_OK) {
				Toast.makeText(this,
						"Download Complete. Download URI: " + string,
						Toast.LENGTH_LONG).show();
				tvStatus.setText("Download Done");
			} else {
				Toast.makeText(this, "Download failed", Toast.LENGTH_LONG)
						.show();
				tvStatus.setText("Download Failed");
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.service_communication, menu);
		return true;
	}

}
