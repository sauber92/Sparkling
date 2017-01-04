package com.sparkling.web.user.controller;


import com.sparkling.web.user.service.LoginService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by gain on 2017. 1. 4..
 */
//            @restTemplate를 쓰면 마지막 파라미터 타입이 Map<String, ?> uriVariables 이기 때문에
//             access_token={access_token}과 같은 형식을 사용해야하고  JSONObject 타입을 사용할 수 없다.
//             from jeon

@RestController
@RequestMapping("/login")
public class LoginController  {


    @Autowired
    LoginService loginService;

    //
    //@Method : github에 로그인하고 loginid와 github 고유id를 반환하며 Mongodb에 저장한다.
    //
    @RequestMapping(value="/login", method= RequestMethod.GET)
    public String gitHubLogin(@RequestParam String code) {


        String result = loginService.loginService(code);


            return result;

    }






}
