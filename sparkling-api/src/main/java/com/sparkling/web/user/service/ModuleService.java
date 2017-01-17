package com.sparkling.web.user.service;

import com.sparkling.entity.Module;



/**
 * Created by gain on 2017. 1. 4..
 */
public interface ModuleService {

    public String insertModule(String id, String module);

    public String selectModuleList(String id);
}
