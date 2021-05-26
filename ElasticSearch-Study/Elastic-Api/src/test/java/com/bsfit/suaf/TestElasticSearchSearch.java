package com.bsfit.suaf;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.Before;
import org.junit.Test;

import java.net.InetAddress;

/**
 * <br>
 *
 * @author bangsun
 * @data 2021/5/25 11:18
 * @verson
 */
public class TestElasticSearchSearch {

    private static TransportClient client;

    @Before
    public void initConn() throws Exception {
        String IP = "10.100.2.188";
        int PORT = 9300;
        client = new PreBuiltTransportClient(Settings.EMPTY)
                .addTransportAddresses(new TransportAddress(InetAddress.getByName(IP), PORT));
    }

    @Test
    public void testSearch() {
        /*查询所有*/
        MatchAllQueryBuilder matchAllQueryBuilder = QueryBuilders.matchAllQuery();
        /*不定索引和type长类型*/
        SearchResponse searchResponse = client.prepareSearch("iks")
                .setTypes("emp").setQuery(matchAllQueryBuilder) //设置条件
                .get();
        SearchHits hits = searchResponse.getHits();
        System.out.println("返回条数:" + hits.totalHits);
        System.out.println("最大分数:" + hits.getMaxScore());
        SearchHit[] documents = hits.getHits();
        for (SearchHit document : documents) {
            System.out.println(document.getSourceAsString());
        }
    }

    @Test
    public void testSearchTerm() {
        /*term查询*/
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("context", "百度");
        /*不定索引和type长类型*/
        SearchResponse searchResponse = client.prepareSearch("iks")
                .setTypes("emp").setQuery(termQueryBuilder) //设置条件
//                .addSort(SortBuilders.fieldSort("age").order(SortOrder.ASC))
                .setSize(3).get();  //设置分页查询
        SearchHits hits = searchResponse.getHits();
        System.out.println("返回条数:" + hits.totalHits);
        System.out.println("最大分数:" + hits.getMaxScore());
        SearchHit[] documents = hits.getHits();
        for (SearchHit document : documents) {
            System.out.println(document.getSourceAsString());
        }
    }

    @Test
    public void testSearchFilter() {
        SearchResponse searchResponse = client.prepareSearch("iks")   //索引
                .setTypes("emp")                //类型
                .setQuery(QueryBuilders.matchAllQuery())  //查询条件
                .setFetchSource("name", "age")  //要name字段，不要age字段
                .setFrom(0).setSize(3).get();//分页get
        SearchHits hits = searchResponse.getHits();
        System.out.println("返回条数:" + hits.totalHits);
        System.out.println("最大分数:" + hits.getMaxScore());
        SearchHit[] documents = hits.getHits();
        for (SearchHit document : documents) {
            System.out.println(document.getSourceAsString());
        }
    }

    @Test
    public void testMethod() {
        /*term查询*/
        TermQueryBuilder termQuery = QueryBuilders.termQuery("context", "集群");
        /*前缀查询*/
        PrefixQueryBuilder prefixQuery = QueryBuilders.prefixQuery("name", "悲伤的");
        /*通配符*/
        WildcardQueryBuilder wildcardQuery = QueryBuilders.wildcardQuery("context", "elastic**arch");
        /*模糊*/
        FuzzyQueryBuilder fuzzyQuery = QueryBuilders.fuzzyQuery("context", "reds");
        /*范围*/
        RangeQueryBuilder rangeQuery = QueryBuilders.rangeQuery("age").gte(18).lte(28);
        /*ids查询*/
        IdsQueryBuilder idsQuery = QueryBuilders.idsQuery().addIds("1", "2", "3");
        /*boolean查询*/
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        boolQuery.mustNot(QueryBuilders.termQuery("id", "2f973823")).must(QueryBuilders.termQuery("context", "软件"));
        /*多字段查询*/
        MultiMatchQueryBuilder multiMatchQuery = QueryBuilders.multiMatchQuery("集群", "context", "name");

        testQuery(termQuery);
        testQuery(prefixQuery);
        testQuery(wildcardQuery);
        testQuery(fuzzyQuery);
        testQuery(rangeQuery);
        testQuery(idsQuery);
        testQuery(boolQuery);
        testQuery(multiMatchQuery);
    }

    @Test
    public void testHigh() {
        /*term查询*/
        TermQueryBuilder termQuery = QueryBuilders.termQuery("context", "集群");
        testHighLight(termQuery);
    }

    public void testQuery(QueryBuilder queryBuilder) {
        SearchResponse searchResponse = client.prepareSearch("iks")   //索引
                .setTypes("emp")                //类型
                .setQuery(queryBuilder)  //查询条件
                .get();//分页get
        SearchHits hits = searchResponse.getHits();
        System.out.println("返回条数:" + hits.totalHits);
        System.out.println("最大分数:" + hits.getMaxScore());
        SearchHit[] documents = hits.getHits();
        for (SearchHit document : documents) {
            System.out.println(document.getSourceAsString());
        }
    }

    public void testHighLight(QueryBuilder queryBuilder) {
        HighlightBuilder highlightBuilder =
                new HighlightBuilder().field("name").field("context").requireFieldMatch(false);
        SearchResponse searchResponse = client.prepareSearch("iks")   //索引
                .setTypes("emp")                //类型
                .setQuery(queryBuilder)  //查询条件
                .highlighter(highlightBuilder)    //高亮
                .get();//分页get
        SearchHits hits = searchResponse.getHits();
        System.out.println("返回条数:" + hits.totalHits);
        System.out.println("最大分数:" + hits.getMaxScore());
        SearchHit[] documents = hits.getHits();
        for (SearchHit document : documents) {
            System.out.println(document.getSourceAsMap());
        }
    }
}
