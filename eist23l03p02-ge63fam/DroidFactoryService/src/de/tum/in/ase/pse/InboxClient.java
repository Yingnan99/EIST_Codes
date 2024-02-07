package de.tum.in.ase.pse;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


import java.util.ArrayList;
import java.util.List;

public final class InboxClient {

    private RestTemplate rest;
    private static final String BASE_URL = "http://localhost:8080/messages/";
    private static final String R_2 = "r2";
    private static final String THREE_PO = "3po";

    private final List<String> messages = new ArrayList<>();


    public InboxClient() {
        this.rest = new RestTemplate();
    }

    // TODO 1: create an http request using the RestTemplate and store the body of the response in the messages attribute
    public void droidReadyR2(String droid) {
        //根据机器人类型（R_2）创建POST请求的URL字符串
        String url = BASE_URL + R_2;
        //使用createHttpEntity方法创建一个HttpEntity对象，该对象包含请求正文和正确的Content-Type标头
        //这里，droid是作为参数传递给droidReadyR2方法的机器人名称。createHttpEntity方法将机器人名称作为请求正文，并创建一个包含正确Content-Type标头的HttpEntity对象
        HttpEntity<String> request = createHttpEntity(droid);
        //使用RestTemplate的postForEntity方法发送POST请求，并将响应保存在一个ResponseEntity对象中
        ResponseEntity<String> response = rest.postForEntity(url, request, String.class);
        //response.getBody()方法获取响应的正文，并将其添加到messages属性中
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
