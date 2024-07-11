package lk.ijse.demokushan.dao.custom.impl;

import lk.ijse.demokushan.dao.DAOFactory;
import lk.ijse.demokushan.dao.SQLUtil;
import lk.ijse.demokushan.dao.custom.CustomerDAO;
import lk.ijse.demokushan.dao.custom.ProductDAO;
import lk.ijse.demokushan.dao.custom.QueryDAO;
import lk.ijse.demokushan.entity.Product;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductDAOImpl implements ProductDAO {

    QueryDAO queryDAO = (QueryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.QUERY);


    @Override
    public  boolean productQtyUpdate(String hairCutId) throws SQLException, ClassNotFoundException {
        return queryDAO.productQtyUpdate(hairCutId);
    }


    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT productId FROM product ORDER BY productId DESC LIMIT 1");
        if(resultSet.next()) {
            String productId = resultSet.getString(1);
            return productId;
        }
        return null;
    }

    @Override
    public boolean add(Product product) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("INSERT INTO product VALUES(?, ?, ?, ?)",product.getProductId(),product.getName(),product.getUnitPrice(),product.getQtyOnHand());
    }

    @Override
    public ArrayList<Product> getAll() throws SQLException, ClassNotFoundException {

        ArrayList<Product> allProduct =new ArrayList<>();
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM product");

        while (resultSet.next()) {
            String pro_id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String unitPrice = resultSet.getString(3);
            String qtyOnHand = resultSet.getString(4);




            Product product = new Product(pro_id, name, unitPrice ,qtyOnHand);
            allProduct.add(product);
        }
        return allProduct;
    }


    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("DELETE FROM product WHERE productId = ?",id);
    }

    @Override
    public boolean update(Product product) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE product SET productName = ?, unitPrice = ?, qtyOnHand = ? WHERE productId = ?",product.getName(),product.getUnitPrice(),product.getQtyOnHand(),product.getProductId());
    }

    @Override
    public Product search(String id) throws SQLException, ClassNotFoundException {

        ResultSet resultSet =SQLUtil.execute("SELECT * FROM product WHERE productId = ?",id);

        if (resultSet.next()) {
            String productId = resultSet.getString(1);
            String productName = resultSet.getString(2);
            String unitPrice = resultSet.getString(3);
            String qtyOnHand = resultSet.getString(4);

            Product product = new Product(productId, productName, unitPrice, qtyOnHand);

            return product;
        }
        return null;
    }
    @Override
    public ResultSet getProductId(String name) throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT productId FROM product WHERE productName = ?",name);

        return resultSet;

    }
}
