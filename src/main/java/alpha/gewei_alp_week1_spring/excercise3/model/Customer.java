package alpha.gewei_alp_week1_spring.excercise3.model;

import java.util.Date;

public class Customer {

    private Integer id;

    private String pwd;

    private String name;

    private String phoneNumber;

    private Date sigininDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getSigininDate() {
        return sigininDate;
    }

    public void setSigininDate(Date sigininDate) {
        this.sigininDate = sigininDate;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", pwd='" + pwd + '\'' +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", sigininDate=" + sigininDate +
                '}';
    }
}
