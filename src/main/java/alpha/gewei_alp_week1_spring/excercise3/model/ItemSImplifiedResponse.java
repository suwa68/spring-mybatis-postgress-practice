package alpha.gewei_alp_week1_spring.excercise3.model;

public class ItemSImplifiedResponse {


    private String product_name;

    private Integer quantity;

    public ItemSImplifiedResponse(String product_name, Integer quantity) {

        this.product_name = product_name;
        this.quantity = quantity;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
