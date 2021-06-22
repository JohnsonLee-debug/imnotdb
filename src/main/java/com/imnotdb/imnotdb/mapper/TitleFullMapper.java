package com.imnotdb.imnotdb.mapper;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.imnotdb.imnotdb.pojo.Title;
import com.imnotdb.imnotdb.pojo.TitleFull;
import com.imnotdb.imnotdb.utils.BigTableTransformer;
import com.imnotdb.imnotdb.utils.SymbolTable;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.index.reindex.DeleteByQueryRequest;
import org.elasticsearch.index.reindex.UpdateByQueryRequest;
import org.elasticsearch.script.Script;
import org.elasticsearch.script.ScriptType;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
public class TitleFullMapper {
    @Autowired
    @Qualifier(SymbolTable.ESCLIENT)
    private RestHighLevelClient esClient;
    @Autowired
    private TitleMapper titleMapper;
    @Autowired
    private BigTableTransformer bigTableTransformer;
    public TitleFull getTitleFullByTconst(String tconst){
        SearchRequest searchRequest = new SearchRequest(SymbolTable.TITLEFULL);
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery(SymbolTable.TCONST, tconst);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(termQueryBuilder);
        searchRequest.source(searchSourceBuilder);
        try {
            SearchResponse search = esClient.search(searchRequest, RequestOptions.DEFAULT);
            SearchHit[] hits = search.getHits().getHits();
            if(hits.length == 0){
                return null;
            }else {
                JSONObject jsonObject = new JSONObject(hits[0].getSourceAsMap());
                return jsonObject.toJavaObject(TitleFull.class);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public String[] getTitleByCnds(Map<String, Object> conditions, int pageNo, int size) {
        if(pageNo <= 0){
            pageNo = 1;
        }
        SearchRequest searchRequest = new SearchRequest(SymbolTable.TITLEFULL);
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        if (conditions.containsKey(SymbolTable.AKASTITLES)){
            boolQueryBuilder.must(QueryBuilders.matchQuery(SymbolTable.AKASTITLES,
                    conditions.get(SymbolTable.AKASTITLES)));
        }
        if (conditions.containsKey(SymbolTable.DIRECTOR)){
            boolQueryBuilder.must(QueryBuilders.matchQuery(SymbolTable.DIRECTOR,
                    conditions.get(SymbolTable.DIRECTOR)));
        }
        if (conditions.containsKey(SymbolTable.WRITER)){
            boolQueryBuilder.must(QueryBuilders.matchQuery(SymbolTable.WRITER,
                    conditions.get(SymbolTable.WRITER)));
        }
        if (conditions.containsKey(SymbolTable.ACTOR)){
            boolQueryBuilder.must(QueryBuilders.matchQuery(SymbolTable.ACTOR,
                    conditions.get(SymbolTable.ACTOR)));
        }
        if (conditions.containsKey(SymbolTable.TITLETYPE)){
            boolQueryBuilder.must(QueryBuilders.termQuery(SymbolTable.TITLETYPE,
                    conditions.get(SymbolTable.TITLETYPE)));
        }
        if (conditions.containsKey(SymbolTable.GENRES)){
            boolQueryBuilder.must(QueryBuilders.termQuery(SymbolTable.GENRES,
                    conditions.get(SymbolTable.GENRES)));
        }
        if (conditions.containsKey(SymbolTable.ISADULT)){
            boolQueryBuilder.must(QueryBuilders.termQuery(SymbolTable.ISADULT,
                    conditions.get(SymbolTable.ISADULT)));
        }
        if (conditions.containsKey(SymbolTable.YEAR_GTE)){
            boolQueryBuilder.filter(QueryBuilders.rangeQuery(SymbolTable.STARTYEAR)
                    .from(conditions.get(SymbolTable.YEAR_GTE)));
        }
        if (conditions.containsKey(SymbolTable.YEAR_LTE)){
            boolQueryBuilder.filter(QueryBuilders.rangeQuery(SymbolTable.STARTYEAR)
                    .to(conditions.get(SymbolTable.YEAR_LTE)));
        }
        if (conditions.containsKey(SymbolTable.RATING_GTE)){
            boolQueryBuilder.filter(QueryBuilders.rangeQuery(SymbolTable.AVERAGERATING)
                    .from(conditions.get(SymbolTable.RATING_GTE)));
        }
        if (conditions.containsKey(SymbolTable.RATING_LTE)){
            boolQueryBuilder.filter(QueryBuilders.rangeQuery(SymbolTable.AVERAGERATING)
                    .to(conditions.get(SymbolTable.RATING_LTE)));
        }
        if (conditions.containsKey(SymbolTable.LENGTH_GTE)){
            boolQueryBuilder.filter(QueryBuilders.rangeQuery(SymbolTable.RUNTIMEMINUTES)
                    .from(conditions.get(SymbolTable.LENGTH_GTE)));
        }
        if (conditions.containsKey(SymbolTable.LENGTH_LTE)){
            boolQueryBuilder.filter(QueryBuilders.rangeQuery(SymbolTable.RUNTIMEMINUTES)
                    .to(conditions.get(SymbolTable.LENGTH_LTE)));
        }
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(boolQueryBuilder);
        searchSourceBuilder.from((pageNo - 1) * size);
        searchSourceBuilder.size(size);
        searchSourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        searchRequest.source(searchSourceBuilder);
        try {
            SearchResponse search = esClient.search(searchRequest, RequestOptions.DEFAULT);
            return Arrays.stream(search.getHits().getHits()).map((x -> x.getSourceAsMap().get(SymbolTable.TCONST))).toArray(String[]::new);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String[]{};
    }
    public void insertTitleFullInES(TitleFull titleFull){
        IndexRequest request = new IndexRequest(SymbolTable.TITLEFULL);
        request.timeout(TimeValue.timeValueSeconds(2));
        request.source(JSON.toJSONString(titleFull), XContentType.JSON);
        try {
            esClient.index(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void updateTitleFullInES(TitleFull titleFull){
        SearchRequest searchRequest = new SearchRequest(SymbolTable.TITLEFULL);
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery(SymbolTable.TCONST, titleFull.getTconst());
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(termQueryBuilder);
        searchRequest.source(searchSourceBuilder);
        try {
            SearchResponse search = esClient.search(searchRequest, RequestOptions.DEFAULT);
            SearchHit[] hits = search.getHits().getHits();
            if (hits.length == 0) {
                return;
            } else {
                String id = hits[0].getId();
                UpdateRequest request = new UpdateRequest(SymbolTable.TITLEFULL, id);
                request.doc(JSON.toJSONString(titleFull), XContentType.JSON);
                esClient.update(request, RequestOptions.DEFAULT);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void deleteTitleFullInES(String tconst){
        DeleteByQueryRequest request =
                new DeleteByQueryRequest(SymbolTable.TITLEFULL);
        request.setQuery(new TermQueryBuilder(SymbolTable.TCONST, tconst));
        try {
            esClient.deleteByQuery(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void syncDataWithMySQL(String tconst){
        Title title = titleMapper.getTitleByTconst(tconst);
        titleMapper.fetchAllLinks(title);
        TitleFull titleFull = bigTableTransformer.titleToFull(title);
        updateTitleFullInES(titleFull);
    }
}
