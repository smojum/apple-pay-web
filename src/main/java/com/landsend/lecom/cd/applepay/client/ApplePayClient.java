package com.landsend.lecom.cd.applepay.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@Component
@Log4j2
public class ApplePayClient {

    private RestTemplate restTemplate;
    private ApplePaySession applePaySession;

    @Autowired
    public ApplePayClient(RestTemplate restTemplate, ApplePaySession applePaySession) {
        this.restTemplate = restTemplate;
        this.applePaySession = applePaySession;
    }

    public String createSession(String validationUrl) throws JsonProcessingException {
        log.info("Sending Request: {}", applePaySession);
        //String response = restTemplate.postForObject(validationUrl, applePaySession, String.class);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonRequest = objectMapper.writeValueAsString(applePaySession);
        log.info("Request Json: {}", jsonRequest);
        HttpEntity<String> request = new HttpEntity<>(jsonRequest, headers);
        String response = restTemplate.postForObject(validationUrl, request, String.class);
        log.info("Response Json: {}", response);
        return response;
    }

    public String createSessionNative(String validationUrl) throws IOException {
        log.info("Sending Request: {}", applePaySession);
        URL url = new URL(validationUrl);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setRequestProperty("Content-Type", "application/json; utf-8");
        httpURLConnection.setRequestProperty("Accept", "application/json");
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonRequest = objectMapper.writeValueAsString(applePaySession);
        log.info("Request Json: {}", jsonRequest);
        try (OutputStream os = httpURLConnection.getOutputStream()) {
            byte[] input = jsonRequest.getBytes("utf-8");
            os.write(input, 0, input.length);
        }
        StringBuilder response = new StringBuilder();
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(httpURLConnection.getInputStream(), "utf-8"))) {
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            log.info("Response: {}", response.toString());
        } finally {

        }

        return response.toString();
    }
}
