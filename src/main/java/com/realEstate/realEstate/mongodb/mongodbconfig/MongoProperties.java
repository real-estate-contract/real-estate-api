package com.realEstate.realEstate.mongodb.mongodbconfig;


import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class MongoProperties {
    String client;
    String name;
}
