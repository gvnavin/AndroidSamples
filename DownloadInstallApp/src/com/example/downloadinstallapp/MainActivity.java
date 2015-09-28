package com.example.downloadinstallapp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.StrictMode;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

	Handler handler;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		setContentView(R.layout.activity_main);
		handler = new Handler();
	}

	@SuppressLint("NewApi")
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	public void onDownload(View view) throws IOException {

		final ProgressDialog dialog = ProgressDialog.show(this, "Downloading", "Downloading the File");

		Thread th = new Thread() {
			
			boolean installed = true;
			
			@SuppressLint("NewApi")
			public void run() {

				try {
					
					StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
					StrictMode.setThreadPolicy(policy);
										
					//String apkurl = "https://www.amazon.com/appstorelanding";
					String apkurl = "https://improvement-ninjas.amazon.com/s3files/s3get.cgi/Kindle-release-signed-3.5.0.68-9-2012-09-05_23-48-05.apk";
					URL url = new URL(apkurl);
					
					/*HttpURLConnection c = (HttpURLConnection) url.openConnection();
					c.setAllowUserInteraction(false);
			        c.setInstanceFollowRedirects(true);
					c.setReadTimeout(10000);  // milliseconds 
					c.setConnectTimeout(15000);  //* milliseconds 
					c.setRequestMethod("GET");
					c.setDoOutput(true);
                    conn.setRequestProperty("User-Agent", "Android-Market/2 (sapphire PLAT-RC33); gzip");
                    conn.setRequestProperty("Cookie", cookieName + "=" + cookieValue);

					c.connect();

					int response = -1;
					response = c.getResponseCode();
					*/
					
					String PATH = Environment.getExternalStorageDirectory() + "/Download/";
					 //String fileToSave = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString() + "/" + assetId + ".apk";
					File file = new File(PATH);
					
					// file.mkdirs();
					
					//File outputFile = new File(file, "amazon_app_store_downloaded_by_code.apk");
					File outputFile = new File(file, "kindle_downloaded_by_code.apk");
					FileOutputStream fos = new FileOutputStream(outputFile);

					InputStream is = null;
					/*if (response == HttpURLConnection.HTTP_OK) {
				        is = c.getInputStream();                                 
				    } */ 
					
					is = url.openStream();

					byte[] buffer = new byte[1024];
					int len1 = 0;
					while ((len1 = is.read(buffer)) != -1) {
						fos.write(buffer, 0, len1);
					}
					fos.close();
					is.close();
				} catch (Exception ex) {
					System.out.println(ex);
					ex.printStackTrace();
					installed = false;
				}

				handler.post(new Runnable() {
					public void run() {
						if ( installed ) {
							Toast.makeText(MainActivity.this, "Downloaded Sucessfully", Toast.LENGTH_SHORT).show();
						} else {
							Toast.makeText(MainActivity.this, "Download Failed", Toast.LENGTH_SHORT).show();
						}
							
						dialog.dismiss();
					}
				});
			}
		};
		th.start();
	}

	public void onInstall(View view) {
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setDataAndType(Uri.fromFile(new File(Environment.getExternalStorageDirectory() + "/Download/" + "amazon_app_store_downloaded_by_code.apk")), "application/vnd.android.package-archive");
		startActivity(intent);
	}

}
