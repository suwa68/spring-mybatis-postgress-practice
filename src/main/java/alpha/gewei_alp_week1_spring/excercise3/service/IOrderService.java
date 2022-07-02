package alpha.gewei_alp_week1_spring.excercise3.service;

import alpha.gewei_alp_week1_spring.excercise3.model.Order;
import alpha.gewei_alp_week1_spring.excercise3.model.Orderitem;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface IOrderService {
    //C
    //必須與 orderitem 一起在 ecommerceServiceLayer 執行
    @Transactional(propagation = Propagation.MANDATORY, isolation = Isolation.READ_COMMITTED)
    Order insertOrder(Order order) throws SQLException;

    //R
    @Transactional
    Order selectOrderById(Integer ordId) throws SQLException;

    @Transactional
    List<Order> selectOrderByBegunAndEnd(String startDate, String endDate);

    //U
    //更新訂單狀態
    @Transactional
    boolean updateOrdStatus(String newOrderStatus, Integer thisorderId) throws SQLException;

    //更新收付款人資料
    @Transactional
    boolean updateOrdReceiver(Map<String, String> newReceiverMap, Integer thisorderId) throws SQLException;

    //更新總價
    @Transactional(propagation = Propagation.MANDATORY)
    Order updateTotallPrice(List<Orderitem> orderitemList) throws SQLException;

    //D
    //需在business layer 層
    @Transactional(propagation = Propagation.MANDATORY)
    boolean deleteOrderByOrderId(Integer orderId) throws SQLException;

    List<Order> batchUpdateOrderPrice(List<Order> orders) throws SQLException;
}
