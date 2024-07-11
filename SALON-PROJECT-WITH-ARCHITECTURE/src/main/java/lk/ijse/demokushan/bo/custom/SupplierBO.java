package lk.ijse.demokushan.bo.custom;

import lk.ijse.demokushan.bo.SuperBO;
import lk.ijse.demokushan.dto.SupplierDTO;
import lk.ijse.demokushan.entity.Supplier;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface SupplierBO extends SuperBO {
    int getSupplierCount() throws SQLException, ClassNotFoundException;

    List<String> getSupplierId() throws SQLException, ClassNotFoundException;

    String  generateNewID() throws SQLException, ClassNotFoundException;

    boolean add(SupplierDTO supplier) throws SQLException, ClassNotFoundException;

    ArrayList<Supplier> getAll() throws SQLException, ClassNotFoundException;

    boolean delete(String nic) throws SQLException, ClassNotFoundException;

    Supplier search(String nic) throws SQLException, ClassNotFoundException;

    List<String> getNames() throws SQLException, ClassNotFoundException;

    boolean update(SupplierDTO supplier) throws SQLException, ClassNotFoundException;
}
