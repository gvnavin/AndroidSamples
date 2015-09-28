package com.example.nasadailyimage;

import java.io.IOException;

import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.app.WallpaperManager;
import android.graphics.Bitmap;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class NasaDailyImage extends Activity {

	Handler handler;
	Bitmap mImage;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		setContentView(R.layout.activity_nasa_daily_image);
		handler = new Handler();
		refreshFromFeed();
	}

	public void OnRefresh(View view) {
		refreshFromFeed();
	}

	private void refreshFromFeed() {
		// TODO Auto-generated method stub

		final ProgressDialog dialog = ProgressDialog.show(this, "Loading", "Loading the image of the Day");
		final IotdHandler iotdHandler = new IotdHandler();
		Thread th = new Thread() {
			public void run() {
				iotdHandler.processFeed();
				handler.post(new Runnable() {
					public void run() {
						mImage = iotdHandler.getImage();
						resetDisplay(iotdHandler.getTitle(), iotdHandler.getDate(), iotdHandler.getImage(), iotdHandler.getDescription().toString());
						dialog.dismiss();
					}
				});
			}
		};
		th.start();
	}

	public void OnSetWallPaper(View view) throws IOException {
		Thread th = new Thread() {
			public void run() {
				WallpaperManager wallpaperManager = WallpaperManager.getInstance(NasaDailyImage.this);
				try {
					wallpaperManager.setBitmap(mImage);
					handler.post(new Runnable() {
						public void run() {
							Toast.makeText(NasaDailyImage.this, "Wallpaper set", Toast.LENGTH_SHORT).show();
						}
					});
				} catch (Exception e) {
					e.printStackTrace();
					handler.post(new Runnable() {
						public void run() {
							Toast.makeText(NasaDailyImage.this, "Error setting wallpaper", Toast.LENGTH_SHORT).show();
						}
					});
				}
			}
		};
		th.start();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_nasa_daily_image, menu);
		return true;
	}

	private void resetDisplay(String title, String date, Bitmap image, String description) {

		TextView titleView = (TextView) findViewById(R.id.imageTitle);
		titleView.setText(title);
		TextView dateView = (TextView) findViewById(R.id.imageDate);
		dateView.setText(date);
		ImageView imageView = (ImageView) findViewById(R.id.imageDisplay);
		imageView.setImageBitmap(image);
		TextView descriptionView = (TextView) findViewById(R.id.imageDescription);
		descriptionView.setText(description);

	}
}
