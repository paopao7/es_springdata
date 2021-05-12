package cn.itinfor.java;

import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringDataESSearchTest {
    @Autowired
    private ProductDao productDao;

    //term查询
    @Test
    public void termQuery() {
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("category", "手机");
        Iterable<Product> products = productDao.search(termQueryBuilder);

        for (Product product : products) {
            System.out.println(product);
        }
    }

    //term分页查询
    @Test
    public void termQueryByPage() {
        int currentPage = 0;
        int pageSize = 5;

        PageRequest pageRequest = PageRequest.of(currentPage, pageSize);

        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("category", "手机");
        Page<Product> products = productDao.search(termQueryBuilder, pageRequest);

        for (Product product : products) {
            System.out.println(product);
        }
    }
}
