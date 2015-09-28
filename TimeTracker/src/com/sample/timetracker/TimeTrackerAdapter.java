package com.sample.timetracker;

import java.util.ArrayList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class TimeTrackerAdapter extends BaseAdapter {
	private ArrayList<TimeRecord> mTimes = new ArrayList<TimeRecord>();
	
	public TimeTrackerAdapter() {
		mTimes.add(new TimeRecord("38:23", "Feeling good!"));
		mTimes.add(new TimeRecord("49:01", "Tired. Needed more caffeine"));
		mTimes.add(new TimeRecord("26:21", "I’m rocking it!"));
		mTimes.add(new TimeRecord("29:42", "Lost some time on the hills, but pretty good."));
		}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mTimes.size();
	}

	@Override
	public Object getItem(int index) {
		// TODO Auto-generated method stub
		return mTimes.get(index);
	}

	@Override
	public long getItemId(int index) {
		// TODO Auto-generated method stub
		return index;
	}

	@Override
	public View getView(int index, View view, ViewGroup parent) {
		// TODO Auto-generated method stub
		if (view == null) {
			LayoutInflater inflater = LayoutInflater.from(parent.getContext());
			view = inflater.inflate(R.layout.time_list_item, parent, false);
		}
		TimeRecord time = mTimes.get(index);
		TextView timeTextView = (TextView) view.findViewById(R.id.timeTextview);
		timeTextView.setText(time.getTime());
		TextView notesTextView = (TextView) view.findViewById(R.id.notesTextview);
		notesTextView.setText(time.getNotes());
		return view;
	}

	public void addTimeRecord(TimeRecord timeRecord) {
		// TODO Auto-generated method stub
		mTimes.add(timeRecord);
	} 

}
