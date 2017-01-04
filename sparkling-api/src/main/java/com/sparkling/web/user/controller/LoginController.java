package com.sparkling.web.user.controller;


import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by gain on 2017. 1. 4..
 */
@RestController
@RequestMapping("/login")
public class LoginController  {

    final String client_id="7d7882ef5faa8cea3fc4";
    final String client_secret= "884100467dce26d450b1f4ce6219725718042481";
    final String redirectURL = "https://github.com/login/oauth/access_token";




    @RequestMapping(value="/login", method= RequestMethod.GET)
    public String loginNinsert(@RequestParam String code) {



            RestTemplate restTemplate = new RestTemplate();

            JSONObject vars = new JSONObject();
            vars.put("client_id", client_id);
            vars.put("client_secret", client_secret);
            vars.put("code", code);

            // set headers
            HttpHeaders headers = new HttpHeaders();

            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity = new HttpEntity<String>(vars.toString(), headers);

            String result = restTemplate.postForObject(redirectURL, entity, String.class);

            JSONObject jsonObject = new JSONObject(result);
            String realResult = jsonObject.getString("access_token");



            String baseURL = "https://api.github.com/user";


//
//            JSONObject token = new JSONObject();
//            token.put("access_token", realResult);
//
//

            restTemplate.setErrorHandler(new DefaultResponseErrorHandler(){
            protected boolean hasError(HttpStatus statusCode) {
                return false;
            }});

            String tokenResult = sendByGet(baseURL, realResult);

            JSONObject loginResult = new JSONObject(tokenResult);
            String login = loginResult.getString("login");
            String id = loginResult.get("id").toString();


            return login;


    }


    public String sendByGet(String serverUrl, String accessToken) {
        Map<String, Object> params = new HashMap<>();
        params.put("access_token", accessToken);

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(
                serverUrl + "?access_token={access_token}",
                String.class,
                params);
    }



}
