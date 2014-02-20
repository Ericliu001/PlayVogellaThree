package com.example.playvogellathree.loader;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.preference.PreferenceManager;

public class SharedPreferenceLoader extends AsyncTaskLoader<SharedPreferences> implements OnSharedPreferenceChangeListener {

	private SharedPreferences prefs = null;
	
	public SharedPreferenceLoader(Context context) {
		super(context);
	}

	@Override
	public SharedPreferences loadInBackground() {
		prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
		prefs.registerOnSharedPreferenceChangeListener(this);
		
		return prefs;
	}

	@Override
	public void onSharedPreferenceChanged(SharedPreferences arg0, String arg1) {
		// TODO Auto-generated method stub
		onContentChanged();
	}
	
	@Override
	protected void onStartLoading() {
		// TODO Auto-generated method stub
		if (prefs != null) {
			deliverResult(prefs);
		}
		
		if (takeContentChanged() || prefs == null) {
			forceLoad();
		}
	}
	
	public static void persist(final SharedPreferences.Editor editor){
		editor.apply();
	}

}
