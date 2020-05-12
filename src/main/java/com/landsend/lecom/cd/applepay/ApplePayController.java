package com.landsend.lecom.cd.applepay;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.landsend.lecom.cd.applepay.client.ApplePayClient;
import com.landsend.lecom.cd.applepay.client.StartSessionRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
@RequestMapping("/api")
@CrossOrigin
@Log4j2
public class ApplePayController {


    private ApplePayClient applePayClient;

    @Autowired
    public ApplePayController(ApplePayClient applePayClient) {
        this.applePayClient = applePayClient;
    }

    @PostMapping("/session/create")
    @ResponseBody
    public ResponseEntity<String> createSession(@RequestBody StartSessionRequest startSessionRequest) throws IOException {
        log.info("/api/session/create called with validationUrl={}", startSessionRequest.getValidationUrl());
        String response = applePayClient.createSession(startSessionRequest.getValidationUrl());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/authorize")
    @ResponseBody
    public ResponseEntity<Void> authorize(@RequestBody String authorizeRequest) {
        log.info("/api/authorize called");
        log.info(authorizeRequest);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/order/save")
    @ResponseBody
    public ResponseEntity<Void> saveOrder(@RequestBody String saveOrderRequest) {
        log.info("/api/order/save called");
        log.info(saveOrderRequest);
        return ResponseEntity.ok().build();
    }

}
