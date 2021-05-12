package cn.itinfor.java;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringDataESProductDaoTest {
    @Autowired
    private ProductDao productDao;

    //新增
    @Test
    public void add() {
        Product product = new Product();
        product.setId(3L);
        product.setTitle("小米手机");
        product.setCategory("手机");
        product.setPrice(3999.00);
        product.setImages("http://www.itinfor.cn/xiaomi.jpg");
        productDao.save(product);
    }

    //查询所有
    @Test
    public void findAll() {
        Iterable<Product> iterable = productDao.findAll();

        for (Product product : iterable) {
            System.out.println(product);
        }
    }

    //删除
    @Test
    public void delete() {
        Product product = new Product();
        product.setId(3L);
        productDao.delete(product);
    }

    //批量新增
    @Test
    public void saveAll() {
        ArrayList<Product> productList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Product product = new Product();
            product.setId(Long.valueOf(i));
            product.setTitle("[" + i + "]小米手机");
            product.setCategory("手机");
            product.setPrice(1999.0 + i);
            product.setImages("http://itinfor.cn/xm.jpg");
            productList.add(product);
        }

        productDao.saveAll(productList);
    }

    //分页查询
    @Test
    public void findByPageable() {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        int currentPage = 0;
        int pageSize = 5;

        PageRequest pageRequest = PageRequest.of(currentPage, pageSize, sort);

        Page<Product> productPage = productDao.findAll(pageRequest);

        for (Product product : productPage.getContent()) {
            System.out.println(product);
        }
    }
}
