package com.adClick.sdk.data.httplinker;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class ImageLoader {
    private static HashMap<String, SoftReference<Bitmap>> imageCache = new HashMap<String, SoftReference<Bitmap>>();    
    File imageCacheDir;    
    String imageCacheDirName;
    private boolean isWorking ;

	/**
     * 
     * @param imageCacheDir
     *imageCacheDir = new File("/mnt/sdcard/test/");    
     */
    public ImageLoader(String imageCacheDir)    
    {    
        imageCacheDirName=imageCacheDir;
        isWorking = false;
        this.imageCacheDir = new File(imageCacheDir);
        if(!this.imageCacheDir.exists()) {    
        	this.imageCacheDir.mkdirs();    
        }    
        
    }    
    
    public Bitmap loadLocalBitmap(String imageURL){
    	
		if (imageCache.containsKey(imageURL)) {
			SoftReference<Bitmap> reference = imageCache.get(imageURL);
			Bitmap bitmap = reference.get();
			if (bitmap != null) {
				return bitmap;
			}
		}else {    
            String bitmapName = getBitmapName(imageURL);    
            File[] cacheFiles =(imageCacheDir.listFiles());    
			if (cacheFiles != null) {
				for (File f:cacheFiles) {
					if (bitmapName.equals(f.getName())) {
						return BitmapFactory.decodeFile(f.getAbsolutePath());
					}
				}
			}
        }
		return null;    
	}
    
    private String getBitmapName(String imageURL){
        String bitmapName = imageURL.substring(imageURL.lastIndexOf("/") + 1);  
        return bitmapName;
    }
    
    public Bitmap downloadBitmap(String imageURL) throws IOException{
    	isWorking = true;
		URL url = new URL(imageURL);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		InputStream bitmapIs = connection.getInputStream();
		Bitmap bitmap = BitmapFactory.decodeStream(bitmapIs);    
		
        imageCache.put(imageURL, new SoftReference<Bitmap>(bitmap)); 
        
        File bitmapFile = new File(imageCacheDirName+"/"+getBitmapName(imageURL));    
        if(!bitmapFile.exists()) bitmapFile.createNewFile();    
      
        FileOutputStream fos = new FileOutputStream(bitmapFile);    
        bitmap.compress(Bitmap.CompressFormat.PNG,100, fos);    
        fos.close();
        
    	isWorking = false;
		return bitmap;    
	}

    public boolean isWorking() {
		return isWorking;
	}
}
