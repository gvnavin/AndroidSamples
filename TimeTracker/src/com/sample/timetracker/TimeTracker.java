package com.sample.timetracker;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

public class TimeTracker extends Activity {
	public static TimeTrackerAdapter mTimeTrackerAdapter;
	public static final int TIME_ENTRY_REQUEST_CODE = 1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_time_tracker);

		ListView listView = (ListView) findViewById(R.id.times_list);
		mTimeTrackerAdapter = new TimeTrackerAdapter();
		listView.setAdapter(mTimeTrackerAdapter);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		super.onCreateOptionsMenu(menu);
		MenuInflater menuInflater = getMenuInflater();
		menuInflater.inflate(R.menu.time_list_menu, menu);
		return true;
	}
	
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		
		if (item.getItemId() == R.id.add_time_menu_item) {
			Intent intent = new Intent(this, AddTimeActivity.class);
			startActivityForResult(intent,TIME_ENTRY_REQUEST_CODE);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == TIME_ENTRY_REQUEST_CODE) {
			if (resultCode == RESULT_OK) {
				String time = data.getStringExtra("time");
				String notes = data.getStringExtra("notes");
				mTimeTrackerAdapter.addTimeRecord(new TimeRecord(time, notes));
				mTimeTrackerAdapter.notifyDataSetChanged();
			}
		}
	}
}
