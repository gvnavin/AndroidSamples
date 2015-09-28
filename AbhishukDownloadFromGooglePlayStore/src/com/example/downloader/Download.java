package com.example.downloader;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.StrictMode;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.gc.android.market.api.MarketSession;
import com.gc.android.market.api.model.Market.GetAssetResponse.InstallAsset;

public class Download extends Activity {

	Handler handler;
	boolean installed = true; 
	String assetId = "com.dasque";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_download);
		handler = new Handler();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_download, menu);
		return true;
	}

	public void downloadApk(View button){
		try {
			    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			    StrictMode.setThreadPolicy(policy);
			    MarketSession session = new MarketSession(true);
			    TextView text = (TextView) findViewById(R.id.textView1) ;
				text.setVisibility(View.VISIBLE);
			    //System.out.println("Login...");
			    session.login("kcpchennai0@gmail.com","readmore", "31b2b6b608f30dab");
			    //System.out.println("Login done");
			    Toast.makeText(Download.this, "Sucessfully logged in", Toast.LENGTH_SHORT).show();
			    
			    InstallAsset ia = session.queryGetAssetRequest(assetId).getInstallAsset(0);
			    String cookieName = ia.getDownloadAuthCookieName();
			    String cookieValue = ia.getDownloadAuthCookieValue();
			    URL url = new URL(ia.getBlobUrl());

			    HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			    conn.setRequestMethod("GET");
			    conn.setRequestProperty("User-Agent", "Android-Market/2 (sapphire PLAT-RC33); gzip");
			    conn.setRequestProperty("Cookie", cookieName + "=" + cookieValue);
			    conn.connect();
			    int var = conn.getResponseCode();
			    Toast.makeText(Download.this, "Opening Input Stream", Toast.LENGTH_SHORT).show();
			    
			    InputStream inputstream = (InputStream) conn.getInputStream(); //(InputStream)url.openStream(); 
			    String fileToSave = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString() + "/" + assetId + ".apk";// Environment.getExternalStorageDirectory() + "/Download/" + assetId + ".apk";
			    
			    //System.out.println("Downloading " + fileToSave);
			    BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(fileToSave));
			    byte buf[] = new byte[1024];
			    int k = 0;
			    for(long l = 0L; (k = inputstream.read(buf)) != -1; l += k )
			        stream.write(buf, 0, k);
			    inputstream.close();
			    System.out.println("Downloading done " + fileToSave);
			    stream.close();
			    } catch(Exception ex) {
			    	installed = false;
			        ex.printStackTrace();
			    } 
				handler.post(new Runnable() {
				public void run() {
					if ( installed ) {
						onInstall(null);
						Toast.makeText(Download.this, "Downloaded Sucessfully", Toast.LENGTH_SHORT).show();
					} else {
						Toast.makeText(Download.this, "Download Failed", Toast.LENGTH_SHORT).show();
					}
				}
			});
	}
	
	public void onInstall(View view) {
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setDataAndType(Uri.fromFile(new File(Environment.getExternalStorageDirectory() + "/Download/" + assetId + ".apk")), "application/vnd.android.package-archive");
		startActivity(intent);
	}
}