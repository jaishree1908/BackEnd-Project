package com.example.ms3.Service;

import java.time.temporal.ChronoUnit;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.ms3.Entity.StatisticDatabyID;
import com.example.ms3.Entity.StatisticDatabyMethod;
import com.example.ms3.Entity.StatisticInfo;
import com.example.ms3.Entity.StatisticsData;
import com.example.ms3.Repository.StatisticDataDao;
import com.fasterxml.jackson.core.JsonProcessingException;

public class Substatsservice {
	
	Logger logger= Logger.getLogger(Substatsservice.class.getName());
	
	@Autowired
	private StatisticDataDao statisticsdatadao;
	public StatisticDatabyMethod setminstatisticdatamethod(StatisticInfo stsinfo2, StatisticDatabyMethod statisticsdatabymethod)
	{
		logger.info("Entering setminstatisticdata() "+stsinfo2+" "+statisticsdatabymethod);
		float min_diffintime = ChronoUnit.MILLIS.between(stsinfo2.getEntrydatetime(),stsinfo2.getExitdatetime());
		float minOldtime = statisticsdatabymethod.getMintime() * 1000;
		if (min_diffintime < minOldtime) {
		statisticsdatabymethod.setMintime(min_diffintime / 1000);
		}
		logger.info("Exiting setminstatisticdata() "+stsinfo2+" "+statisticsdatabymethod);

		return statisticsdatabymethod;
	}

	public StatisticDatabyMethod setmaxstatisticdatamethod(StatisticInfo stsinfo2, StatisticDatabyMethod statisticsdatabymethod)
	{
	// set max time diff
		logger.info("Entering setmaxstatisticdata() "+stsinfo2+" "+statisticsdatabymethod);
		float max_diffintime = ChronoUnit.MILLIS.between(stsinfo2.getEntrydatetime(),stsinfo2.getExitdatetime());
		float maxOldtime = statisticsdatabymethod.getMaxtime() * 1000;
		if (max_diffintime > maxOldtime) {
		statisticsdatabymethod.setMaxtime(max_diffintime / 1000);
		}
		logger.info("Exiting setmaxstatisticdata() "+stsinfo2+" "+statisticsdatabymethod);

		return statisticsdatabymethod;
	}
	
	public StatisticDatabyMethod setcountavgstatisticdatamethod(StatisticInfo stsinfo2, StatisticDatabyMethod statisticsdatabymethod)
	{
	logger.info("Entering setcountavgstatisticdata() "+stsinfo2+" "+statisticsdatabymethod);
	float oldSumtime = statisticsdatabymethod.getAvgtime() * statisticsdatabymethod.getCount() * 1000;
	statisticsdatabymethod.setCount(statisticsdatabymethod.getCount() + 1);
	float timediff = ChronoUnit.MILLIS.between(stsinfo2.getEntrydatetime(), stsinfo2.getExitdatetime());
	statisticsdatabymethod.setAvgtime(((oldSumtime + timediff) / statisticsdatabymethod.getCount()) / 1000);
	logger.info("Exiting setcountavgstatisticdata() "+stsinfo2+" "+statisticsdatabymethod);

	return statisticsdatabymethod;
	}

	public StatisticsData setminstatisticdata(StatisticInfo stsinfo2, StatisticsData statisticsdata)
	{
		logger.info("Entering setminstatisticdata() "+stsinfo2+" "+statisticsdata);

		float min_diffintime = ChronoUnit.MILLIS.between(stsinfo2.getEntrydatetime(),stsinfo2.getExitdatetime());
		float minOldtime = statisticsdata.getMintime() * 1000;
		if (min_diffintime < minOldtime) {
			statisticsdata.setMintime(min_diffintime / 1000);
		}
		
		logger.info("Exiting setminstatisticdata() "+stsinfo2+" "+statisticsdata);

		return statisticsdata;
	}

