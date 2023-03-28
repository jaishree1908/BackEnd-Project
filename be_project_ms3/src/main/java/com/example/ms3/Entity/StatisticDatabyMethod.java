package com.example.ms3.Entity;

import java.util.HashMap;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("StatisticDataMethod")
public class StatisticDatabyMethod {

	public String method;
	public HashMap<Integer, Integer> methodcount=new HashMap<Integer, Integer>();
	{
		for(int i=0;i<15;i++)
		{
			methodcount.put(i,0);
		}
	}
	public float mintime=999999;
	public float maxtime=-1;
	public int count=0;
	public float avgtime=0;
	
	public StatisticDatabyMethod() {}
	
	public String getMethod() {
		return method;
	}
	public StatisticDatabyMethod(String method, HashMap<Integer, Integer> methodcount, float mintime, float maxtime,
		int count, float avgtime) {
	super();
	this.method = method;
	this.methodcount = methodcount;
	this.mintime = mintime;
	this.maxtime = maxtime;
	this.count = count;
	this.avgtime = avgtime;
}
	public void setMethod(String method) {
		this.method = method;
	}


	public float getMintime() {
		return mintime;
	}


	public HashMap<Integer, Integer> getMethodcount() {
		return methodcount;
	}

	public void setMethodcount(HashMap<Integer, Integer> methodcount) {
		this.methodcount = methodcount;
	}

	public void setMintime(float mintime) {
		this.mintime = mintime;
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


	public void setCount(int count) {
		this.count = count;
	}


	public float getAvgtime() {
		return avgtime;
	}


	public void setAvgtime(float avgtime) {
		this.avgtime = avgtime;
	}


	@Override
	public String toString() {
		return "StatisticDatabyMethod [method=" + method + ", methodcount=" + methodcount + ", mintime=" + mintime
				+ ", maxtime=" + maxtime + ", count=" + count + ", avgtime=" + avgtime + "]";
	}
	
}
