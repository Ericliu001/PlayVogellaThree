package com.example.playvogellathree.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class LocalWordService extends Service {

	private final IBinder mBinder = new MyBinder();
	private ArrayList<String> list = new ArrayList<String>();

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		Random random = new Random();

		list.add("The first line.");
		if (random.nextBoolean()) {
			list.add("Linux");
		}
		if (random.nextBoolean()) {
			list.add("Android");
		}
		if (random.nextBoolean()) {
			list.add("iPhone");
		}
		if (random.nextBoolean()) {
			list.add("Windows7");
		}
		if (random.nextBoolean()) {
			list.add("MacOS");
		}
		if (random.nextBoolean()) {
			list.add("Crap");
		}
		if (random.nextBoolean()) {
			list.add("Sexy");
		}
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub

		return Service.START_NOT_STICKY;
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return mBinder;
	}

	public class MyBinder extends Binder {
		LocalWordService getService() {
			return LocalWordService.this;
		}
	}

	public List<String> getWordList() {
		return list;
	}

}
