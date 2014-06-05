package com.scaluo.douban250;

import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MovieActivity extends Activity {
	private TextView txtTitle;
	private TextView txtSummary;
	private NetworkImageView imgCover;
	private RequestQueue queue;
	private String mid;
	private ProgressBar bar;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_movie);
		Intent intent = getIntent();
		mid = String.valueOf(intent.getIntExtra("mid",0));
		txtTitle = (TextView)findViewById(R.id.txtTitle);
		txtSummary = (TextView)findViewById(R.id.txtSummary);
		imgCover = (NetworkImageView)findViewById(R.id.imgCover);
		bar = (ProgressBar)findViewById(R.id.prgBar);
		bar.setVisibility(View.GONE);
		queue = Volley.newRequestQueue(this);
		getMovieInfo();
	}
	
	public void getMovieInfo(){
		String url = "https://api.douban.com/v2/movie/subject/"+mid;
		hidden();
		JsonObjectRequest jsonRequest = new JsonObjectRequest(
				Request.Method.GET,
				url, 
				null,
				new Response.Listener<JSONObject>(){

					@Override
					public void onResponse(JSONObject response) {
					    try {
					    	String name = response.getString("title");
					    	String summary = response.getString("summary");
					    	JSONObject imgobj = response.getJSONObject("images");
					    	String cover = imgobj.getString("large");
					    	Movie move = new Movie(name,summary,cover);
					    	setInterface(move);
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
	
	private void setInterface(Movie move){
		txtTitle.setVisibility(View.VISIBLE);
		txtSummary.setVisibility(View.VISIBLE);
		imgCover.setVisibility(View.VISIBLE);
		Utils.showImageByNetwork(queue,imgCover,move.getCover());
		bar.setVisibility(View.GONE);
		this.txtTitle.setText(move.getName());
		this.txtSummary.setText(move.getSummary());
	}
	
	private void hidden(){
		txtTitle.setVisibility(View.GONE);
		txtSummary.setVisibility(View.GONE);
		imgCover.setVisibility(View.GONE);
		bar.setVisibility(View.VISIBLE);
	}
	
	

}
