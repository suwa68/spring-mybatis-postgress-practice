package alpha.gewei_alp_week1_spring.excercise3.model;

import java.io.Serializable;

public class Payment implements Serializable {

    private Integer paymentId;

    private String type;

    public Integer getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Integer paymentId) {
        this.paymentId = paymentId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
