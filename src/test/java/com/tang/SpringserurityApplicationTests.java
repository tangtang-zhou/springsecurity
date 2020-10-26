package com.tang;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tang.mapper.PermissionMapper;
import com.tang.mapper.RolePermissionMapper;
import com.tang.mapper.UserMapper;
import com.tang.mapper.UserRoleMapper;
import com.tang.model.ElasticSearchUser;
import com.tang.model.LoginUser;
import com.tang.model.RolePermissionList;
import com.tang.service.GroupRoleService;
import com.tang.service.GroupUserService;
import com.tang.service.UserRoleService;
import com.tang.util.ElasticsearchUtils;
import com.tang.util.RedisUtil;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.common.document.DocumentField;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringserurityApplicationTests {

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserRoleMapper userRoleMapper;

    @Autowired
    UserRoleService userRoleService;

    @Autowired
    PermissionMapper permissionMapper;

    @Autowired
    GroupUserService groupUserService;

    @Autowired
    GroupRoleService groupRoleService;

    @Autowired
    RolePermissionMapper rolePermissionMapper;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private RestHighLevelClient restHighLevelClient;
    @Autowired
    ElasticsearchUtils elasticsearchUtils;
    @Test
    public void contextLoads() throws IOException {

        /*CreateIndexRequest user_hot = new CreateIndexRequest("user_hot");
        CreateIndexResponse createIndexResponse = restHighLevelClient.indices().create(user_hot, RequestOptions.DEFAULT);
        String index = createIndexResponse.index();*/
        /*ElasticSearchUser elasticSearchUser = new ElasticSearchUser();
        elasticSearchUser.setId(1);
        elasticSearchUser.setUsername("root");
        elasticSearchUser.setHotNumber(0);
        elasticsearchUtils.addDocument("user_hot", elasticSearchUser.getUsername(), JSON.toJSONString(elasticSearchUser));
*/
        //String objects = elasticsearchUtils.existsDocument("user_hot", "root");
        //System.out.println(objects);

        //ElasticSearchUser elasticSearchUser = JSON.parseObject("{\"hotNumber\":1,\"id\":1,\"username\":\"root\"}", ElasticSearchUser.class);
        //elasticsearchUtils.deleteDocument("user_hot", "root");

        elasticsearchUtils.getDocuments("user_hot");
        //SearchRequest user_hot = new SearchRequest("logstash-api-2020.09");
        //String s = elasticsearchUtils.existsDocument("logstash-api-2020.09", "yD2Pm3QBxyXKQZNkt8gK");
        //System.out.println(s);

    }

}
