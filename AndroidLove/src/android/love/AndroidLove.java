package android.love;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class AndroidLove extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    public void onLoveButtonClicked(View view) {
    	//doesn’t do anything yet
    	TextView haikuTextView = (TextView) findViewById(R.id.haikuTextView);
    	haikuTextView.setVisibility(View.VISIBLE);
    	
    	}
}
