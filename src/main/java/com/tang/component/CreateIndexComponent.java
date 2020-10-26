package com.tang.component;

import com.tang.util.ElasticsearchUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CreateIndexComponent implements CommandLineRunner {

    @Autowired
    ElasticsearchUtils elasticsearchUtils;

    @Override
    public void run(String... args) throws Exception {

        if (!elasticsearchUtils.existsIndex("user_hot")){
            System.out.println("创建索引user_hot");
            elasticsearchUtils.createIndex("user_hot");
        }

    }
}
