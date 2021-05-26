package com.bsfit.suaf.dao;

import com.bsfit.suaf.pojo.Book;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * <br>
 * ElasticsearchRepository<1,2> 操作对象泛型和主键泛型_id和id
 * 如果需要拓展按照官网给的格式进行扩展，只扩展不实现
 * </>
 * @author bangsun
 * @data 2021/5/25 16:23
 * @verson
 */
public interface BookRepository extends ElasticsearchRepository<Book,String> {

    /**
     * 根据name 和 context进行查找
     * */
    List<Book> findByNameAndContext(String nameKeyword,String contentKeyword);

    List<Book> findByAgeBetween(Integer a1,Integer a2);
}
