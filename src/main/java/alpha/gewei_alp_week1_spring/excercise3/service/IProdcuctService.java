package alpha.gewei_alp_week1_spring.excercise3.service;

import alpha.gewei_alp_week1_spring.excercise3.exception.OutOfStockException;
import alpha.gewei_alp_week1_spring.excercise3.model.Product;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

public interface IProdcuctService {
    @Transactional(isolation = Isolation.READ_COMMITTED)
    Product getProductById(Integer productId) throws SQLException;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = OutOfStockException.class)
    void udpateStock(Integer productId, Integer stocktDeduction) throws OutOfStockException, SQLException;
}
