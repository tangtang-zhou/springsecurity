package com.tang.util;


import com.alibaba.fastjson.JSON;
import com.tang.model.ElasticSearchUser;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.graph.GraphExploreRequest;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class ElasticsearchUtils {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    /*创建索引
    判断是否存在后创建
    * */
    public boolean existsIndex(String indexName) throws IOException {
        GetIndexRequest user_hot = new GetIndexRequest(indexName);
        return restHighLevelClient.indices().exists(user_hot, RequestOptions.DEFAULT);
    }

    // 判断文档是否存在,存在返回source，不存在返回空
    public String existsDocument(String indexName, String id) throws IOException {
        GetRequest getRequest = new GetRequest(indexName, id);
        boolean exists = restHighLevelClient.exists(getRequest, RequestOptions.DEFAULT);
        System.out.println(exists);
        if (exists){
            GetResponse documentFields = restHighLevelClient.get(getRequest, RequestOptions.DEFAULT);
            return documentFields.getSourceAsString();
        }
        return null;
    }

    public String createIndex (String indexName) throws IOException {
        if (!existsIndex(indexName)){
            CreateIndexRequest userHot = new CreateIndexRequest(indexName);
            CreateIndexResponse createIndexResponse = restHighLevelClient.indices().create(userHot, RequestOptions.DEFAULT);
            return createIndexResponse.index();
        }
        return "创建失败";
    }

    public void addDocument(String indexName, String id, String json) throws IOException {
        System.out.println("添加");
        if (existsIndex(indexName)){
            IndexRequest indexRequest = new IndexRequest(indexName);
            indexRequest.id(id);
            indexRequest.timeout("1s");
            indexRequest.source(json, XContentType.JSON);
            restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
        }
    }

    public void updateDocument(String indexName, String id, String json) throws IOException {
        System.out.println("更新");
        UpdateRequest updateRequest = new UpdateRequest(indexName, id);
        updateRequest.doc(json, XContentType.JSON);
        restHighLevelClient.update(updateRequest, RequestOptions.DEFAULT);
    }

    public void deleteDocument(String indexName, String id) throws IOException {
        DeleteRequest deleteRequest = new DeleteRequest(indexName, id);

        DeleteResponse delete = restHighLevelClient.delete(deleteRequest, RequestOptions.DEFAULT);

        System.out.println(delete.status());// OK

    }

    public List<Map<String, Object>> getDocuments(String indexName) throws IOException {
        System.out.println("取数据");
        SearchRequest user_hot = new SearchRequest(indexName);
        // 查询匹配所有数据
        MatchAllQueryBuilder matchAllQueryBuilder = QueryBuilders.matchAllQuery();
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        //sourceBuilder.sort("hotNumber", SortOrder.DESC);
        sourceBuilder.query(matchAllQueryBuilder);
        user_hot.source(sourceBuilder);
        SearchResponse search = restHighLevelClient.search(user_hot, RequestOptions.DEFAULT);
        List<Map<String, Object>> sourceMapList = new ArrayList<>();
        for (SearchHit hit : search.getHits().getHits()) {
            sourceMapList.add(hit.getSourceAsMap());
        }
        return sourceMapList;
    }

}
