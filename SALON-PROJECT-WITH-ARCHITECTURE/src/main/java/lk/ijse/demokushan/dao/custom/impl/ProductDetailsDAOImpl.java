package lk.ijse.demokushan.dao.custom.impl;

import lk.ijse.demokushan.dao.SQLUtil;
import lk.ijse.demokushan.dao.custom.ProductDetailsDAO;
import lk.ijse.demokushan.db.DbConnection;
import lk.ijse.demokushan.entity.Product_detail;

import java.sql.Connection;
import java.sql.SQLException;

public class ProductDetailsDAOImpl implements ProductDetailsDAO {


    @Override
    public boolean updateProductQtyOnHand(String pName, int qty) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE product SET qtyOnHand = qtyOnHand + ? WHERE productName = ?", qty, pName);
    }

    @Override
    public boolean addProductDetails(String sId, String pName, int qty) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO productDetail VALUES(?, ?, ?)",sId,getProductId(sId, pName, qty),qty);
    }

    @Override
    public String getProductId(String sId, String pName, int qty) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("SELECT productId from product WHERE productName = ?",pName);
    }

    @Override
    public boolean setAssociate(Product_detail productDetail) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("INSERT INTO product_detail VALUES(?, ?)",productDetail.getSname(),productDetail.getPname());
    }
}
