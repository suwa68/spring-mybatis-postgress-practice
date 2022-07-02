package alpha.gewei_alp_week1_spring.excercise3.controler;

import alpha.gewei_alp_week1_spring.excercise3.dao.OrderMapper;
import alpha.gewei_alp_week1_spring.excercise3.exception.OutOfStockException;
import alpha.gewei_alp_week1_spring.excercise3.model.*;
import alpha.gewei_alp_week1_spring.excercise3.service.IEcommerceService;
import alpha.gewei_alp_week1_spring.excercise3.service.IOrderItemService;
import alpha.gewei_alp_week1_spring.excercise3.service.IOrderService;
import alpha.gewei_alp_week1_spring.excercise3.utils.JsonConveter;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/ecommerce")
public class OrderRestController {

    @Autowired
    private IOrderService orderService;

    @Autowired
    private IOrderItemService itemService;

    @Autowired
    private IEcommerceService ecommerceService;

    @Autowired
    private JsonConveter jsonConveter;

    //a 新增
    @PostMapping("/order")
    private @ResponseBody Order insert(@RequestBody Order order) throws OutOfStockException, SQLException {
        return ecommerceService.buy(order);
    }

    //b 更新
    // 批次更新 更新多個 item 的價格與數量 並回傳 item
    //batch update map key : itemid ,newprice ,newquantity
    @PostMapping("/order/updateitems")
    private @ResponseBody List<Order> updateOrderAndRelated(@RequestBody List<Map<String, Integer>> updateKeyValues) throws SQLException {

        return ecommerceService.batchUpdateItemAndOrder(itemService.convertMapToItemAndUpdateBean(updateKeyValues));
    }


    //public boolean updateOrdReceiver(Map<String, String> newReceiverMap, Integer thisorderId) throws SQLException {
    //改傳入 map String newAddress, String newReceiver, String newTel, Integer thisorderId
    //順便測試 post method pathvariable
    @PutMapping("/order/updateorderreceiver/{orderid}")
    private @ResponseBody boolean updateOrderReceiver(@RequestBody Map<String, String> receirverMap
            , @PathVariable("orderid") Integer thisOrderId) throws SQLException {

        return orderService.updateOrdReceiver(receirverMap, thisOrderId);
    }

    //newReceiverMap
    //updateOrderStatus

    //public boolean updateOrdStatus(String newOrderStatus, Integer thisorderId) throws SQLException {
    @PutMapping("/order/updateorderstatus/{orderid}")
    private @ResponseBody boolean updateOrderStatus(@RequestParam("newOrderStatus") String newOrderStatusStr
            , @PathVariable("orderid") Integer orderid) throws SQLException {

        return orderService.updateOrdStatus(newOrderStatusStr, orderid);

    }

    //c 刪除訂單並刪除訂單明細
    @DeleteMapping("/order/{orderid}")
    private @ResponseBody boolean deleteOrderAndRelatedItem(@PathVariable("orderid") Integer orderId) throws SQLException {
        return ecommerceService.deleteOrderAndItem(orderId);
    }

    //d 時間查詢 以及 單筆訂單查詢
    //localhost:8080/grant/ecommerce/selectbydates?begindate=2022-06-19&enddate=2022-06-28
    @GetMapping("/selectbydates")
    private @ResponseBody List<OrderWithitemResponse> selectOrderByDate(@RequestParam("begindate") String begindate
            , @RequestParam("enddate") String enddate) {

        List<Order> selectedOriginalData = orderService.selectOrderByBegunAndEnd(begindate, enddate);

        return jsonConveter.parseOrderListToOrderResponseList(selectedOriginalData);
    }

    //localhost:8080/grant/ecommerce/order/19
    @GetMapping("/order/{orderid}")
    private @ResponseBody OrderWithitemResponse selectOrderAnsShowOrderWithItem
    (@PathVariable("orderid") Integer orderId) throws SQLException {

        Order orderOriginal = orderService.selectOrderById(orderId);

        return jsonConveter.parseordertoorderwithitemresponse(orderOriginal);
    }

    //e
    @GetMapping("/item/{itemid}")
    private @ResponseBody OrderitemResponse selectitembyitemid(@PathVariable("itemid") Integer itemid) throws SQLException {

        Orderitem orderitemoriginal = itemService.selectItemByItemId(itemid);

        return jsonConveter.parseItemToItemResponseList(orderitemoriginal);
    }

}
