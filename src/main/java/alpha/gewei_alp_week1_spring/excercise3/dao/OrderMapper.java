package alpha.gewei_alp_week1_spring.excercise3.dao;

import alpha.gewei_alp_week1_spring.excercise3.model.Order;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Mapper
public interface OrderMapper {

    Order selectOrderById(Integer oid) throws SQLException;

    //    Integer insertOrder(Integer customerId,Integer paymentId,String orderStatus
//    ,String address, String receiver , String tel , Integer totalPrice);
    void insertOrder(Order order) throws SQLException;

    void deleteOrderById(Integer orderId) throws SQLException;

    void updateOrderStatus(String newOrderStatus, Integer thisorderId) throws SQLException;

    //void updateOrderReceiverData(String newAddress , String newReceiver,String newTel , Integer thisorderId);
    void updateOrderReceiverData(Order order) throws SQLException;

    void updateTotalPrice(Order order) throws SQLException;

    void batchUpdateOrderPrice(Map<String, Object> map) throws SQLException;

    List<Order> selectOrderByBeginAndEnd(String startDate, String endDate);
}
