package com.example.playvogellathree.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.app.IntentService;
import android.content.Intent;
import android.os.Environment;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

public class DownloadService extends IntentService {
	
	
	private int result = Activity.RESULT_CANCELED;
	public static final String FILE_URL = "urlpath";
	public static final String FILENAME = "filename";
	public static final String FILEPATH = "filepath";
	public static final String RESULT = "result";
	public static final String NOTIFICATION = "com.playvogellathree.receiver";

	public DownloadService() { // this constructor must be empty
		super("DownloadService");
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		// TODO Auto-generated method stub
		String urlPath = intent.getStringExtra(FILE_URL);
		String fileName = intent.getStringExtra(FILENAME);
		
		File output = new File(Environment.getExternalStorageDirectory(), fileName);
		
		if (output.exists()) {
			output.delete();
		}
		
		InputStream stream = null;
		FileOutputStream fos = null; // I'll try FileOutputWriter later on
		
		try {
			URL url = new URL(urlPath);
			stream = url.openConnection().getInputStream();
			InputStreamReader reader = new InputStreamReader(stream);
			fos = new FileOutputStream(output.getPath());
			int next = -1;
			while ((next = reader.read()) != -1) {
				fos.write(next);
			}
			
			result = Activity.RESULT_OK;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			if (stream != null) {
				try {
					stream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			}
		}
		publishResults (output.getAbsolutePath(), result);
	}

	private void publishResults(String outputPath, int result) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(NOTIFICATION);
		intent.putExtra(FILEPATH, outputPath );
		intent.putExtra(RESULT, result);
		Log.i("eric", outputPath);
		LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
	}

}
