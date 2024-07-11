package lk.ijse.demokushan.dao.custom;

import lk.ijse.demokushan.dao.CrudDAO;
import lk.ijse.demokushan.entity.Supplier;

import java.sql.SQLException;
import java.util.List;

public interface SupplierDAO extends CrudDAO<Supplier> {


    int getSupplierCount() throws SQLException, ClassNotFoundException;

    List<String> getSupplierId() throws SQLException, ClassNotFoundException;

    boolean add(Supplier supplier) throws SQLException, ClassNotFoundException;

    List<String> getNames() throws SQLException, ClassNotFoundException;
}
