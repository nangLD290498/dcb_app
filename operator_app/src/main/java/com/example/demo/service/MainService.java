package com.example.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class MainService {



    public Map<String, Object> generateAccessToken(String serviceKey, String serviceName) throws Exception {
        log.info("Start generateAccessToken: {}, {}", serviceKey, serviceName);
        Map<String, Object> response = new HashMap<>();
        if(serviceKey.equals("abc67-45434-d034") && serviceName.equals("service-name")){
            response.put("scope", "am_application_scope default");
            response.put("access_token", "8710103f-40a0-39ad-871a-f1ea84691f28");
            response.put("expires_in", 3600);
            response.put("token_type", "Bearer");
            log.info("response: {}", response.toString());
        } else throw new Exception("Something went wrong !!!");
        log.info("end generateAccessToken");
        return response;
    }

    public Map<String, Object> processRequestFromDCB(Map<String, Object> request, HttpServletRequest httpRequest) throws Exception {
        log.info("Start processRequestFromDCB: {}", request.toString());
        String token = httpRequest.getHeader("Authorization");
        Map<String, Object> response = new HashMap<>();
        Map<String, Object> transactionInfo = new HashMap<>();
        if(!token.isEmpty() && token.equals("8710103f-40a0-39ad-871a-f1ea84691f28")){
            log.info("Authorization OK");
            transactionInfo.put("msisdn","6281990449203");
            transactionInfo.put("responseDesc","Request has been execute successfully");
            transactionInfo.put("refId","ff0ced5b-eb31-4eea-8105-d4ebe9557c88");
            transactionInfo.put("responseCode","00");
        } else {
            log.info("Authorization Failed");
            /*transactionInfo.put("msisdn","null");
            transactionInfo.put("responseDesc","Failed");
            transactionInfo.put("refId","null");
            transactionInfo.put("responseCode","11");*/
            throw new Exception("Something went wrong !!!");
        }
        response.put("transactionInfo",transactionInfo);
        log.info("End processRequestFromDCB: {}", response.toString());
        return response;
    }

}
