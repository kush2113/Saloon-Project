package lk.ijse.demokushan.dao.custom.impl;

import lk.ijse.demokushan.dao.SQLUtil;
import lk.ijse.demokushan.dao.custom.CustomerDAO;
import lk.ijse.demokushan.entity.Customer;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public  String generateNewID() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT customerId FROM customer ORDER BY customerId DESC LIMIT 1");
        if(resultSet.next()) {
            String customerId = resultSet.getString(1);
            return customerId;
        }
        return null;
    }

    @Override
    public  boolean add(Customer customer) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("INSERT INTO customer VALUES(?, ?, ?, ?, ?, ?)",customer.getCustomerId(),customer.getName(),customer.getPhoneNumber(),customer.getAddress(),customer.getEmail(),customer.getStatus());
    }
    @Override
    public  int getCustomerCount() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute( "select count(*) from customer ");

        if(resultSet.next()) {
            return resultSet.getInt(1);
        }
        return 0;
    }


    @Override
    public  ArrayList<Customer> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Customer> allCustomer =new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM customer");
        while (rst.next()) {
            String id = rst.getString(1);
            String name = rst.getString(2);
            String phoneNumber = rst.getString(3);
            String address = rst.getString(4);
            String email = rst.getString(5);
            String status = rst.getString(6);
            Customer customer = new Customer(id,name,phoneNumber,address,email,status);
            allCustomer.add(customer);
        }
        return allCustomer;
    }
    @Override
    public  Customer search(String id) throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT * FROM customer WHERE customerId = ?",id);

        if (resultSet.next()) {
            String customerId = resultSet.getString(1);
            String customerName = resultSet.getString(2);
            String phoneNumber = resultSet.getString(3);
            String address = resultSet.getString(4);
            String email = resultSet.getString(5);
            String status = resultSet.getString(6);

            Customer customer = new Customer(customerId, customerName, phoneNumber, address, email,status);

            return customer;
        }

        return null;
    }
    @Override
    public  boolean delete(String id) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("DELETE FROM customer WHERE customerId = ?",id);
    }
    @Override
    public  boolean update(Customer customer) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("UPDATE customer SET customerName = ?,  phoneNumber = ?,address = ?, email = ? , status = ? WHERE customerId = ?"
                ,customer.getName(),customer.getPhoneNumber(),customer.getAddress(),customer.getEmail(),customer.getStatus(),customer.getCustomerId());


    }
    @Override
    public  List<String> getCustomerId() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT customerId FROM customer");

        List<String> idList = new ArrayList<>();
        while (resultSet.next()) {
            idList.add(resultSet.getString(1));
        }
        return idList;
    }
    @Override
    public List<String> getCustomerId(String id) throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT customerId FROM customer WHERE customerId = ?",id);
        List<String> idList = new ArrayList<>();
        while (resultSet.next()) {
            idList.add(resultSet.getString(1));
        }
        return idList;
    }
    @Override
    public  List<String> getStatus() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT status FROM customer");

        List<String> idList = new ArrayList<>();
        while (resultSet.next()) {
            idList.add(resultSet.getString(1));
        }
        return idList;

    }
    @Override
    public List<String> getStatus(String status) throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT status FROM customer WHERE status = ?",status);

        List<String> idList = new ArrayList<>();
        while (resultSet.next()) {
            idList.add(resultSet.getString(1));
        }
        return idList;
    }

}
