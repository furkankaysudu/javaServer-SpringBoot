package com.furkank.springdemo;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface TextRepository extends MongoRepository<Text,String> {

}
