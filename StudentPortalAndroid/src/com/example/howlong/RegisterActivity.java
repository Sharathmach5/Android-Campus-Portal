package com.example.howlong;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;



import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class RegisterActivity extends Activity {

	Button a, b;
	EditText n,u,p,l,e,m,h,y;
	Spinner sp;
	String na,us,pa,se,lo,em,mo,s;
	//String url="http://10.0.2.2/howlong/createuser.php";
	String url="http://ambesttechnologies.com/StudentPortal/createstudent.php";
	ProgressDialog pd;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		 a = (Button) findViewById(R.id.savebutton1);
		 h = (EditText) findViewById(R.id.hall);
		 n = (EditText) findViewById(R.id.name);
		 y = (EditText) findViewById(R.id.year);
		 p = (EditText) findViewById(R.id.password);
		 
		 e = (EditText) findViewById(R.id.email);
		 m = (EditText) findViewById(R.id.phone);
		final List<String> list=new ArrayList<String>();
		list.add("CSE");
		list.add("ECE");
		list.add("IT");
		list.add("EEE");
		list.add("MECH");
		

		
		
		final Spinner sp=(Spinner) findViewById(R.id.spinner1);
		ArrayAdapter<String> adp= new ArrayAdapter<String>(this,
		                                android.R.layout.simple_list_item_1,list);
		adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp.setAdapter(adp);
		
		sp.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				s = sp.getItemAtPosition(arg2).toString();
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		a.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(n.equals("")||h.equals("")||p.equals("")||e.equals("")||m.equals("")){
					Toast.makeText(RegisterActivity.this, "Pls fill the fields", Toast.LENGTH_LONG).show();
				}else{
					
					//na,us,pa,se,lo,em,mo
					/*na = n.getText().toString();
					us = u.getText().toString();
					pa = p.getText().toString();
					se = sp.getSelectedItem().toString();
					lo = l.getText().toString();
					em = e.getText().toString();
					mo = m.getText().toString();*/
					LoadUrl l=new LoadUrl();
					l.execute(url);
				}
			}
		});
	}
	public class LoadUrl extends AsyncTask<String, String, String>
	{

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pd=new ProgressDialog(RegisterActivity.this);
			pd.setMessage("Loading...");
			pd.setMax(100);
			pd.setCancelable(false);
			pd.setIndeterminate(false);
			pd.show();
		}

		

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			//na,us,pa,se,lo,em,mo
			List<NameValuePair> param=new ArrayList<NameValuePair>();
			param.add(new BasicNameValuePair("h",h.getText().toString()));
			param.add(new BasicNameValuePair("u",n.getText().toString()));

			param.add(new BasicNameValuePair("e",e.getText().toString()));
			param.add(new BasicNameValuePair("m",m.getText().toString()));
			
			param.add(new BasicNameValuePair("p",p.getText().toString()));
			param.add(new BasicNameValuePair("y",y.getText().toString()));
			param.add(new BasicNameValuePair("s",s));

			
			
			JSONParser jp=new JSONParser();
			JSONObject obj=jp.makeHttpRequest(url, "POST", param);
			Log.d("JSON", obj.toString());
			try {
				final int s=obj.getInt("success");
				Log.d("int", ""+s);
				runOnUiThread( new Runnable() {
					public void run() {
						if(s==1)
						{
						Toast.makeText(RegisterActivity.this, "Created Successfully", Toast.LENGTH_SHORT).show();
						Intent i=new Intent(RegisterActivity.this, LoginActivity.class);
						
						startActivity(i);
						}
						else
						{
							Toast.makeText(RegisterActivity.this, "Failed to Created", Toast.LENGTH_SHORT).show();
							n.setText("");
							h.setText("");
							p.setText("");
							
							e.setText("");
							m.setText("");
						}
					}
				});
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			if(pd!=null && pd.isShowing())
				pd.dismiss();
		}
		
	}


}
