package alpha.gewei_alp_week1_spring.excercise3.service;

import alpha.gewei_alp_week1_spring.excercise3.dao.OrderItemMapper;
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
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OrderItemService implements IOrderItemService {

    @Autowired
    private OrderItemMapper itemMapper;
    @Autowired
    private OrderMapper orderMapper;

    //C
    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public List<Orderitem> insertItem(List<Orderitem> orderitems) throws SQLException {

        itemMapper.insertBatchItem(orderitems);

        return orderitems;
    }

    //R
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public List<Orderitem> selectOrderByCartId(Integer ordId) throws SQLException {

        Order order = orderMapper.selectOrderById(ordId);
        if (order == null) {
            return null;
        }

        return order.getItems();
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public Orderitem selectItemByItemId(Integer itemId) throws SQLException {

        return itemMapper.selectItemByItemId(itemId);

    }

    //U
    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public Orderitem updateItemPQ(Integer itemId, Integer price, Integer quantity) throws SQLException {
        Orderitem item = itemMapper.selectItemByItemId(itemId);
        item.setQuantity(quantity);
        item.setItemPrice(price);
        item.calculateSubtotal();

        //我先update item ecommerceService再從資料庫 select order udpate
        //還要再加update stock 在 ecommerce service
        itemMapper.updateItemPAndQ(item);
        return item;
    }

    //D //設定需與 order 共同刪除，call 要開 transaction
    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public boolean deleteItemByOrderId(Integer orderId) throws SQLException {
        List<Orderitem> orderitems = itemMapper.getItemByOrderId(orderId);
        if (orderitems == null || orderitems.size() == 0) {
            return false;
        }

        itemMapper.deleteItemByOrderId(orderId);

        return true;
    }


    @Override
    @Transactional
    public List<Integer> updateItemPAndQBatch(List<Orderitem> items) throws SQLException {
        List<List<Orderitem>> splitedLists = ListUtils.splitListBycapacity(items, 1000);
        Map<String, Object> map = new HashMap<>();
        for (List<Orderitem> itemList : splitedLists) {
            map.put("list", itemList);
            itemMapper.updateItemAAndQBatch(map);
        }

        //returm 所有 unique 的 order_id list

        List<Integer> updatedOrderIdList =
                items.stream().map(Orderitem::getOrderId)
                        .distinct().collect(Collectors.toList());

        return updatedOrderIdList;
    }

    @Override
    @Transactional
    public List<Orderitem> convertMapToItemAndUpdateBean(List<Map<String, Integer>> mapList) throws SQLException {

        List<Orderitem> items = new LinkedList<>();

        for (Map<String, Integer> map : mapList) {
            System.out.println("checkMap content");
            System.out.println(map);
            Orderitem orderitem = itemMapper.selectItemByItemId(map.get("itemid"));
            //NPE 應該是 item 沒有選到
            System.out.println("被選到的item id" + map.get("itemId"));
            System.out.println(orderitem);

            orderitem.setItemPrice(map.get("newprice"));
            orderitem.setQuantity(map.get("newquantity"));
            orderitem.calculateSubtotal();
            items.add(orderitem);
        }

        return items;

    }
}
