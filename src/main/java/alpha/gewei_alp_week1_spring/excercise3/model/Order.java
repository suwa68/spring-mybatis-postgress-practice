package alpha.gewei_alp_week1_spring.excercise3.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Order implements Serializable {

    private Integer orderId;

    private List<Orderitem> items;


    @JsonIgnore
    private Customer customer;

    private Payment payment;

    private String orderStatus;

    private String address;

    private String receiver;

    private String tel;

    private Integer totalPrice;

    private Date createdDate;

    private Date updatedDate;

    //for rest api
    private Integer customerId;

    private Integer paymentId;
    //

    //business logic related
    public Integer calcalateTotal() {
        List<Orderitem> items = this.items;

        //deal NPE
        if (items == null || items.size() == 0) {
            this.totalPrice = 0;
            return 0;
        }

        Integer sum = 0;
        for (Orderitem item : items) {
            sum += item.getSubtotal();
        }
        this.totalPrice = sum;
        return sum;
    }

    public Integer getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Integer paymentId) {
        this.paymentId = paymentId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customerId = customer.getId();
        this.customer = customer;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.paymentId = payment.getPaymentId();
        this.payment = payment;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
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

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public List<Orderitem> getItems() {
        return items;
    }

    public void setItems(List<Orderitem> items) {
        this.items = items;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setItemOrderId() {
        if (items != null && items.size() != 0) {
            items.forEach(items -> items.setOrderId(this.orderId));
        }
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", items=" + items +
                ", orderStatus='" + orderStatus + '\'' +
                ", address='" + address + '\'' +
                ", receiver='" + receiver + '\'' +
                ", tel='" + tel + '\'' +
                ", totalPrice=" + totalPrice +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                ", customerId=" + customerId +
                ", paymentId=" + paymentId +
                ", paymentObject" + payment +
                '}';
    }
}
