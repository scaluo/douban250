package com.scaluo.douban250;
import java.util.ArrayList;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.NetworkImageView;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MoviesAdapter extends BaseAdapter{

	private ArrayList<MovieItem> movies;
	private Context context;
	private RequestQueue queue;
	
	public MoviesAdapter(Context context,ArrayList<MovieItem> movies,RequestQueue queue){
		this.context = context;
		this.movies = movies;
		this.queue = queue;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return movies.size();
	}

	@Override
	public Object getItem(int pos) {
		// TODO Auto-generated method stub
		return movies.get(pos);
	}

	@Override
	public long getItemId(int pos) {
		// TODO Auto-generated method stub
		return pos;
	}

	@Override
	public View getView(int pos, View convertView, ViewGroup parent) {
		MovieItem item = movies.get(pos);
		
		View view;
		if (convertView==null){
			LayoutInflater inflater = LayoutInflater.from(context);
			view = inflater.inflate(R.layout.move_item, null);
		}
		else{
			view = convertView;
		}
		fillItemView(view,item);
		return view;
	}
	
	private void fillItemView(View view,MovieItem item){
		NetworkImageView imgNet = (NetworkImageView)view.findViewById(R.id.imgSmall);
		TextView txtTitle = (TextView)view.findViewById(R.id.txtTitle);
		TextView txtYear = (TextView)view.findViewById(R.id.txtYear);
		TextView txtScore = (TextView)view.findViewById(R.id.txtScore);
		txtTitle.setText(item.getName());
		
		txtYear.setText("放映年份:"+String.valueOf(item.getPubYear()));
		txtScore.setText("豆瓣评分:"+item.getScore());
		Utils.showImageByNetwork(queue,imgNet,item.getImgSmall());
	}
	
	
	
	
}
