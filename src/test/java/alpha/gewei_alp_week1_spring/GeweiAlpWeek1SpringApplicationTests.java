package alpha.gewei_alp_week1_spring;

import alpha.gewei_alp_week1_spring.excercise3.dao.CustomerMapper;
import alpha.gewei_alp_week1_spring.excercise3.dao.OrderMapper;
import alpha.gewei_alp_week1_spring.excercise3.dao.PaymentMapper;
import alpha.gewei_alp_week1_spring.excercise3.dao.ProductMapper;
import alpha.gewei_alp_week1_spring.excercise3.model.Customer;

import alpha.gewei_alp_week1_spring.excercise3.model.Order;
import alpha.gewei_alp_week1_spring.excercise3.model.Payment;
import alpha.gewei_alp_week1_spring.excercise3.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


@SpringBootTest
class GeweiAlpWeek1SpringApplicationTests {



    @Autowired
    private  CustomerMapper customerMapper;

    @Test
    public void testCustomerMappeer() throws  Exception{
        Customer customer = customerMapper.getCustomerById(1);
        System.out.println(customer);
    }

    @Autowired
    private ProductMapper productMapper;

    @Test
    public void testProductMapper() throws SQLException {
        List<Product> products = productMapper.selectAllProduct();
        Product product = productMapper.selectProductById(1);
        System.out.println(product);
        System.out.println(products);
    }

    @Autowired
    private PaymentMapper paymentMapper;

    //@Test
    public void testPaymentMapper() throws SQLException {
        Payment payment = paymentMapper.getPaymentById(1);
        System.out.println(payment);
    }


}
