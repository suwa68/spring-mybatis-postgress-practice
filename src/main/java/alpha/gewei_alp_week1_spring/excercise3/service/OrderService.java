package alpha.gewei_alp_week1_spring.excercise3.service;

import alpha.gewei_alp_week1_spring.excercise3.dao.OrderMapper;
import alpha.gewei_alp_week1_spring.excercise3.model.Order;
import alpha.gewei_alp_week1_spring.excercise3.model.Orderitem;
import alpha.gewei_alp_week1_spring.excercise3.utils.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.*;


@Service
public class OrderService implements IOrderService {

    @Autowired
    private OrderMapper orderMapper;

    //C
    //必須與 orderitem 一起在 ecommerceServiceLayer 執行
    @Override
    @Transactional(propagation = Propagation.MANDATORY, isolation = Isolation.READ_COMMITTED)
    public Order insertOrder(Order order) throws SQLException {
        orderMapper.insertOrder(order);

        //order id amd item id  auto generated
        //order =orderMapper.selectOrderById(order.getOrderId());

        return order;
    }

    //R
    @Override
    @Transactional
    public Order selectOrderById(Integer ordId) throws SQLException {
        return orderMapper.selectOrderById(ordId);
    }

    @Transactional
    @Override
    public List<Order> selectOrderByBegunAndEnd(String startDate, String endDate) {
        //date format must be sql date format
        return orderMapper.selectOrderByBeginAndEnd(startDate, endDate);
    }

    //U
    //更新訂單狀態
    @Override
    @Transactional
    public boolean updateOrdStatus(String newOrderStatus, Integer thisorderId) throws SQLException {
        //改傳入Map String newOrderStatus, Integer thisorderId
        Order order = orderMapper.selectOrderById(thisorderId);
        if (order == null) {
            return false;
        }
        orderMapper.updateOrderStatus(newOrderStatus, thisorderId);
        return true;
    }

    //更新收付款人資料
    @Override
    @Transactional
    public boolean updateOrdReceiver(Map<String, String> newReceiverMap, Integer thisorderId) throws SQLException {
        //改傳入 map String newAddress, String newReceiver, String newTel, Integer thisorderId
        //順便測試 post method pathvariable
        Order order = orderMapper.selectOrderById(thisorderId);
        if (order == null) {
            return false;
        }
        order.setReceiver(newReceiverMap.get("newReceiver"));
        order.setTel(newReceiverMap.get("newTel"));
        order.setAddress(newReceiverMap.get("newAddress"));
        //order.setUpdatedDate(new Date());  mapper 改為 updated_date = now()
        orderMapper.updateOrderReceiverData(order);
        return true;
    }

    //更新總價
    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public Order updateTotallPrice(List<Orderitem> orderitemList) throws SQLException {
        if (orderitemList == null || orderitemList.size() == 0) {
            return null;
        }
        //未更新 subtotal 的 order
        Order order
                = orderMapper.selectOrderById(orderitemList.get(0).getOrderId());

        order.setItems(orderitemList);
        order.calcalateTotal();
        orderMapper.updateTotalPrice(order);

        return order;
    }


    //D
    //需在business layer 層
    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public boolean deleteOrderByOrderId(Integer orderId) throws SQLException {
        Order order = orderMapper.selectOrderById(orderId);
        if (order == null) {
            return false;
        }

        orderMapper.deleteOrderById(orderId);
        return true;
    }

    @Override
    public List<Order> batchUpdateOrderPrice(List<Order> orders) throws SQLException {

        List<List<Order>> splitedOrderLists = ListUtils.splitListBycapacity(orders, 1000);
        Map<String, Object> map = new HashMap<>();
        for (List<Order> orderList : splitedOrderLists) {
            map.put("list", orderList);
            orderMapper.batchUpdateOrderPrice(map);
        }

        return orders;
    }
}
