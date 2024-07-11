package lk.ijse.demokushan.bo.custom;

import lk.ijse.demokushan.bo.SuperBO;
import lk.ijse.demokushan.dto.CustomerDTO;
import lk.ijse.demokushan.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface CustomerBO extends SuperBO {

    String generateNewID() throws SQLException, ClassNotFoundException;

    boolean add(CustomerDTO customer) throws SQLException, ClassNotFoundException;

    int getCustomerCount() throws SQLException, ClassNotFoundException;

    ArrayList<Customer> getAll() throws SQLException, ClassNotFoundException;

    Customer search(String id) throws SQLException, ClassNotFoundException;

    boolean delete(String id) throws SQLException, ClassNotFoundException;

    boolean update(CustomerDTO customer) throws SQLException, ClassNotFoundException;

    List<String> getCustomerId() throws SQLException, ClassNotFoundException;

    List<String> getCustomerId(String id) throws SQLException, ClassNotFoundException;

    List<String> getStatus() throws SQLException, ClassNotFoundException;

    List<String> getStatus(String status) throws SQLException, ClassNotFoundException;
}
