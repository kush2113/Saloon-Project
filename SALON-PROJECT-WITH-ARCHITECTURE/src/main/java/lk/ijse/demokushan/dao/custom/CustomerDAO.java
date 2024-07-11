package lk.ijse.demokushan.dao.custom;

import lk.ijse.demokushan.dao.CrudDAO;
import lk.ijse.demokushan.dao.SuperDAO;
import lk.ijse.demokushan.entity.Customer;

import java.sql.SQLException;
import java.util.List;

public interface CustomerDAO extends CrudDAO<Customer> {
    int getCustomerCount() throws SQLException, ClassNotFoundException;

    List<String> getCustomerId() throws SQLException, ClassNotFoundException;

    List<String> getCustomerId(String id) throws SQLException, ClassNotFoundException;

    List<String> getStatus() throws SQLException, ClassNotFoundException;

    List<String> getStatus(String status) throws SQLException, ClassNotFoundException;


}
