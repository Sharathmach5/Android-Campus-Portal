package com.example.howlong;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Attendance extends Activity {
	private Button a1;
	private Button a2;
	String xx=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_attendance);
		a1 = (Button) findViewById(R.id.button1);
		 a2 = (Button) findViewById(R.id.button2);
		 Intent i=getIntent();
	        xx= i.getExtras().getString("xx");
		 a1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				Intent i = new Intent(Attendance.this,Month.class);
				i.putExtra("xx",xx);
				startActivity(i);
			}
		});
		 a2.setOnClickListener(new OnClickListener() {
				 
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					
					Intent ii = new Intent(Attendance.this,Total.class);
					ii.putExtra("xx",xx);
					startActivity(ii);
				}
			});
		 
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.attendance, menu);
		return true;
	}

}
