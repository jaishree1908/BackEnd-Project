package com.example.ms3.Entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("StatisticInfo")
public class StatisticInfo implements Serializable{
/**
	 * 
	 */
	private static final long serialVersionUID = -8462321702478952319L;	

@Id
private int id;
private String name;
private String method;
private LocalDateTime entrydatetime;
private LocalDateTime exitdatetime;


public StatisticInfo()
{}

public StatisticInfo(int id, String name, String method, LocalDateTime entrydatetime, LocalDateTime exitdatetime) {

	this.id = id;
	this.name = name;
	this.method = method;
	this.entrydatetime = entrydatetime;
	this.exitdatetime = exitdatetime;
}

public StatisticInfo(int aid, int id, String name, String method, LocalDateTime entrydatetime,
		LocalDateTime exitdatetime) {
	super();
	this.id = id;
	this.name = name;
	this.method = method;
	this.entrydatetime = entrydatetime;
	this.exitdatetime = exitdatetime;
}


public StatisticInfo(int id2, String name2, String method2) {
	// TODO Auto-generated constructor stub
	super();
	this.id = id2;
	this.name = name2;
	this.method = method2;
}

public int getId() {
	return id;
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
public String getMethod() {
	return method;
}
public void setMethod(String method) {
	this.method = method;
}
public LocalDateTime getEntrydatetime() {
	return entrydatetime;
}
public void setEntrydatetime(LocalDateTime entrydatetime) {
	this.entrydatetime = entrydatetime;
}
public LocalDateTime getExitdatetime() {
	return exitdatetime;
}
public void setExitdatetime(LocalDateTime exitdatetime) {
	this.exitdatetime = exitdatetime;
}

@Override
public String toString() {
	return "StatisticInfo [id=" + id + ", name=" + name + ", method=" + method + ", entrydatetime=" + entrydatetime
			+ ", exitdatetime=" + exitdatetime + "]";
}

}

