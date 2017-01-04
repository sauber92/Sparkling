package com.sparkling.reposotiry;

import com.sparkling.entity.Module;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by gain on 2017. 1. 4..
 */
public interface ModuleRepository extends MongoRepository<Module, String>{
}
