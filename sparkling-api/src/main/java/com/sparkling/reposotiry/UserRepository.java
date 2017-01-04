package com.sparkling.reposotiry;

import com.sparkling.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by gain on 2017. 1. 4..
 */
public interface UserRepository extends MongoRepository<User, String> {
}
