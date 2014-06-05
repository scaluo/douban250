package com.scaluo.douban250;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.ProgressBar;

public class MainActivity extends Activity {

	private ArrayList<MovieItem> movies;
	private MoviesAdapter adapter;
	private RequestQueue queue;
	private ListView lstMovies;
	private ProgressBar prgBar;
	private int rescount;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		movies = new ArrayList<MovieItem>();
		queue = Volley.newRequestQueue(this);
		lstMovies = (ListView)findViewById(R.id.lstMovies);
		prgBar = (ProgressBar)findViewById(R.id.prgBar);
		prgBar.setVisibility(View.GONE);
		init();
		
	}

	
	private void init(){
		adapter = new MoviesAdapter(this,movies,queue);
		lstMovies.setAdapter(adapter);
		lstMovies.setOnItemClickListener(new OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView<?> view, View arg1, int pos,
					long arg3) {
				MovieItem item = (MovieItem)lstMovies.getItemAtPosition(pos);
				Intent intent = new Intent();
				intent.putExtra("mid",item.getMid());
				intent.setClass(MainActivity.this,MovieActivity.class);
				startActivity(intent);
				
			}
			
		});
		getMovies250Info();
	}
	
	private void getMovies250Info(){
		prgBar.setVisibility(View.VISIBLE);
		String url = "https://api.douban.com/v2/movie/top250?count=100&start=";
		movies.clear();
		rescount = 0;

		for (int k=0;k<3;k++){
			
			String murl = url+String.valueOf(k*100);
			JsonObjectRequest jsonRequest = new JsonObjectRequest(
				Request.Method.GET,
				murl, 
				null,
				new Response.Listener<JSONObject>(){

					@Override
					public void onResponse(JSONObject response) {
						// TODO Auto-generated method stub
						
						try {
							JSONArray jsonArray = response.getJSONArray("subjects");
							
							int start = response.getInt("start");
							for (int i=0;i<jsonArray.length();i++){
								JSONObject item = jsonArray.getJSONObject(i);
								String mName = item.getString("title");
								String mOrgName = item.getString("original_title");
								int mid = item.getInt("id");
								JSONObject rating = item.getJSONObject("rating");
								String mScore = rating.getString("average");
								int mPub = item.getInt("year");
								JSONObject images = item.getJSONObject("images");
								String mPic = images.getString("small");
								
								int order = start+i;
								MovieItem movieitem = new MovieItem(order,mid,mName,mOrgName,mScore,mPub,mPic);
								movies.add(movieitem);
								
							}
							rescount++;
							if (rescount==3)
							{
								Collections.sort(movies,new Comparator<Object>(){
									@Override
									public int compare(Object arg0, Object arg1) {
										// TODO Auto-generated method stub
										MovieItem item1 = (MovieItem)arg0;
										MovieItem item2 = (MovieItem)arg1;
										return item1.getOrder()-item2.getOrder();
									}
										
								});
								
								adapter.notifyDataSetChanged();
								prgBar.setVisibility(View.GONE);
								
							}
							
							
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
					
				},
				new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						// TODO Auto-generated method stub
						Log.e("ERROR:",error.getMessage());
					}
				});
			queue.add(jsonRequest);
		}
	}
	

}
