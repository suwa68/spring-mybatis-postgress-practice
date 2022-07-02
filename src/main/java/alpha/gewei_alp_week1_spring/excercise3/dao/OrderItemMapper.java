package alpha.gewei_alp_week1_spring.excercise3.dao;

import alpha.gewei_alp_week1_spring.excercise3.model.Orderitem;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Mapper
public interface OrderItemMapper {

    //for xml use
    List<Orderitem> getItemByOrderId(Integer orderId) throws SQLException;

    Orderitem selectItemByItemId(Integer itemId) throws SQLException;

    void insertBatchItem(List<Orderitem> items) throws SQLException;

    void deleteItemByOrderId(Integer orderId) throws SQLException;

    //section for update quantity
    void deleteItemByItemId(Orderitem orderitem) throws SQLException;

    void updateItemPAndQ(Orderitem orderitem) throws SQLException;
    //

    void updateItemAAndQBatch(Map<String, Object> map) throws SQLException;
}