	public StatisticsData setmaxstatisticdata(StatisticInfo stsinfo2, StatisticsData statisticsdata)
	{
	// set max time diff
		logger.info("Entering setmaxstatisticdata() "+stsinfo2+" "+statisticsdata);
		
		float max_diffintime = ChronoUnit.MILLIS.between(stsinfo2.getEntrydatetime(),stsinfo2.getExitdatetime());
		float maxOldtime = statisticsdata.getMaxtime() * 1000;
		if (max_diffintime > maxOldtime) {
			statisticsdata.setMaxtime(max_diffintime / 1000);
		}
		logger.info("Exiting setmaxstatisticdata() "+stsinfo2+" "+statisticsdata);

		return statisticsdata;
	}
	
	public StatisticsData setcountavgstatisticdata(StatisticInfo stsinfo2, StatisticsData statisticsdata) throws JsonProcessingException
	{
		logger.info("Entering setcountavgstatisticdata() "+stsinfo2+" "+statisticsdata);
		
	float oldSumtime = statisticsdata.getAvgtime() * statisticsdata.getCount() * 1000;
	statisticsdata.setCount(statisticsdata.getCount() + 1);
	float timediff = ChronoUnit.MILLIS.between(stsinfo2.getEntrydatetime(), stsinfo2.getExitdatetime());
	statisticsdata.setAvgtime(((oldSumtime + timediff) / statisticsdata.getCount()) / 1000);
	statisticsdatadao.save(statisticsdata); 
	logger.info("Exiting setcountavgstatisticdata() "+stsinfo2+" "+statisticsdata);

	return statisticsdata;
	}
	
	public StatisticDatabyID setminstatisticdataid(StatisticInfo stsinfo2, StatisticDatabyID statisticsdatabyid1)
	{
		logger.info("Entering setminstatisticdataid() "+stsinfo2+" "+statisticsdatabyid1);

		float min_diffintime = ChronoUnit.MILLIS.between(stsinfo2.getEntrydatetime(),stsinfo2.getExitdatetime());
		float minOldtime = statisticsdatabyid1.getMintime() * 1000;
		if (min_diffintime < minOldtime) {
			statisticsdatabyid1.setMintime(min_diffintime / 1000);
		}
		
		logger.info("Exiting setminstatisticdataid() "+stsinfo2+" "+statisticsdatabyid1);

		return statisticsdatabyid1;
	}

	public StatisticDatabyID setmaxstatisticdataid(StatisticInfo stsinfo2, StatisticDatabyID statisticsdatabyid1)
	{
	// set max time diff
		logger.info("Entering setmaxstatisticdataid() "+stsinfo2+" "+statisticsdatabyid1);
		
		float max_diffintime = ChronoUnit.MILLIS.between(stsinfo2.getEntrydatetime(),stsinfo2.getExitdatetime());
		float maxOldtime = statisticsdatabyid1.getMaxtime() * 1000;
		if (max_diffintime > maxOldtime) {
			statisticsdatabyid1.setMaxtime(max_diffintime / 1000);
		}
		logger.info("Exiting setmaxstatisticdataid() "+stsinfo2+" "+statisticsdatabyid1);

		return statisticsdatabyid1;
	}
	
	public StatisticDatabyID setcountavgstatisticdataid(StatisticInfo stsinfo2, StatisticDatabyID statisticsdatabyid1)
	{
	
		logger.info("Entering setcountavgstatisticdataid() "+stsinfo2+" "+statisticsdatabyid1);
		
	float oldSumtime = statisticsdatabyid1.getAvgtime() * statisticsdatabyid1.getCount() * 1000;
	statisticsdatabyid1.setCount(statisticsdatabyid1.getCount() + 1);
	float timediff = ChronoUnit.MILLIS.between(stsinfo2.getEntrydatetime(), stsinfo2.getExitdatetime());
	statisticsdatabyid1.setAvgtime(((oldSumtime + timediff) / statisticsdatabyid1.getCount()) / 1000);
	logger.info("Exiting setcountavgstatisticdataid() "+stsinfo2+" "+statisticsdatabyid1);

	return statisticsdatabyid1;
	}
}
