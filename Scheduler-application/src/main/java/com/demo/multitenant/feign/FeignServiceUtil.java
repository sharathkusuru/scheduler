
package com.demo.multitenant.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.demo.multitenant.model.Employee;

@FeignClient(name = "Employee", url = "http://localhost:8081")
public interface FeignServiceUtil {

	@GetMapping("/getEmployee/all")
	List<Employee> getAll(@RequestHeader(name="X-TenantID") String XTenantId);
	
	@PostMapping(value = "/createEmployee" , consumes= { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<HttpStatus> save(@RequestBody Employee emp,@RequestHeader(name="X-TenantID") String XTenantId);

}
