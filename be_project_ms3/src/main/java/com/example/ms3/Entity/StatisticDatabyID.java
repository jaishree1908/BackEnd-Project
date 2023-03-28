package com.example.ms3.Entity;

import java.util.HashMap;

import org.springframework.data.redis.core.RedisHash;

@RedisHash("StatisticInfoID")
public class StatisticDatabyID {

	public int id=-999;
	public String name="";
	public HashMap<String, Integer> mapcount=new HashMap<String, Integer>();
	{
		mapcount.put("login",0);
		mapcount.put("logout",0);
		mapcount.put("search",0);
		mapcount.put("error",0);
		mapcount.put("request",0);
		}
	public float mintime=99999;
	public float maxtime=-1;
	public int count=0;
	public float avgtime=0;

	
	
	public StatisticDatabyID() {}
	public int getId() {
		return id;
	}

	public StatisticDatabyID(int id, String name, int logincount, int logoutcount, int searchcount, int requestcount,
			int errorcount , float mintime, float maxtime, int count,  float avgtime) {
		this.id = id;
		this.name = name;
		this.mapcount.put("login",logincount);
		this.mapcount.put("request",requestcount);
		this.mapcount.put("search",searchcount);
		this.mapcount.put("logout",logoutcount);
		this.mapcount.put("error",errorcount);
		this.mintime = mintime;
		this.maxtime = maxtime;
		this.count = count;
		this.avgtime = avgtime;
		
	}
	
	@Override
	public String toString() {
		return "StatisticDatabyID [id=" + id + ", name=" + name + ", mapcount=" + mapcount + ", mintime=" + mintime
				+ ", maxtime=" + maxtime + ", count=" + count + ", avgtime=" + avgtime + "]";
	}

	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	public float getMintime() {
		return mintime;
	}

	public void setMintime(float mintime) {
		this.mintime = mintime;
	}
	public HashMap<String, Integer> getMapcount() {
		return mapcount;
	}
	public void setMapcount(HashMap<String, Integer> mapcount) {
		this.mapcount=mapcount;
	}
	public float getMaxtime() {
		return maxtime;
	}
	public void setMaxtime(float maxtime) {
		this.maxtime = maxtime;
	}
	public int getCount() {
		return count;
	}
	public StatisticDatabyID(int id, String name, HashMap<String, Integer> mapcount, float mintime, float maxtime,
			int count, float avgtime) {
		super();
		this.id = id;
		this.name = name;
		this.mapcount = mapcount;
		this.mintime = mintime;
		this.maxtime = maxtime;
		this.count = count;
		this.avgtime = avgtime;
	}
	
	
	
	public void setCount(int count) {
		this.count = count;
	}
	public float getAvgtime() {
		return avgtime;
	}
	public void setAvgtime(float avgtime) {
		this.avgtime = avgtime;
	}
	
	
}
