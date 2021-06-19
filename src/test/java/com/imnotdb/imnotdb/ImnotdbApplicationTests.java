package com.imnotdb.imnotdb;

import com.alibaba.fastjson.JSON;
import com.imnotdb.imnotdb.mapper.TitleFullMapper;
import com.imnotdb.imnotdb.utils.SymbolTable;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

@SpringBootTest
class ImnotdbApplicationTests {

    @Autowired
    @Qualifier(SymbolTable.ESCLIENT)
    private RestHighLevelClient esClient;
    @Autowired
    private TitleFullMapper titleFullMapper;
    @Test
    void contextLoads() {
    }

    @Test
    void testSearchIndex(){
        GetIndexRequest request = new GetIndexRequest(SymbolTable.TITLEFULL);
        boolean exists = false;
        try {
            exists = esClient.indices().exists(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(exists);
    }
    void testIsExists(){
        GetRequest title_full = new GetRequest("title_full");
        title_full.fetchSourceContext(new FetchSourceContext(false));
    }
    @Test
    void testQueryRequest(){
        SearchRequest searchRequest = new SearchRequest(SymbolTable.TITLEFULL);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery(SymbolTable.AKASTITLES,"你好");
        searchSourceBuilder.query(matchQueryBuilder);
        searchRequest.source(searchSourceBuilder);
        try {
            SearchResponse searchResponse = esClient.search(searchRequest, RequestOptions.DEFAULT);
            System.out.println(JSON.toJSONString(searchResponse.getHits()));
            System.out.println("=========================================");
            String[] tconstList = Arrays.stream(searchResponse.getHits().getHits()).map((x -> x.getSourceAsMap().get(SymbolTable.TCONST))).toArray(String[]::new);
            for (String s : tconstList) {
                System.out.println(s);
            }
//            for (SearchHit hit : searchResponse.getHits().getHits()) {
//                hit.getFields().get(SymbolTable.TCONST);
//                System.out.println(hit.getSourceAsMap().get(SymbolTable.TCONST));
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    void testGetTitleByCnds(){
        HashMap<String, Object> cnds = new HashMap<>();
        cnds.put(SymbolTable.AKASTITLES, "你好");
        cnds.put(SymbolTable.RATING_GTE, 5.4);
        cnds.put(SymbolTable.RATING_LTE, 8.5);
        String[] titleByCnds = titleFullMapper.getTitleByCnds(cnds, 1, 20);
        for (String titleByCnd : titleByCnds) {
            System.out.println(titleByCnd);
        }
    }
}
