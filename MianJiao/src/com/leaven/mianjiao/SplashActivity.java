package com.leaven.mianjiao;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class SplashActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);

		Timer timer = new Timer();

		TimerTask task = new TimerTask() {

			public void run() {

				Intent intent = new Intent(SplashActivity.this,
						MultiTabActivity.class);

				startActivity(intent);

				overridePendingTransition(android.R.anim.fade_in,
						android.R.anim.fade_out);

				finish();
			}

		};

		timer.schedule(task, 1500);

	}
}
