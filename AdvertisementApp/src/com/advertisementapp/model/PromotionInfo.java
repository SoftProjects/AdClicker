package com.advertisementapp.model;

public class PromotionInfo {
    private String imgUrl;
    private String backgroundUrl;
    private String activityTitle;
    private int neededScore;
    private int restNum;
    private int restDay;
    private String location;
    
    public String getImgUrl(){
    	return this.imgUrl;
    }
    public void setImgUrl(String imgUrl){
    	this.imgUrl = imgUrl;
    }
    
    public String getBackgroundUrl(){
    	return this.backgroundUrl;
    }
    public void setBackgroundUrl(String backgroundUrl){
    	this.backgroundUrl = backgroundUrl;
    }
    
    public String getActivityTitle(){
    	return this.activityTitle;
    }
    public void setActivityTitle(String activityTitle){
    	this.activityTitle = activityTitle;
    }
    
    public int getNeededScore(){
    	return this.neededScore;
    }
    public void setNeededScore(int score){
    	this.neededScore = score;
    }
    
    public int getRestNum(){
    	return this.restNum;
    }
    public void setRestNum(int restNum){
    	this.restNum = restNum;
    }
    
    public int getRestDay(){
    	return this.restDay;
    }
    public void setRestDay(int restDay){
    	this.restDay = restDay;
    }
    
    public String getLocation(){
    	return this.location;
    }
    public void setLoaction(String location){
    	this.location = location;
    }
}
