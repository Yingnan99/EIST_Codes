package de.tum.in.ase.pse;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class InboxResource {
    private RestTemplate rest;
    private static final String BASE_URL = "http://localhost:8080/messages/";
    private static final String R_2 = "r2";
    private static final String THREE_PO = "3po";

    private final List<String> messages = new ArrayList<>();

    public InboxResource() {
        this.rest = new RestTemplate();
    }


    public void droidReadyR2(String droid) {
        String url = BASE_URL + R_2;
        HttpEntity<String> request = createHttpEntity(droid);
        ResponseEntity<String> response = rest.postForEntity(url, request, String.class);
        messages.add(response.getBody());
    }
    public void droidReady3PO(String droid) {
        String url = BASE_URL + THREE_PO;
        HttpEntity<String> request = createHttpEntity(droid);
        ResponseEntity<String> response = rest.postForEntity(url, request, String.class);
        messages.add(response.getBody());
    }
    public void printMessages() {
        this.messages.forEach(System.out::println);
    }
    public List<String> getMessages() {
        return messages;
    }
    private HttpEntity<String> createHttpEntity(String droid) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(droid, headers);
    }
}
