package com.example.ms2.publisher;

import com.example.ms1.dto.Employee;
import com.example.ms3.Entity.StatisticInfo;

public interface MessagePublisher {
	void publish(StatisticInfo stsinfo);
}
