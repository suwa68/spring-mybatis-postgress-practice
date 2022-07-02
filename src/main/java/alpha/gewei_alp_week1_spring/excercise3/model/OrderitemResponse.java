package alpha.gewei_alp_week1_spring.excercise3.model;

public class OrderitemResponse {

    private Integer item_id;

    private Integer order_id;

    private Integer product_id;

    private String product_name;

    private Integer item_price;

    private Integer quantity;

    private Integer subtotal;

    public OrderitemResponse(Integer item_id, Integer order_id, Integer product_id, String product_name, Integer item_price, Integer quantity, Integer subtotal) {
        this.item_id = item_id;
        this.order_id = order_id;
        this.product_id = product_id;
        this.product_name = product_name;
        this.item_price = item_price;
        this.quantity = quantity;
        this.subtotal = subtotal;
    }

    public Integer getItem_id() {
        return item_id;
    }

    public void setItem_id(Integer item_id) {
        this.item_id = item_id;
    }

    public Integer getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }

    public Integer getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public Integer getItem_price() {
        return item_price;
    }

    public void setItem_price(Integer item_price) {
        this.item_price = item_price;
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

}
