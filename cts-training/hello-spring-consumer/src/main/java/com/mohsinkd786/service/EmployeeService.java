package com.mohsinkd786.service;

import com.mohsinkd786.config.HelloSettings;
import com.mohsinkd786.dto.EmployeeDto;
import com.mohsinkd786.dto.EmployeeRequest;
import com.mohsinkd786.dto.RawUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

//@Component
@Service
public class EmployeeService {

    private RestTemplate restTemplate;

    private HelloSettings helloSettings;

    @Autowired
    public void setHelloSettings(HelloSettings helloSettings) {
        this.helloSettings = helloSettings;
    }

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<EmployeeDto> getAllEmployees(){
        ResponseEntity<RawUser[]> responseEntity = restTemplate.getForEntity(helloSettings.getHelloBaseUrl().concat("v1/users"),RawUser[].class);
        RawUser[] body = responseEntity.getBody();
        List<EmployeeDto> employeeDtos = objectsToDtoList(body);
        return employeeDtos;
    }


    public boolean saveEmployee(EmployeeRequest request){
        ResponseEntity<RawUser> responseEntity = restTemplate.postForEntity(helloSettings.getHelloBaseUrl().concat("v1/users"),request,RawUser.class);
        if(Objects.nonNull(responseEntity)){
            return true;
        }
        return false;
    }

    public boolean updateEmployee(String empId,EmployeeDto employeeDto){

        String url ="";
        // put call
        //Map<String,String> headers = new HashMap<>();
        HttpEntity request = new HttpEntity(null);
        ResponseEntity<String> responseEntity =  restTemplate.exchange(url, HttpMethod.PUT,request,String.class);

        return false;
    }

    public EmployeeDto getEmployeeById(String id){
        String url = helloSettings.getHelloBaseUrl().concat("v1/users/").concat(id);

        ResponseEntity<RawUser> responseEntity = restTemplate.getForEntity(url,RawUser.class);
        try{
            EmployeeDto employeeDto = objectToDto(responseEntity.getBody());
            return employeeDto;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    private EmployeeDto objectToDto(Object obj) throws Exception{
        RawUser rawUser = (RawUser)obj;
        if(Objects.nonNull(rawUser)){
            String[] names = rawUser.getName().split(" ");
            EmployeeDto employeeDto = new EmployeeDto(rawUser.getId(),names[0],names[1],rawUser.getEmail());
            return employeeDto;
        }else{
            throw new Exception("Invalid Object");
        }
    }

    private List<EmployeeDto> objectsToDtoList(RawUser[] objects){
        List<EmployeeDto> employeeDtos = new ArrayList<>();
        Arrays.stream(objects).forEach(ob->{
            try{
                EmployeeDto employeeDto = objectToDto(ob);
                employeeDtos.add(employeeDto);

            }catch (Exception ex){
                ex.printStackTrace();
            }
        });

        return employeeDtos;
    }
}
