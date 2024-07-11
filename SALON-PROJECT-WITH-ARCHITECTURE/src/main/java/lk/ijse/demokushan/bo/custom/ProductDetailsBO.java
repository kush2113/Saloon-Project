package lk.ijse.demokushan.bo.custom;

import lk.ijse.demokushan.bo.SuperBO;
import lk.ijse.demokushan.entity.Product_detail;

import java.sql.SQLException;

public interface ProductDetailsBO extends SuperBO {
    boolean updatePrductDetailsTable(String supplierId, String productName, int qty) throws SQLException;

    String getProductId(String sId, String pName, int qty) throws SQLException, ClassNotFoundException;

    boolean setAssociate(Product_detail productDetail) throws SQLException, ClassNotFoundException;
}
