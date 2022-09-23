package dcb_app.demo.utils;

import dcb_app.demo.model.ResponseWrapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class CallAPIMethod {

    RestTemplate restTemplate;

    public ResponseWrapper doPost(String url, Map requestBody){
        restTemplate = new RestTemplate();
        HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody);
        ResponseEntity<ResponseWrapper> response = restTemplate
                .exchange(url, HttpMethod.POST, request, ResponseWrapper.class);

        ResponseWrapper responseObject = response.getBody();

        return responseObject;
    }
}
