package dcb_app.demo.controller;

import dcb_app.demo.service.StandardRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class RequestController {

    @Autowired
    private StandardRequestService standardRequestService;

    @PostMapping("/process-sp-request")
    public Map<String, Object> processAndSendSPRequest(@RequestBody Map<String, Object> request){
        return standardRequestService.processAndSendSPRequest(request);
    }

}
