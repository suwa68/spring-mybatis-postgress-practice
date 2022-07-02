package alpha.gewei_alp_week1_spring.excercise3.dao;

import alpha.gewei_alp_week1_spring.excercise3.model.Payment;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;

@Mapper
public interface PaymentMapper {

    Payment getPaymentById(Integer id) throws SQLException;

}
