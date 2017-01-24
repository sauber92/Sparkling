package com.sparkling.web.user.controller;

import com.sparkling.web.user.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by gain on 2017. 1. 4..
 */
@RestController
@RequestMapping("/module")
public class ModuleController {

    @Autowired
    ModuleService moduleService;


    @RequestMapping(method = RequestMethod.GET)
    public String insertModule(@RequestParam String id, String module){

        String result = this.moduleService.insertModule(id, module);

        return result;
    }


}
