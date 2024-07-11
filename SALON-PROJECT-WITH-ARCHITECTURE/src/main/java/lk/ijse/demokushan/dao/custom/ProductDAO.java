package lk.ijse.demokushan.dao.custom;

import lk.ijse.demokushan.dao.CrudDAO;
import lk.ijse.demokushan.entity.Product;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ProductDAO extends CrudDAO<Product> {
    boolean productQtyUpdate(String hairCutId) throws SQLException, ClassNotFoundException;

    ResultSet getProductId(String name) throws SQLException, ClassNotFoundException;
}
