package com.adClick.sdk.data.httplinker;

import java.util.ArrayList;
import java.util.List;


public class HttpManager {
//	taskQueue
	List<ImageLoader> imageLoaders = new ArrayList<ImageLoader>();
	public HttpManager(String imageCacheDir,int numOfImageLoader){
		for (int i = 0; i < numOfImageLoader; i++) {
			imageLoaders.add(new ImageLoader(imageCacheDir));
		}
	}
}
