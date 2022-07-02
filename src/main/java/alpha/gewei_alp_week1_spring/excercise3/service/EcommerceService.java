package alpha.gewei_alp_week1_spring.excercise3.service;

import alpha.gewei_alp_week1_spring.excercise3.exception.OutOfStockException;
import alpha.gewei_alp_week1_spring.excercise3.model.Order;
import alpha.gewei_alp_week1_spring.excercise3.model.Orderitem;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class EcommerceService implements IEcommerceService {

    @Autowired
    private IOrderService orderService;

    @Autowired
    private IOrderItemService itemService;

    @Autowired
    private IProdcuctService prodcuctService;

    @Override
    @Transactional
    public Order buy(Order order) throws SQLException, OutOfStockException {
        //stock update , may throw exception
        //List<Orderitem> items = order.getItems();
        //這邊會有 exception 是因為 orderItem 物件還未與db 互動 次序應該要在從 db select 之後
//        for(Orderitem item : items){
//            prodcuctService.udpateStock(item.getProductId(),item.getQuantity());
//        }
        orderService.insertOrder(order);
        order.setItemOrderId();
        itemService.insertItem(order.getItems());

        //test 1 都不與 db 互動 此次看一下此次的物件狀況
        //此次應該 會有 orderId 其他關聯都是 null -> 沒錯

        //test 2 重新 select 一次 看一下 order 的狀況 -> 此次正確
        Order newOrderSelectFromDB = orderService.selectOrderById(order.getOrderId());

        //test 3 更新一下 ordeitem 的 product
        for (Orderitem item : newOrderSelectFromDB.getItems()) {
            prodcuctService.udpateStock(item.getProductId(), item.getQuantity());
        }
        //成功更新


        return newOrderSelectFromDB;
    }

    @Override
    @Transactional
    public boolean deleteOrderAndItem(Integer orderId) throws SQLException {
        boolean itemDeleteOrNot = itemService.deleteItemByOrderId(orderId);
        boolean orderDeleteOrNot = orderService.deleteOrderByOrderId(orderId);
        return itemDeleteOrNot && orderDeleteOrNot;
    }


    //batch update List<Map<String , integer>>
//    @Transactional
//    public List<Integer> updateOrderAndItem(List< Map<String,Integer> > listOfUpdateMap) throws SQLException {
//        //map key itemId , price , quantity
//        String key[] = new String[]{"itemId","price","quantity"};
//        //item 更新
//        for (Map<String,Integer> updateMap: listOfUpdateMap
//             ) {
//            itemService.updateItemPQ(updateMap.get(key[0]),updateMap.get(key[2]),updateMap.get(key[3]));
//        }
//        //return 受到影響的 order
//        return  null;
//    }

    //update price and quantity for item


    // item in the different order ,batch
    @Override
    @Transactional
    public List<Order> batchUpdateItemAndOrder(List<Orderitem> itemsBeUpdated) throws SQLException {
        //batch udpate for item
        List<Integer> updatedOrderIdList = itemService.updateItemPAndQBatch(itemsBeUpdated);
        //好像用 union 可以 batch select 在此先用 直接 select
        List<Order> ordersWaitingForupdatedTotal = new LinkedList<>();
        for (Integer orderid : updatedOrderIdList) {
            Order thisOrder = orderService.selectOrderById(orderid);
            //calculate total
            thisOrder.calcalateTotal();
            ordersWaitingForupdatedTotal.add(thisOrder);
        }

        //batch udpate for order
        orderService.batchUpdateOrderPrice(ordersWaitingForupdatedTotal);

        return ordersWaitingForupdatedTotal;
    }


}
