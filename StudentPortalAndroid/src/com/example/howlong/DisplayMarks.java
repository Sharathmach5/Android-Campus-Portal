package com.example.howlong;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class DisplayMarks extends ListActivity {
	ListView lv;
	ListAdapter adapter;
	private String f;
	private String t;
	private String c;
	JSONObject obj;
	String sf,st;
	ArrayList<String> results = new ArrayList<String>();
	//String url="http://10.0.2.2/howlong/getroute.php";
	String url ="http://miniprojectcvr.atwebpages.com/getmarksnew.php";
	ProgressDialog pd;
	ArrayList<HashMap<String, String>> list;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_marks);
	//	lv = (ListView) findViewById(R.id.listView1);
		sf=getIntent().getStringExtra("s");
		//st=getIntent().getStringExtra("t");
		LoadUrl L = new LoadUrl();
		L.execute(url);
		
		}
	public class LoadUrl extends AsyncTask<String, String, String>
	{

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pd=new ProgressDialog(DisplayMarks.this);
			pd.setTitle("Getting Avaiable Slots..");
			pd.setMax(100);
			pd.setCancelable(false);
			pd.setIndeterminate(false);
			pd.show();
		}

		

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			List<NameValuePair> param=new ArrayList<NameValuePair>();
			param.add(new BasicNameValuePair("fr", sf));
			//param.add(new BasicNameValuePair("to", st));
			 list=new ArrayList<HashMap<String,String>>();
			JSONParser jp=new JSONParser();
			JSONObject obj=jp.makeHttpRequest(url, "POST", param);
			Log.d("JSON", obj.toString());
			try {
				System.out.println(obj.length());
				for(int x=0;x<obj.length();x++){
				JSONArray arr=obj.getJSONArray("product");
				for(int i=0;i<arr.length();i++)					
				{
					JSONObject js=arr.getJSONObject(i);
					HashMap<String, String> map=new HashMap<String, String>();
					//map.put("two", js.getString ("value"));
				    //map.put("phone", js.getString  ("phone"));
					//map.put("email", js.getString("email"));
					//map.put("address", js.getString ("address"));
					results.add(js.getString ("markslink"));
					//list.add(map);
					
				}
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

			  setListAdapter(new ArrayAdapter<String>(getApplicationContext(),
		                android.R.layout.simple_list_item_1, results));
		        
		     final   ListView lv=getListView();
		        lv.setTextFilterEnabled(true);
		        lv.setOnItemClickListener(new OnItemClickListener() {
		            public void onItemClick(AdapterView<?> parent, View view,int position, long id) {

		               //If you wanna send any data to nextActicity.class you can use
		               
		            	//as.add(results.get(position));
		                 Intent anotherActivityIntent = new Intent(getApplicationContext(), MarksDisplay.class);
		             //Collections.sort(results);
		                
		               String na=(String)(lv.getItemAtPosition(position));
		                 anotherActivityIntent.putExtra("xx",na);
		                 
		                
		                 startActivity(anotherActivityIntent); 
		            
		            }
		          });

		//	adapter=new SimpleAdapter(DisplayMarks.this, list, R.layout.customlist, new String[]{"two"}, new int[]{R.id.textView1});
			//  lv.setAdapter(adapter);
			  
		}
		
	}

}
