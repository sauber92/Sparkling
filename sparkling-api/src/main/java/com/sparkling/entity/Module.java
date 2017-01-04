package com.sparkling.entity;

/**
 * Created by gain on 2017. 1. 4..
 */
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;


@Document(collection = "module")
public class Module {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String _id;
    @Field
    private String id;
    @Field
    private String module;


    public Module (){}


    public Module(String id, String module) {
        this.id = id;
        this.module = module;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    @Override
    public String toString() {
        return "Module{" +
                "_id='" + _id + '\'' +
                ", id='" + id + '\'' +
                ", module='" + module + '\'' +
                '}';
    }
}