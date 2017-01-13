package com.sparkling.web.user.service;

import com.sparkling.entity.User;
import com.sparkling.reposotiry.UserRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by gain on 2017. 1. 4..
 */


@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    MongoTemplate mongoTemplate;


    final String CLIENT_ID="7d7882ef5faa8cea3fc4";
    final String CLIENT_SECRET= "884100467dce26d450b1f4ce6219725718042481";
    final String REDIRECT_URL = "https://github.com/login/oauth/access_token";
    final String BASE_URL = "https://api.github.com/user";

    @Override
    public String loginService(String code) {

        RestTemplate restTemplate = new RestTemplate();

        JSONObject vars = new JSONObject();
        vars.put("client_id", CLIENT_ID);
        vars.put("client_secret", CLIENT_SECRET);
        vars.put("code", code);

        // set headers
        HttpHeaders headers = new HttpHeaders();

        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<String>(vars.toString(), headers);

        String result = restTemplate.postForObject(REDIRECT_URL, entity, String.class);




        // 올바르지 않은 code의 입력으로 access_token이 유효하지 않을 경우
        try {
            JSONObject jsonObject = new JSONObject(result);
            String realResult = jsonObject.getString("access_token");

            restTemplate.setErrorHandler(new DefaultResponseErrorHandler(){
                protected boolean hasError(HttpStatus statusCode) {
                    return false;
                }});

            String tokenResult = sendByGet(BASE_URL, realResult);

            JSONObject loginResult = new JSONObject(tokenResult);
            String login = loginResult.getString("login");
            String id = loginResult.get("id").toString();



            //최종 결과 JSON TYPE으로 반환
            JSONObject returnResult = new JSONObject();
            returnResult.put("login",login);
            returnResult.put("id",id);



            //MongoDB에 저장 - upsert() 로 값이 있으면 업데이트 없으면 새로 추가!


            Query query = new Query();
            query.addCriteria(Criteria.where("id").is(id));
            Update update = new Update();
            update.set("id", id);
            update.set("login", login);
            mongoTemplate.upsert(query, update, User.class);

            // 나중에 예외처리 해줘야 할듯


            return returnResult.toString();


        }catch (Exception e){

            JSONObject returnErrorResult = new JSONObject();

            returnErrorResult.put("error","-1");

            // 예외처리 시
            // return org.json.JSONException: JSONObject["access_token"] not found.

            return returnErrorResult.toString();
        }





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
