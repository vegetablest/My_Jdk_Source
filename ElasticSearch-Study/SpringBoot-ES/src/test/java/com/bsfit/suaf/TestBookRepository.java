package com.bsfit.suaf;


import com.bsfit.suaf.dao.BookRepository;
import com.bsfit.suaf.pojo.Book;
import org.apache.commons.collections4.MapUtils;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.data.elasticsearch.core.query.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

/**
 * <br>
 *
 * @author bangsun
 * @data 2021/5/25 16:26
 * @verson
 */
@SpringBootTest(classes = Application.class)
@RunWith(SpringRunner.class)
public class TestBookRepository {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Test
    public void testSave() {
        Book book = new Book().setId(UUID.randomUUID().toString().substring(0, 8)).
                setAge(25).setBir(new Date()).setName("罗小胖").
                setContext("设置刷新策略，若未设置最常见的情况就是执行es中数据更新或插入，检查，同步去查询时数据发现没有更新");
        bookRepository.save(book);
    }

    @Test
    public void testFindAll() {
        Iterable<Book> all = bookRepository.findAll();
        all.forEach(System.out::println);
    }

    @Test
    public void testFindOne() {
        Optional<Book> byId = bookRepository.findById("3de75e3d");
        byId.ifPresent(System.out::println);
    }

    @Test
    public void testDeleteAll() {
        bookRepository.deleteAll();
    }
    @Test
    public void testDeleteOne() {
        bookRepository.delete(new Book().setId("sdfa"));
        bookRepository.deleteById("sdfae");
    }

    @Test
    public void testFindByNameAndContext() {
        List<Book> byNameAndContext = bookRepository.findByNameAndContext("罗时光", "检查");
        byNameAndContext.forEach(System.out::println);
    }
    @Test
    public void testFindByAgeBetween() {
        List<Book> byNameAndContext = bookRepository.findByAgeBetween(23, 24);
        byNameAndContext.forEach(System.out::println);
    }
    /**
     * 分页操作
     * */
    @Test
    public void testSearchPage() {
        MatchAllQueryBuilder allQuery = QueryBuilders.matchAllQuery();
        SearchQuery searchQuery = new NativeSearchQuery(allQuery).setPageable(PageRequest.of(1,2));
        Page<Book> search = bookRepository.search(searchQuery);
        search.forEach(System.out::println);
    }
    @Test
    public void testSearchPageAndSort() {
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withIndices("dangdang").withTypes("book")
                .withQuery(QueryBuilders.matchAllQuery())
                .withSort(SortBuilders.fieldSort("age").order(SortOrder.ASC))
                .withPageable(PageRequest.of(0,2))
                .build();
        AggregatedPage<Book> books = elasticsearchTemplate.queryForPage(searchQuery, Book.class);
        books.forEach(System.out::println);
    }

    @Test
    public void testUpdate() {
        UpdateRequest updateRequest = new UpdateRequest();
        Map<String,Object> map = new HashMap<>(16);
        map.put("age",26);
        map.put("context","springboot集成Elasticsearch使用completion suggest实现自动关键字补全");
        updateRequest.doc(map);

        UpdateQuery build = new UpdateQueryBuilder().withIndexName("dangdang")
                .withType("book").withId("f155e977").withUpdateRequest(updateRequest).build();
        elasticsearchTemplate.update(build);
    }
    @Test
    public void testHighLight() {
        HighlightBuilder.Field context = new HighlightBuilder.Field("*")
                .requireFieldMatch(false) //关闭检索字段匹配
                .preTags("<span style='color:red'>").postTags("</span>");

        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withIndices("dangdang").withTypes("book")  //索引  type
                .withQuery(QueryBuilders.queryStringQuery("时光连接更新").field("name").field("context"))
                .withFilter(QueryBuilders.rangeQuery("age").gte(23).lte(25))  //过滤
                .withSort(SortBuilders.fieldSort("age").order(SortOrder.ASC)) //升序
                .withPageable(PageRequest.of(0, 2))   //分页
                .withHighlightFields(context).build(); //高亮

        AggregatedPage<Book> books = elasticsearchTemplate.queryForPage(searchQuery, Book.class, new SearchResultMapper() {
            @Override
            public <T> AggregatedPage<T> mapResults(SearchResponse searchResponse, Class<T> aClass, Pageable pageable) {
                //获取hits
                SearchHits hits = searchResponse.getHits();
                //获取检索结果hits
                SearchHit[] searchHits = hits.getHits();
                List<Book> books = new ArrayList<>();
                //遍历
                for (SearchHit searchHit : searchHits) {
                    Book book = new Book();
                    //获取原始结果
                    Map<String, Object> sourceAsMap = searchHit.getSourceAsMap();
                    Map<String, HighlightField> highlightFields = searchHit.getHighlightFields();
                    book.setContext(MapUtils.getString(sourceAsMap, "context"));
                    book.setName(MapUtils.getString(sourceAsMap, "name"));
                    if (highlightFields.containsKey("name")){
                        book.setName(highlightFields.get("name").fragments()[0].toString());
                    }
                    if (highlightFields.containsKey("context")){
                        book.setContext(highlightFields.get("context").fragments()[0].toString());
                    }
                    book.setId(MapUtils.getString(sourceAsMap, "id"));
                    book.setAge(MapUtils.getInteger(sourceAsMap, "age"));
                    book.setBir(new Date(MapUtils.getLong(sourceAsMap, "bir")));
                    books.add(book);
                }
                return new AggregatedPageImpl<T>((List<T>) books);
            }
        });
        books.forEach(System.out::println);
    }

}
