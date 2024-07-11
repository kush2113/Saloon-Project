package lk.ijse.demokushan.bo.custom.impl;

import lk.ijse.demokushan.bo.custom.SupplierBO;
import lk.ijse.demokushan.dao.DAOFactory;
import lk.ijse.demokushan.dao.custom.SupplierDAO;
import lk.ijse.demokushan.dto.SupplierDTO;
import lk.ijse.demokushan.entity.Supplier;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierBOImpl implements SupplierBO {

    SupplierDAO supplierDAO =(SupplierDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SUPPLIER);

    @Override
    public int getSupplierCount() throws SQLException, ClassNotFoundException {

        return supplierDAO.getSupplierCount();
    }

    @Override
    public List<String> getSupplierId() throws SQLException, ClassNotFoundException {

        return supplierDAO.getSupplierId();
    }

    @Override
    public String  generateNewID() throws SQLException, ClassNotFoundException {

        return supplierDAO.generateNewID();
    }

    @Override
    public boolean add(SupplierDTO supplier) throws SQLException, ClassNotFoundException {

        return supplierDAO.add(new Supplier(supplier.getSupplierId(),supplier.getName(),supplier.getNic(),supplier.getPhoneNumber()));
    }

    @Override
    public  ArrayList<Supplier> getAll() throws SQLException, ClassNotFoundException {

        return supplierDAO.getAll();
    }

    @Override
    public boolean delete(String nic) throws SQLException, ClassNotFoundException {

        return supplierDAO.delete(nic);
    }


    @Override
    public Supplier search(String nic) throws SQLException, ClassNotFoundException {

        return supplierDAO.search(nic);
    }


    @Override
    public List<String> getNames() throws SQLException, ClassNotFoundException {

        return supplierDAO.getNames();
    }

    @Override
    public  boolean update(SupplierDTO supplier) throws SQLException, ClassNotFoundException {

        return supplierDAO.update(new Supplier(supplier.getSupplierId(),supplier.getName(),supplier.getNic(),supplier.getPhoneNumber()));

    }
}
