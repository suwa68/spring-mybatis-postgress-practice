package alpha.gewei_alp_week1_spring.excercise3.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

public class Orderitem implements Serializable {

    private Integer itemId;
    @JsonIgnore
    private Order order;
    private Product product;

    private Integer itemPrice;

    private Integer quantity;

    private Integer subtotal;

    //for json ignore
    @JsonIgnore
    private Integer orderId;

    private Integer productId;
    //

    //for business logic

    //
    public Integer calculateSubtotal() {
        if (this.itemPrice == null || this.quantity == null
                || this.itemPrice <= 0 || this.quantity <= 0) {
            this.subtotal = 0;
            return 0;
        }

        Integer subtotal = this.itemPrice * this.quantity;
        this.subtotal = subtotal;
        return subtotal;
    }


    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.productId = product.getProductId();
        this.product = product;
    }

    public Integer getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(Integer itemPrice) {
        this.itemPrice = itemPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Integer subtotal) {
        this.subtotal = subtotal;
    }

    @Override
    public String toString() {
        return "Orderitem{" +
                "orderId" + this.orderId +
                "itemId=" + itemId +
                ", product=" + product +
                ", itemPrice=" + itemPrice +
                ", quantity=" + quantity +
                ", subtotal=" + subtotal +
                ", orderId=" + orderId +
                '}';
    }
}
