package com.example.howlong;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class StudentHome extends Activity {
	private Button a1;
	private Button a2;
	public String xx=null;
	private Button a3;
	private Button a4;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_student_home);
		 a1 = (Button) findViewById(R.id.button1);
		 a2 = (Button) findViewById(R.id.button2);
		 a3 = (Button) findViewById(R.id.button3);
		 a4 = (Button) findViewById(R.id.button4);

Intent i=getIntent();
        xx= i.getExtras().getString("xx");

		 a1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				Intent i = new Intent(StudentHome.this,Attendance.class);
				i.putExtra("xx",xx);
				startActivity(i);
			}
		});
		 a2.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					
					Intent ii = new Intent(StudentHome.this,Marks.class);
					startActivity(ii);
				}
			});
		 a3.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					
					Intent i = new Intent(StudentHome.this,Events.class);
					startActivity(i);
				}
			});
			 a4.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						
						Intent ii = new Intent(StudentHome.this,Placements.class);
						startActivity(ii);
					}
				});
			 Button logout=(Button)findViewById(R.id.logout);
			  logout.setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View v) {
			 			// TODO Auto-generated method stub
						Toast.makeText(getApplicationContext(), "logging out...!!", Toast.LENGTH_LONG).show();
						Intent ad= new Intent(getApplicationContext(),MainActivity.class);
						startActivity(ad);
					}
		        	 });

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.student_home, menu);
		return true;
	}

}
