package com.example.howlong;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	private Button a1;
	private Button a2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	 a1 = (Button) findViewById(R.id.button1);
	// a2 = (Button) findViewById(R.id.button2);
	
	 a1.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			
			Intent i = new Intent(MainActivity.this,LoginActivity.class);
			startActivity(i);
		}
	});
	 	 
	}

	

}
