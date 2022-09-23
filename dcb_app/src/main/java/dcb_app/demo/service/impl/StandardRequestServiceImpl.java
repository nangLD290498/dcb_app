package dcb_app.demo.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import dcb_app.demo.constant.SPUrlEnum;
import dcb_app.demo.model.ResponseWrapper;
import dcb_app.demo.repository.StandardRequestRepository;
import dcb_app.demo.service.StandardRequestService;
import dcb_app.demo.utils.CallAPIMethod;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class StandardRequestServiceImpl implements StandardRequestService {

    @Autowired
    private StandardRequestRepository requestRepository;

    @Autowired
    private CallAPIMethod callAPIMethod;

    @Value("${operator.host}")
    String operatorHost;

    @Override
    public Map<String, Object> processAndSendSPRequest(Map<String, Object> request) {
        log.info("Start processAndSendSPRequest");
        log.info("SP request: {}", request.toString());
        Map<String, Object> response = new HashMap<>();
        Map<String, Object> operatorRequest = new HashMap<>();
        ResponseWrapper<Map<String, Object>> operatorResponse = new ResponseWrapper<>();
        //todo: prepare operator request
        ObjectMapper objectMapper = new ObjectMapper();

        try{
            operatorResponse = callAPIMethod.doPost(operatorHost.concat(SPUrlEnum.SP_REQUEST.getUri()), operatorRequest);
        } catch (Exception e) {}
        finally {
            log.info("SoperatorResponse: {}", operatorResponse.toString());
            log.info("Sent request to operator successfully!");
        }
        //todo: process BCD response
        return response;
    }
}
