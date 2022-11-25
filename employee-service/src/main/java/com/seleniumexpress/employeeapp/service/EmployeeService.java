package com.seleniumexpress.employeeapp.service;

import com.seleniumexpress.employeeapp.entity.Employee;
import com.seleniumexpress.employeeapp.feignclient.AddressClient;
import com.seleniumexpress.employeeapp.repo.EmployeeRepo;
import com.seleniumexpress.employeeapp.response.AddressResponse;
import com.seleniumexpress.employeeapp.response.EmployeeResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class EmployeeService
{
    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    public ModelMapper modelMapper;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WebClient webClient;

    //If we do not want to create the Bean for RestTemplate we can also initialize it inside a constructor like shown below
    /*
    public EmployeeService(@Value("${addressservice.base.url}") String addressBaseUrl,RestTemplateBuilder builder)
    {
        this.restTemplate = builder
                            .rootUri(addressBaseUrl) //instead of concatenating it we can directly add it in here
                            .build();
    }*/

    @Value("${addressservice.base.url}")
    private String addressBaseUrl;

    @Autowired
    AddressClient addressClient;

    /*public Employee getEmployeeById(int id)
    {
        Employee employee = employeeRepo.findById(id).get();
        return employee;
        //instead of using this we can use modelmapper as shown below
    }*/



    public EmployeeResponse getEmployeeById(int id)
    {
        Employee employee = employeeRepo.findById(id).get(); //db call
        EmployeeResponse employeeResponse = modelMapper.map(employee, EmployeeResponse.class);
        AddressResponse addressResponse = addressClient.getAddressByEmployeeId(id);
        employeeResponse.setAddressResponse(addressResponse);
        return employeeResponse;
    }

   /* public EmployeeResponse getEmployeeById(int id)
    {
        //addressResponse -> set data by making a rest api call
        //AddressResponse addressResponse = new AddressResponse();//its a null object

//        EmployeeResponse employeeResponse = new EmployeeResponse();
//        employeeResponse.setId(employee.getId());
//        employeeResponse.setName(employee.getName());
//        employeeResponse.setEmail(employee.getEmail());
//        employeeResponse.setBloodgroup(employee.getBloodgroup());
        //instead of manually typing in the data we can use modelMapper
        Employee employee = employeeRepo.findById(id).get();
        EmployeeResponse employeeResponse=modelMapper.map(employee, EmployeeResponse.class);
        AddressResponse addressResponse=restTemplate.getForObject(addressBaseUrl+"/address/{id}", AddressResponse.class, id);

        //this is a replacement of restTemplate which is blocking in nature, and web client is not blocking in nature
      //  AddressResponse addressResponse1 = webClient
                                           // .get()
                                           // .uri("/address/"+id)
                                           // .retrieve()
                                           // .bodyToMono(AddressResponse.class)
                                           // .block();
        employeeResponse.setAddressResponse(addressResponse);

        return employeeResponse;
    }*/
}
