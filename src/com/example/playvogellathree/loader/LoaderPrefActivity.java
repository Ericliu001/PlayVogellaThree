package com.example.playvogellathree.loader;

import com.example.playvogellathree.R;
import com.example.playvogellathree.R.layout;
import com.example.playvogellathree.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Loader;
import android.content.SharedPreferences;
import android.view.Menu;
import android.widget.TextView;

public class LoaderPrefActivity extends Activity implements LoaderCallbacks<SharedPreferences> {

	private static final String KEY = "prefs";
	TextView textView;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_loader_pref);
		
		textView = (TextView) findViewById(R.id.tvPrefs);
		getLoaderManager().initLoader(0, null, this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.loader_pref, menu);
		return true;
	}

	@Override
	public Loader<SharedPreferences> onCreateLoader(int id, Bundle args) {
		// TODO Auto-generated method stub
		return new SharedPreferenceLoader(this);
	}

	@Override
	public void onLoadFinished(Loader<SharedPreferences> loader,
			SharedPreferences prefs) {
		// TODO Auto-generated method stub
		int value = prefs.getInt(KEY, 0);
		value += 1;
		
		textView.setText(String.valueOf(value));
		
		SharedPreferences.Editor editor = prefs.edit();
		editor.putInt(KEY, value);
		SharedPreferenceLoader.persist(editor);
	}

	@Override
	public void onLoaderReset(Loader<SharedPreferences> arg0) {
		// TODO Auto-generated method stub
		
	}

}
