package alpha.gewei_alp_week1_spring.excercise3.service;

import alpha.gewei_alp_week1_spring.excercise3.model.Orderitem;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface IOrderItemService {
    //C
    @Transactional(propagation = Propagation.MANDATORY)
    List<Orderitem> insertItem(List<Orderitem> orderitems) throws SQLException;

    //R
    @Transactional(isolation = Isolation.READ_COMMITTED)
    List<Orderitem> selectOrderByCartId(Integer ordId) throws SQLException;

    @Transactional(isolation = Isolation.READ_COMMITTED)
    Orderitem selectItemByItemId(Integer itemId) throws SQLException;

    //U
    @Transactional(propagation = Propagation.MANDATORY)
    Orderitem updateItemPQ(Integer itemId, Integer price, Integer quantity) throws SQLException;

    //D //設定需與 order 共同刪除，call 要開 transaction
    @Transactional(propagation = Propagation.MANDATORY)
    boolean deleteItemByOrderId(Integer orderId) throws SQLException;


    List<Integer> updateItemPAndQBatch(List<Orderitem> list) throws SQLException;

    @Transactional
    List<Orderitem> convertMapToItemAndUpdateBean(List<Map<String, Integer>> mapList) throws SQLException;
}
