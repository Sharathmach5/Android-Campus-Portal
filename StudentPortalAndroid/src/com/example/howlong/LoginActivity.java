package com.example.howlong;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.Toast;

@SuppressLint("NewApi")
public class LoginActivity extends Activity {
	//String url="http://10.0.2.2/howlong/login.php";
	String url="http://miniprojectcvr.atwebpages.com/studentlogin.php";
	ProgressDialog pd;
	ArrayList<HashMap<String, String>> list;
EditText user, pass;
private Button a;
private Button b;
 public String u;
public String p;
 JSONObject obj;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		user = (EditText) findViewById(R.id.editText1);
		pass = (EditText) findViewById(R.id.editText2);
		
		a = (Button) findViewById(R.id.logbutton1);
		b = (Button) findViewById(R.id.logbutton2);
		
		b.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				user.setText("");
				pass.setText("");
			}
		});
	
	a.setOnClickListener(new OnClickListener() {
		
		

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
			//if(user.getText().toString().equals("")||pass.getText().toString().isEmpty()){
			if(TextUtils.isEmpty(user.getText().toString().trim()) || TextUtils.isEmpty(pass.getText().toString().trim())) {	
				Toast.makeText(LoginActivity.this, "PLz enter the fields..!", Toast.LENGTH_LONG).show();
			}else{
				
				//have check here with db
				
				 u = user.getText().toString();
				 p = pass.getText().toString();
				 LoadUrl l = new LoadUrl();
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
			pd=new ProgressDialog(LoginActivity.this);
			pd.setTitle("Loading...");
			pd.setMax(100);
			pd.setCancelable(false);
			pd.setIndeterminate(false);
			pd.show();
		}

		

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			List<NameValuePair> param=new ArrayList<NameValuePair>();
			param.add(new BasicNameValuePair("u", u));
			param.add(new BasicNameValuePair("p", p));
			 list=new ArrayList<HashMap<String,String>>();
			JSONParser jp=new JSONParser();
			 obj=jp.makeHttpRequest(url, "POST", param);
			Log.d("JSON", obj.toString());
			runOnUiThread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					try {
						if(obj.getInt("success")==1){
							
							Toast.makeText(LoginActivity.this, "Welcome..!", 200).show();
							Intent i = new Intent(LoginActivity.this,StudentHome.class);
							i.putExtra("xx",u);
							startActivity(i);
							
						}else{
							Toast.makeText(LoginActivity.this, "Wrong..!", 200).show();
						}
						
						
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			return null;
		}
		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			if(pd!=null && pd.isShowing())
				pd.dismiss();
		//	ListAdapter adapter=new SimpleAdapter(GetAllActivity.this, list, R.layout.customlist, new String[]{"id","name","age"}, new int[]{R.id.id_textView1,R.id.name_textView2,R.id.age_textView3});
			//lv.setAdapter(adapter);
		}
		
	}

}
