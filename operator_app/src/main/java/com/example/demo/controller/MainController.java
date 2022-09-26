package com.example.demo.controller;

import com.example.demo.service.MainService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
public class MainController {

    @Autowired
    MainService service;

    @GetMapping("/generateToken")
    public Map<String, Object> generateAccessToken(@RequestParam(name = "service_key") String serviceKey,
                                                    @RequestParam(name= "service_name") String serviceName) throws Exception {
        return service.generateAccessToken(serviceKey, serviceName);
    }

    @PostMapping(value = "/carrier/payment")
    public Map<String, Object> processRequestFromDCB(@RequestBody Map<String, Object> request, HttpServletRequest httpRequest) throws Exception {
        return service.processRequestFromDCB(request,  httpRequest);
    }
}
