package dcb_app.demo.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dcb_app.demo.constant.OperatorUrlEnum;
import dcb_app.demo.constant.SPUrlEnum;
import dcb_app.demo.model.StandardRequest;
import dcb_app.demo.repository.StandardRequestRepository;
import dcb_app.demo.service.StandardRequestService;
import dcb_app.demo.utils.CallAPIMethod;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
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
    private String operatorHost;

    @Value("${token.serviceKey}")
    private String serviceKey;

    @Value("${token.serviceName}")
    private String serviceName;

    @Override
    public Map<String, Object> processAndSendSPRequest(Map<String, Object> request) throws Exception {
        log.info("Start processAndSendSPRequest");
        Map<String, Object> response;
        Map<String, Object> operatorResponse = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        // prepare operator request
        Map<String, Object> operatorRequest = processRequest(request);
        try{
            //get access token
            String tokenGenerationUrl = operatorHost.concat(OperatorUrlEnum.OPERATOR_GENERATE_TOKEN.getUri().replace("{service_key}",serviceKey).replace("{service_name}",serviceName));
            log.info("sending request to: {}", tokenGenerationUrl);
            Map<String, Object> tokenGenerationRes = callAPIMethod.callAPI(tokenGenerationUrl, null, HttpMethod.GET, new ParameterizedTypeReference<Map<String, Object>>() {}, null );
            log.info("token response received: {}", tokenGenerationRes);
            String accessToken = (String) tokenGenerationRes.get("access_token");
            // send to operator
            String url = operatorHost.concat(OperatorUrlEnum.OPERATOR_REQUEST.getUri());
            log.info("sending request to: {}", url);
            operatorResponse = callAPIMethod.callAPI(url, operatorRequest, HttpMethod.POST, new ParameterizedTypeReference<Map<String, Object>>() {}, accessToken);
            log.info("Sent request to operator successfully!");
        } catch (Exception e) {
        } finally {
            response = processResponse(operatorResponse);
        }
        // process BCD response

        log.info("DCB request: {}", request.toString());
        log.info("DCB response: {}", response.toString());
        log.info("operatorRequest: {}", operatorRequest.toString());
        log.info("operatorResponse: {}", operatorResponse.toString());
        //save to DB
        StandardRequest standardRequest = new StandardRequest();
        standardRequest.setJsonRequest(mapper.writeValueAsString(request));
        standardRequest.setJsonResponse(mapper.writeValueAsString(response));
        standardRequest.setOperatorRequest(mapper.writeValueAsString(operatorRequest));
        standardRequest.setOperatorResponse(mapper.writeValueAsString(operatorResponse));
        requestRepository.save(standardRequest);
        return response;
    }

    private Map<String, Object> processRequest(Map<String, Object> request){
        Map<String, Object> chargingRequest = (Map<String, Object>) request.get("chargingRequest");
        Map<String, String> customerInfo = (Map<String, String>) chargingRequest.get("customerInfo");
        Map<String, String> transactionInfo = (Map<String, String>) chargingRequest.get("transactionInfo");
        Map<String, Object> operatorRequest = new HashMap<>();
        Map<String, String> operatorTransactionInfo = new HashMap<>();

        operatorTransactionInfo.put("msisdn", customerInfo.get("mobileNo"));
        operatorTransactionInfo.put("refId", transactionInfo.get("transactionId"));
        operatorTransactionInfo.put("item", transactionInfo.get("item"));
        operatorTransactionInfo.put("itemDescription", transactionInfo.get("itemDescription"));
        operatorTransactionInfo.put("balanceType", transactionInfo.get("balanceType"));
        operatorTransactionInfo.put("chargeAmount", transactionInfo.get("amount"));
        operatorTransactionInfo.put("currency", transactionInfo.get("currency"));

        operatorRequest.put("transactionInfo", operatorTransactionInfo);
        return operatorRequest;
    }

    private Map<String, Object> processResponse(Map<String, Object> operatorResponse){
        Map<String, String> operatorTransactionInfo = (Map<String, String>) operatorResponse.get("transactionInfo");
        Map<String, Object> dcbResponse = new HashMap<>();
        Map<String, String> customerInfo = new HashMap<>();
        Map<String, String> transactionInfo = new HashMap<>();
        try {
            customerInfo.put("mobileNo", operatorTransactionInfo.get("msisdn"));

            transactionInfo.put("transactionId", operatorTransactionInfo.get("refId"));
            transactionInfo.put("responseStatus", operatorTransactionInfo.get("responseCode").equals("00") ? "Success" : "Failed");
            transactionInfo.put("responseDesc", operatorTransactionInfo.get("responseDesc"));

            dcbResponse.put("customerInfo", customerInfo);
            dcbResponse.put("transactionInfo", transactionInfo);
        }catch (Exception e){
            dcbResponse.clear();
            dcbResponse.put("responseStatus", "Failed");
        }
        return dcbResponse;
    }
}
