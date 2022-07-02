package alpha.gewei_alp_week1_spring.excercise3.model;

import java.io.Serializable;

public class Product implements Serializable {

    private Integer productId;

    private String productName;

    private String productInfo;

    private Integer instock;

    private Integer price;

    private Boolean available;

    private String unit;


    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductInfo() {
        return productInfo;
    }

    public void setProductInfo(String productInfo) {
        this.productInfo = productInfo;
    }

    public Integer getInstock() {
        return instock;
    }

    public void setInstock(Integer instock) {
        this.instock = instock;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", productInfo='" + productInfo + '\'' +
                ", inStock=" + instock +
                ", price=" + price +
                ", available=" + available +
                ", unit='" + unit + '\'' +
                '}';
    }
}
