package alpha.gewei_alp_week1_spring.excercise3.dao;

import alpha.gewei_alp_week1_spring.excercise3.model.Customer;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface CustomerMapper {

    Customer getCustomerById(Integer id) throws SQLException;


}
