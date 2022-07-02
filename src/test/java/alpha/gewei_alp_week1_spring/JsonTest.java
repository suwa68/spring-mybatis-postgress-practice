package alpha.gewei_alp_week1_spring;

import alpha.gewei_alp_week1_spring.excercise3.dao.OrderItemMapper;
import alpha.gewei_alp_week1_spring.excercise3.dao.OrderMapper;
import alpha.gewei_alp_week1_spring.excercise3.model.Order;
import alpha.gewei_alp_week1_spring.excercise3.model.Orderitem;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

@SpringBootTest
public class JsonTest {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderItemMapper orderItemMapper;

    //0628 問題 功能正常
    //但INSERT 和 SELECT 時出現問題
    @Test
    void testSelectOrderAndRelatedTimeAndAssociation() throws SQLException {

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

        System.out.println("在新增前的 order 狀態");
        System.out.println("在新增前的 order 狀態"+order);

        orderMapper.insertOrder(order);
        System.out.println("在新增後未 SEELCT 的 order 狀態");
        System.out.println("在新增後未 SEELCT 的 order 狀態" + order);

        //insert order 之後自動產生id 了
        //此時先幫 item set orderId 後新增至order item
        order.setItemOrderId();
        orderItemMapper.insertBatchItem(order.getItems());


        System.out.println("重新select 後的 order 狀態");
        Order orderTest = orderMapper.selectOrderById(order.getOrderId());
        System.out.println("重新select 後的 order 狀態" + orderTest);
        //重新 select 過後是能從 db 獲得 order 的 新增修改日期的 payment 物件也或取到了
        //從 db fetch 也能或取到相應的order id

        //理論上來說 新增之後重新在從 db get 一次 就有該物件全部得資料
        //線將 service layer 做的事情簡單化

        //在 insert 時 createdDate 和 updateDate 是 null
        //把 payment object打開
        //payment object 也是 null
        // item 的 product 也是 null
        // null 的部分應該是由於 未與 db 互動
        //確認新增流程

        //在新增 order 時其實有特別從 db select 過了

    }
}
