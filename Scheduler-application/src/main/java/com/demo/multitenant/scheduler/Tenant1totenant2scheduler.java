package com.demo.multitenant.scheduler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.demo.multitenant.feign.FeignServiceUtil;
import com.demo.multitenant.model.Employee;

@Component
public class Tenant1totenant2scheduler {

	@Autowired
	FeignServiceUtil feignServiceUtil;

	@Scheduled(fixedRate = 600000)
	public void executeSyncProcess() {

		List<Employee> empList = feignServiceUtil.getAll("tenant1");
		if (empList != null && empList.size() > 0) {
			
			empList.stream().forEach(emp -> {
				System.out.println("emp size "+emp.getName());
				feignServiceUtil.save(emp,"tenant2");
			});
		}

	}
}
