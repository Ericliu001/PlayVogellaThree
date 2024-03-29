package com.example.playvogellathree;


import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends ListActivity {
	
	String[] ActivityNames = {
			"loader.LoaderPrefActivity"
			,"threads.ThreadsLifecycleActivity"
			,"service.ServiceCommunicationActivity"
			,"service.BoundServiceActivity"
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ActivityNames);
		setListAdapter(adapter);
	}
	

	@Override
	protected void onListItemClick(ListView list, View view, int position, long id) {
		String activityName = ActivityNames[position];
		try {
			Class targetActivity = Class.forName("com.example.playvogellathree." + activityName);
			Intent intent = new Intent(this, targetActivity);
			startActivity(intent);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}