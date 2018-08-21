package com.example.howlong;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MarksDisplay extends Activity {
String sf=null;
Button a1=null;	
@Override
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_marks_display);
		
		sf=getIntent().getStringExtra("xx");
		Intent browserIntent = new Intent(
				Intent.ACTION_VIEW,
				Uri.parse(sf));
		startActivity(browserIntent);
		a1 = (Button) findViewById(R.id.button1);
		// a2 = (Button) findViewById(R.id.button2);
		
		 a1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				Intent anotherActivityIntent = new Intent(getApplicationContext(), Marks.class);
		        //Collections.sort(results);
		           
				startActivity(anotherActivityIntent);	}
		});
		
            
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.marks_display, menu);
		return true;
	}

}
