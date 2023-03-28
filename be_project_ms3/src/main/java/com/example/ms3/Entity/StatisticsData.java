package com.example.ms3.Entity;

import java.io.Serializable;
import org.springframework.data.redis.core.RedisHash;
import jakarta.persistence.Id;

@RedisHash("StatisticData")
public class StatisticsData implements Serializable{

	private static final long serialVersionUID = -3443479990406951585L;
	@Id
	private int id;
	private float mintime=99999;
	private float maxtime=-1;
	private int count=0;
	private float avgtime=0;

	
	public StatisticsData(int id, float mintime, float maxtime, int count, float avgtime) {
		super();
		this.id = id;
		this.mintime = mintime;
		this.maxtime = maxtime;
		this.count = count;
		this.avgtime = avgtime;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public StatisticsData() {}
	public StatisticsData(float mintime, float maxtime, int count, float avgtime) {
		super();

		this.mintime = mintime;
		this.maxtime = maxtime;
		this.count = count;
		this.avgtime = avgtime;
	}
	public float getMintime() {
		return mintime;
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
		return "StatisticsData [id=" + id + ", mintime=" + mintime + ", maxtime=" + maxtime + ", count=" + count
				+ ", avgtime=" + avgtime + "]";
	}
	
}
