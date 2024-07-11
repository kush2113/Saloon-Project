package lk.ijse.demokushan.bo.custom.impl;

import lk.ijse.demokushan.bo.custom.CustomerBO;
import lk.ijse.demokushan.dao.DAOFactory;
import lk.ijse.demokushan.dao.custom.CustomerDAO;
import lk.ijse.demokushan.dto.CustomerDTO;
import lk.ijse.demokushan.entity.Customer;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerBOImpl implements CustomerBO {

    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    @Override
    public  String generateNewID() throws SQLException, ClassNotFoundException {

        return customerDAO.generateNewID();

    }

    @Override
    public  boolean add(CustomerDTO customer) throws SQLException, ClassNotFoundException {

        return customerDAO.add(new Customer(customer.getCustomerId(),customer.getName(),customer.getPhoneNumber(),customer.getAddress(),customer.getEmail(),customer.getStatus()));
    }
    @Override
    public  int getCustomerCount() throws SQLException, ClassNotFoundException {

        return customerDAO.getCustomerCount();
    }


    @Override
    public ArrayList<Customer> getAll() throws SQLException, ClassNotFoundException {

        return customerDAO.getAll();
    }
    @Override
    public  Customer search(String id) throws SQLException, ClassNotFoundException {

            return customerDAO.search(id);
    }
    @Override
    public  boolean delete(String id) throws SQLException, ClassNotFoundException {


        return customerDAO.delete(id);
    }
    @Override
    public  boolean update(CustomerDTO customer) throws SQLException, ClassNotFoundException {

        return customerDAO.update(new Customer(customer.getCustomerId(),customer.getName(),customer.getPhoneNumber(),customer.getAddress(),customer.getEmail(),customer.getStatus()));

    }
    @Override
    public List<String> getCustomerId() throws SQLException, ClassNotFoundException {

       return customerDAO.getCustomerId();
    }
    @Override
    public  List<String> getCustomerId(String id) throws SQLException, ClassNotFoundException {

        return customerDAO.getCustomerId(id);
    }
    @Override
    public  List<String> getStatus() throws SQLException, ClassNotFoundException {

        return customerDAO.getStatus();

    }
    @Override
    public  List<String> getStatus(String status) throws SQLException, ClassNotFoundException {

        return customerDAO.getStatus(status);
    }

}
