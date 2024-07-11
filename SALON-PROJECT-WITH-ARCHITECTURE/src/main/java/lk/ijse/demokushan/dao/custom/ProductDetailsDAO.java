package lk.ijse.demokushan.dao.custom;

import lk.ijse.demokushan.dao.CrudDAO;
import lk.ijse.demokushan.dao.SuperDAO;
import lk.ijse.demokushan.entity.Product_detail;

import java.sql.SQLException;

public interface ProductDetailsDAO extends SuperDAO {
    boolean updateProductQtyOnHand(String pName, int qty) throws SQLException, ClassNotFoundException;

    boolean addProductDetails(String sId, String pName, int qty) throws SQLException, ClassNotFoundException;

    String getProductId(String sId, String pName, int qty) throws SQLException, ClassNotFoundException;

    boolean setAssociate(Product_detail productDetail) throws SQLException, ClassNotFoundException;
}
