package com.sparkling.web.user.service;

import com.sparkling.entity.Module;
import com.sparkling.reposotiry.ModuleRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by gain on 2017. 1. 4..
 */

@Service
public class ModuleServiceImpl implements ModuleService {

    @Autowired
    ModuleRepository moduleRepository;

    @Autowired
    MongoTemplate mongoTemplate;


    @Override
    public String insertModule(String id, String module){

        JSONObject resultCode = new JSONObject();


        try{
            Query query = new Query();
            query.addCriteria(Criteria.where("id").is(id));
            Update update = new Update();
            update.set("id", id);
            update.set("module", module);
            mongoTemplate.upsert(query, update, Module.class);

            resultCode.put("success", "1");

            return resultCode.toString();

        }catch (Exception e){

            resultCode.put("error", "-1");

            return resultCode.toString();
        }
    }

    @Override
    public String selectModuleList(String id) {


        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));

        Module module = mongoTemplate.findOne(query,Module.class);


        return module.getModule().toString();
    }
}
