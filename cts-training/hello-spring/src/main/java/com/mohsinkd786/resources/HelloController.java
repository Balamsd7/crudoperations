package com.mohsinkd786.resources;

import com.mohsinkd786.request.MessageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String sayHello(){
        return "Hello, Welcome to Spring Boot";
    }

    @GetMapping("/message/{msg}")
    public String getMessage(@PathVariable("msg") String message){
        return "MESSAGE : "+message;
    }

    // http://localhost:8080/calculate?a=1&b=9
    @GetMapping("/calculate")
    public double calculate(@RequestParam(required = false , defaultValue = "0") int a,
                            @RequestParam(required = false , defaultValue = "0") int b){
        return a + b;
    }

    @PostMapping("/message/save")
    public boolean saveMessage(@RequestBody MessageRequest request){
        System.out.println("MESSAGE RECEIVED : "+request.getMessage());
        return true;
    }
}
