package lk.ijse.demokushan.bo.custom.impl;

import lk.ijse.demokushan.bo.custom.ProductDetailsBO;
import lk.ijse.demokushan.dao.DAOFactory;
import lk.ijse.demokushan.dao.custom.ProductDetailsDAO;
import lk.ijse.demokushan.db.DbConnection;
import lk.ijse.demokushan.entity.Product_detail;

import java.sql.Connection;
import java.sql.SQLException;

public class ProductDetailsBOImpl implements ProductDetailsBO {


    ProductDetailsDAO productDetailsDAO = (ProductDetailsDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PRODUCTDETAILS);


    @Override
    public boolean updatePrductDetailsTable(String supplierId, String productName, int qty) throws SQLException{
        Connection connection = DbConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        try {
            boolean isProductDetailsTable = productDetailsDAO.addProductDetails(supplierId, productName, qty);
            System.out.println(isProductDetailsTable);
            System.out.println("isProductDetailsTable");

            if (isProductDetailsTable) {
                System.out.println("isHairCutDetailsUpdate");
                boolean isUpdateProductQtyOnHand = productDetailsDAO.updateProductQtyOnHand(productName, qty);
                System.out.println("isHairCutDetailsUpdate");
                System.out.println(isUpdateProductQtyOnHand);
                if (isUpdateProductQtyOnHand) {
                    System.out.println("commit");
                    connection.commit();
                    return true;
                }
            }
            connection.rollback();
            return false;
        } catch (Exception e) {
            connection.rollback();
            return false;
        } finally {
            connection.setAutoCommit(true);
        }
    }


    @Override
    public String getProductId(String sId, String pName, int qty) throws SQLException, ClassNotFoundException {
        return productDetailsDAO.getProductId(sId, pName, qty);
    }

    @Override
    public boolean setAssociate(Product_detail productDetail) throws SQLException, ClassNotFoundException {
        return productDetailsDAO.setAssociate(productDetail);
    }
}