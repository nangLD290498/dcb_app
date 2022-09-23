package dcb_app.demo.service;

import java.util.Map;


public interface StandardRequestService {

    Map<String, Object> processAndSendSPRequest(Map<String, Object> request);
}
