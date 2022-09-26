package dcb_app.demo.service;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.Map;


public interface StandardRequestService {

    Map<String, Object> processAndSendSPRequest(Map<String, Object> request) throws JsonProcessingException;
}
