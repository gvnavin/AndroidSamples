package com.paad.earthquakeListWidget1;

import java.util.List;

import android.preference.PreferenceActivity;

public class FragmentPreferences extends PreferenceActivity {

  @Override
  public void onBuildHeaders(List<Header> target) {
    loadHeadersFromResource(R.xml.preference_headers, target);
  }
}