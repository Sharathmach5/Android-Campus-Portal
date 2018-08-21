package com.example.howlong;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class Total extends Activity {
String s=null;
ListView lv;
ListAdapter adapter;
private String f;
private String t;
private String c;
JSONObject obj;
String sf,st;
String xx=null;
//String url="http://10.0.2.2/howlong/getroute.php";
String url ="http://miniprojectcvr.atwebpages.com/gettotal.php";
ProgressDialog pd;
ArrayList<HashMap<String, String>> list;
EditText a;
Button btn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_total);
		lv = (ListView) findViewById(R.id.listView1);
		
		
		Intent i=getIntent();
        xx= i.getExtras().getString("xx");
		
		
		
					
				LoadUrl L = new LoadUrl();
				L.execute(url);
				
				}
		

			


			public class LoadUrl extends AsyncTask<String, String, String>
			{

				@Override
				protected void onPreExecute() {
					// TODO Auto-generated method stub
					super.onPreExecute();
					pd=new ProgressDialog(Total.this);
					pd.setTitle("Getting Total Details..");
					pd.setMax(100);
					pd.setCancelable(false);
					pd.setIndeterminate(false);
					pd.show();
				}

				

				@Override
				protected String doInBackground(String... params) {
					// TODO Auto-generated method stub
					List<NameValuePair> param=new ArrayList<NameValuePair>();
				//	param.add(new BasicNameValuePair("fr", s));
					param.add(new BasicNameValuePair("gr", xx));
					 list=new ArrayList<HashMap<String,String>>();
					JSONParser jp=new JSONParser();
					JSONObject obj=jp.makeHttpRequest(url, "POST", param);
					Log.d("JSON", obj.toString());
					try {
						
						
						JSONArray arr=obj.getJSONArray("product");
						for(int i=0;i<arr.length();i++)					
						{
							JSONObject js=arr.getJSONObject(i);
							HashMap<String, String> map=new HashMap<String, String>();
							map.put("Total", js.getString ("value"));
						    
			
							list.add(map);
							
						}
						
						
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
					adapter=new SimpleAdapter(Total.this, list, R.layout.customlist, new String[]{"Total"}, new int[]{R.id.textView1});
					  lv.setAdapter(adapter);
				}
				
			}

		}
