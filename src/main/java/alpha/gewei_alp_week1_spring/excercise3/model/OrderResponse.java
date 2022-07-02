package alpha.gewei_alp_week1_spring.excercise3.model;

import java.util.Date;

public class OrderResponse {

    private Integer orderId;

    private Integer customer_id;

    private String customer_name;

    private String payment;

    private String order_status;

    private String address;

    private String receiver;

    private String telephone;

    private Integer total_price;

    private Date created_date;

    private Date updated_date;

    public OrderResponse(Integer orderId, Integer customer_id, String customer_name, String payment, String order_status, String address, String receiver, String telephone, Integer total_price, Date created_date, Date updated_date) {
        this.orderId = orderId;
        this.customer_id = customer_id;
        this.customer_name = customer_name;
        this.payment = payment;
        this.order_status = order_status;
        this.address = address;
        this.receiver = receiver;
        this.telephone = telephone;
        this.total_price = total_price;
        this.created_date = created_date;
        this.updated_date = updated_date;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Integer customer_id) {
        this.customer_id = customer_id;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Integer getTotal_price() {
        return total_price;
    }

    public void setTotal_price(Integer total_price) {
        this.total_price = total_price;
    }

    public Date getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }

    public Date getUpdated_date() {
        return updated_date;
    }

    public void setUpdated_date(Date updated_date) {
        this.updated_date = updated_date;
    }
}
