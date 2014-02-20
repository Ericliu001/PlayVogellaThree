package com.example.playvogellathree.service;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.playvogellathree.R;

public class BoundServiceActivity extends ListActivity {
	private LocalWordService s;
	private List<String> wordList;
	private ArrayAdapter<String> adapter;
	
	private ServiceConnection mConnection = new ServiceConnection() {
		

		@Override
		public void onServiceConnected(ComponentName className, IBinder binder) {
			// TODO Auto-generated method stub
			LocalWordService.MyBinder b = (LocalWordService.MyBinder)binder;
			s = b.getService();
			Toast.makeText(BoundServiceActivity.this, "Connected", Toast.LENGTH_SHORT).show();
		}
		
		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub
			s = null;
		}
		
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bound_service);
		
		wordList = new ArrayList<String>();
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, wordList);
		setListAdapter(adapter);
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Intent intent = new Intent(this, LocalWordService.class);
		bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		unbindService(mConnection);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.bound, menu);
		return true;
	}
	
	public void showServiceData(View view) {
		if (s != null) {
			
			Toast.makeText(this, "Number of elements " + s.getWordList().size(), Toast.LENGTH_SHORT).show();
			wordList.clear();
			wordList.addAll(s.getWordList());
			adapter.notifyDataSetChanged();
		}
	}

}
