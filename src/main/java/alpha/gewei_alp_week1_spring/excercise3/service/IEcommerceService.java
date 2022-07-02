package alpha.gewei_alp_week1_spring.excercise3.service;

import alpha.gewei_alp_week1_spring.excercise3.exception.OutOfStockException;
import alpha.gewei_alp_week1_spring.excercise3.model.Order;
import alpha.gewei_alp_week1_spring.excercise3.model.Orderitem;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

public interface IEcommerceService {
    @Transactional
    Order buy(Order order) throws SQLException, OutOfStockException;

    @Transactional
    boolean deleteOrderAndItem(Integer orderId) throws SQLException;

    // item in the different order ,batch
    @Transactional
    List<Order> batchUpdateItemAndOrder(List<Orderitem> itemsBeUpdated) throws SQLException;
}
