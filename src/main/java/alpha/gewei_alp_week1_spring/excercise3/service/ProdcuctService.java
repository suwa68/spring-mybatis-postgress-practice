package alpha.gewei_alp_week1_spring.excercise3.service;

import alpha.gewei_alp_week1_spring.excercise3.dao.ProductMapper;
import alpha.gewei_alp_week1_spring.excercise3.exception.OutOfStockException;
import alpha.gewei_alp_week1_spring.excercise3.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

@Service
public class ProdcuctService implements IProdcuctService {

    @Autowired
    ProductMapper productMapper;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public Product getProductById(Integer productId) throws SQLException {
        return productMapper.selectProductById(productId);
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY, rollbackFor = OutOfStockException.class)
    public void udpateStock(Integer productId, Integer stocktDeduction) throws OutOfStockException, SQLException {

        Integer stock = productMapper.selectStockByProductId(productId);
        System.out.println(stock);
        if (stock < stocktDeduction) {
            throw new OutOfStockException("product " + productId + "stock is not enough");
        }

        stock -= stocktDeduction;

        productMapper.updateProductStockByIdAndStock(stock, productId);
    }
}
