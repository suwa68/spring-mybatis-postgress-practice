package alpha.gewei_alp_week1_spring;

import alpha.gewei_alp_week1_spring.excercise3.dao.OrderItemMapper;
import alpha.gewei_alp_week1_spring.excercise3.dao.OrderMapper;
import alpha.gewei_alp_week1_spring.excercise3.dao.ProductMapper;
import alpha.gewei_alp_week1_spring.excercise3.model.Order;
import alpha.gewei_alp_week1_spring.excercise3.model.Orderitem;
import alpha.gewei_alp_week1_spring.excercise3.model.Product;
import alpha.gewei_alp_week1_spring.excercise3.service.OrderItemService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
public class OrderAndItemTest {
    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderItemMapper itemMapper;

    //查詢
    @Test
     void testOrderMapper() throws SQLException {
        Order order = orderMapper.selectOrderById(1);
        System.out.println(order);
    }


    //新增 order 同時新增 item
    @Test
    void testInsertOrder() throws SQLException {
        Order order = new Order();
        order.setCustomerId(2);
        order.setPaymentId(1);
        order.setOrderStatus("未出貨");
        order.setAddress("台北市信義區");
        order.setReceiver("王曉明");
        order.setTel("09666");
        order.setTotalPrice(800);

        List<Orderitem> items = new LinkedList<>();
        Orderitem item1 = new Orderitem();
        item1.setProductId(1);
        item1.setQuantity(5);
        item1.setItemPrice(100);
        item1.setSubtotal(500);
        items.add(item1);

        Orderitem item2 = new Orderitem();
        item2.setProductId(3);
        item2.setQuantity(1);
        item2.setItemPrice(300);
        item2.setSubtotal(300);
        items.add(item2);

        order.setItems(items);
//        orderMapper.insertOrder(order.getCustomerId(),order.getPaymentId(),order.getOrderStatus()
//                ,order.getAddress(),order.getReceiver(),order.getTel(),order.getTotalPrice());

        orderMapper.insertOrder(order);

        System.out.println("有無拿到 item id ");
        System.out.println(order.getOrderId());
        order.setItemOrderId();

        itemMapper.insertBatchItem(order.getItems());
    }

    //刪除測試
    @Test
    void testDeleteOrder() throws SQLException {
        itemMapper.deleteItemByOrderId(18);
        orderMapper.deleteOrderById(18);
    }

    //order 單表格時更新測試
    @Test
    void TestUpdateOrderStatus() throws SQLException {
        //前端傳入的參數 this orderId
        Order order = orderMapper.selectOrderById(2);
        order.setOrderStatus("已出貨");
        //orderMapper.updateOrderStatus(order.getOrderStatus(), order.getOrderId());
        System.out.println("執行更新訂單狀態");
    }

    //order 更新收件相關
    @Test
    void TestUpdatedOrderReceiverData() throws SQLException {
        //前端傳入的參數 this orderId
        Order order = orderMapper.selectOrderById(3);
        order.setReceiver("新取件人");
        order.setAddress("新地址");
        order.setTel("新手機號");
        orderMapper.updateOrderReceiverData(order);
        System.out.println("執行更新收件相關");
    }

    //item 及 order 價量更新
    @Test
    void TestUpdateOrderAnsItemPQ() throws SQLException {
        Order order = orderMapper.selectOrderById(17);

        //假設傳入新的價格數量
        List<Orderitem> items = order.getItems();
        //在 service 層需思考選item 的方式????????
        Orderitem item1 = items.get(0);
        Orderitem item2 = items.get(1);

        item1.setQuantity(100);
        item1.setItemPrice(100);
        item1.calculateSubtotal();

        item2.setQuantity(50);
        item2.setItemPrice(50);
        item2.calculateSubtotal();

        order.calcalateTotal();

        itemMapper.updateItemPAndQ(item1);
        itemMapper.updateItemPAndQ(item2);
        orderMapper.updateTotalPrice(order);
    }

    //存貨控制相關
    @Autowired
   private  ProductMapper productMapper;

    @Test
    void testStockRelated() throws SQLException {

        // result map 跟 result type
         Product productNo3 = productMapper.selectProductById(3);
        System.out.println(productNo3);

        Integer no1Stock = productMapper.selectStockByProductId(3);
        System.out.println(no1Stock);

        productNo3.setInstock(500);
        productMapper.updateProductStock(productNo3);
        productMapper.updateProductStockByIdAndStock(600,3);
    }

    @Autowired
    OrderItemService itemService;

    @Test
    void testItemServiceSelectRelated() throws SQLException {

        Orderitem orderitem = itemService.selectItemByItemId(9);
        System.out.println(orderitem);
        List<Orderitem> items = itemService.selectOrderByCartId(17);
        System.out.println(items);

    }

    @Test
    void testOrderSelectByDate(){

        List<Order> orders = orderMapper.selectOrderByBeginAndEnd("2022-06-19","2022-06-20");

        for(Order order:orders){
            System.out.println(order);
        }

        //assertNull (orders,"this orders should be null");
        assertEquals(0,orders.size(),"not exist so must be 0");
    }

    //在 java 中看的到
    @Test
    void testUseOrderToGetPayment() throws SQLException {
        Orderitem orderitem = itemMapper.selectItemByItemId(12);
        System.out.println(orderitem.getProduct());
        //fetchType="eager"
    }

    //
    @Test
    void testTimeFromOrder() throws SQLException {
        Order order = orderMapper.selectOrderById(12);
        System.out.println(order);
    }
}
