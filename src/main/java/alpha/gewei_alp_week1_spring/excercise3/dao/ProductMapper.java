package alpha.gewei_alp_week1_spring.excercise3.dao;

import alpha.gewei_alp_week1_spring.excercise3.model.Product;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface ProductMapper {
    Product selectProductById(Integer productId) throws SQLException;

    List<Product> selectAllProduct() throws SQLException;

    //存貨控制相關
    Integer selectStockByProductId(Integer productId) throws SQLException;

    void updateProductStock(Product product) throws SQLException;

    void updateProductStockByIdAndStock(Integer newStock, Integer productId) throws SQLException;
}
