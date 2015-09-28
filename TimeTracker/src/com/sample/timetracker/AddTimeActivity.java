package com.sample.timetracker;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class AddTimeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_time);
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_add_time, menu);
		return true;
	}

	public void onCancel(View view) {
		finish();
	}
	
	public void onSave(View view) {
		Intent intent = getIntent();
		EditText timeView = (EditText) findViewById(R.id.timeEditText);
		intent.putExtra("time", timeView.getText().toString());
		EditText notesView = (EditText) findViewById(R.id.notesEditText);
		intent.putExtra("notes", notesView.getText().toString());
		this.setResult(RESULT_OK, intent);
		finish();
	}
}
