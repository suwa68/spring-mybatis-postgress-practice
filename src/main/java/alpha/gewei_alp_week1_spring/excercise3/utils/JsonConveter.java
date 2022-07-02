package alpha.gewei_alp_week1_spring.excercise3.utils;

import alpha.gewei_alp_week1_spring.excercise3.model.*;
import alpha.gewei_alp_week1_spring.excercise3.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class JsonConveter {

    @Autowired
    private IOrderService orderService;

    public List<OrderWithitemResponse> parseOrderListToOrderResponseList(List<Order> selectedOriginalData) {

        List<OrderWithitemResponse> lists = new LinkedList<>();
        if (selectedOriginalData.size() != 0) {

            selectedOriginalData.forEach((orderOrign) -> {
                        lists.add(
                                parseordertoorderwithitemresponse(orderOrign)
                        );
                    }
            );

        }

        return lists;
    }

    public OrderWithitemResponse parseordertoorderwithitemresponse(Order orderOriginal) {

        List<ItemSImplifiedResponse> items = new LinkedList<>();

        for (Orderitem item : orderOriginal.getItems()) {
            items.add(parseitemtosimplified(item));
        }

        return new OrderWithitemResponse(orderOriginal.getOrderId(), orderOriginal.getCustomerId(),
                orderOriginal.getCustomer().getName(), orderOriginal.getPayment().getType(), orderOriginal.getOrderStatus()
                , orderOriginal.getAddress(), orderOriginal.getReceiver(), orderOriginal.getTel(), orderOriginal.getTotalPrice()
                , orderOriginal.getCreatedDate(), orderOriginal.getUpdatedDate(), items);
    }

    private ItemSImplifiedResponse parseitemtosimplified(Orderitem item) {

        return new ItemSImplifiedResponse(item.getProduct().getProductName(), item.getQuantity());
    }

    public OrderitemResponse parseItemToItemResponseList(Orderitem orderitemoriginal) {

        if (orderitemoriginal != null) {
            return new OrderitemResponse(
                    orderitemoriginal.getItemId(),
                    orderitemoriginal.getOrderId(),
                    orderitemoriginal.getProductId(),
                    orderitemoriginal.getProduct().getProductName(),
                    orderitemoriginal.getItemPrice(),
                    orderitemoriginal.getQuantity(),
                    orderitemoriginal.getSubtotal());
        }
        return null;
    }
}
