package com.scaluo.douban250;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.ImageLoader.ImageCache;

public class Utils {
	static public ImageCache getImageCache(){
		final LruCache<String,Bitmap> lruCache = new LruCache<String,Bitmap>(20);
		ImageCache imageCache = new ImageCache(){
			@Override
			public Bitmap getBitmap(String key) {
				// TODO Auto-generated method stub
				return lruCache.get(key);
			}
			@Override
			public void putBitmap(String key, Bitmap value) {
				// TODO Auto-generated method stub
				lruCache.put(key, value);
			}
		};
		return imageCache;
	}
	
	static public void showImageByNetwork(RequestQueue queue,NetworkImageView img,String url){
		ImageLoader mImageLoader = new ImageLoader(queue,Utils.getImageCache());
		img.setTag(url);
		img.setImageUrl(url,mImageLoader);
	}

}
