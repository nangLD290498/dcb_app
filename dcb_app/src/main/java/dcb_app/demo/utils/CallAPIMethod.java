package dcb_app.demo.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dcb_app.demo.constant.OperatorUrlEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class CallAPIMethod {

    private RestTemplate restTemplate;

    public <T> T callAPI(String url, Object body, HttpMethod httpMethod, ParameterizedTypeReference typeReference, String accessToken){
        restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        if(!StringUtils.isEmpty(accessToken)){
            headers.set("Authotization", accessToken);
        }
        HttpEntity<Object> entity = new HttpEntity<>(body, headers);
        try {
            ResponseEntity<T> response = restTemplate.exchange(url, httpMethod, entity, typeReference);
        }catch (HttpClientErrorException e){}
        return null;
    }



}
