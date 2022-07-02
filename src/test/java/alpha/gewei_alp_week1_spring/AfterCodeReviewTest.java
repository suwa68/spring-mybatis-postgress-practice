package alpha.gewei_alp_week1_spring;

import alpha.gewei_alp_week1_spring.excercise3.dao.PaymentMapper;
import alpha.gewei_alp_week1_spring.excercise3.dao.ProductMapper;
import alpha.gewei_alp_week1_spring.excercise3.model.Payment;
import alpha.gewei_alp_week1_spring.excercise3.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;

@SpringBootTest
public class AfterCodeReviewTest {

    @Autowired
    private PaymentMapper paymentMapper;

    @Autowired
    private ProductMapper productMapper;

    @Test
    void testGetPaymentWithoutResultMapResultType() throws SQLException {

        //case 1 移除 result Type resultMap -> 錯誤  至少要有一種
        //case 2 移除 resultMap
        Payment payment = paymentMapper.getPaymentById(1);

        System.out.println(payment);

    }

    @Test
    void testGetProductWithoutResultMap() throws SQLException {

        Product product = productMapper.selectProductById(1);

        System.out.println(product);
    }

}
