package com.example.accessgoogleplay;


import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);
		
	}
	
	@SuppressLint("NewApi")
	public void OnButton1Clicked(View view) {
		
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		
		final String appName = "com.imdb.mobile";
		try {
		    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id="+appName)));
		} catch (android.content.ActivityNotFoundException anfe) {
		    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id="+appName)));
		}
			    
		/*final String appName = "com.imdb.mobile";
		try {
		    startActivityForResult(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id="+appName)),1);
		} catch (android.content.ActivityNotFoundException anfe) {
		    startActivityForResult(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id="+appName)),1);
		}*/
	}
	
	/*protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == 1) {
			if (resultCode == RESULT_OK) {
				System.out.println("here");
				
			}
		}
	}*/
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
