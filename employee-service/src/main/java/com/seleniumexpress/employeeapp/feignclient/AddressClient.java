package com.seleniumexpress.employeeapp.feignclient;

import com.seleniumexpress.employeeapp.response.AddressResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//http://localhost:8081/address-app/api/address/1 -- we want to call this url
@FeignClient(name = "address-service", url="http://localhost:8081/address-app/api")
public interface AddressClient
{
    @GetMapping("/address/{id}")
    AddressResponse getAddressByEmployeeId(@PathVariable("id") int id);

    //Spring will create the implementation of this class and will inject the bean in the EmployeeService.java class where
    // AddressClient addressClient is autowired.

    //AddressClientProxy will extend the AddressClient and will override the getAddressByEmployeeId() method and give it an implementation

    //FeignClient is a declarative Rest client

    //You can dump more methods inside this interface , if address-service has some more endpoints (api's) you can design methods here to call those api's
    //Feign client can be easily integrated with other spring cloud projects (Eureka, Gateway. Load balancer)
}
