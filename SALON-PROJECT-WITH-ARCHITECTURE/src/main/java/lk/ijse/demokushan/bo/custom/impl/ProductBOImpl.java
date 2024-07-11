package lk.ijse.demokushan.bo.custom.impl;

import lk.ijse.demokushan.bo.custom.ProductBO;
import lk.ijse.demokushan.dao.DAOFactory;
import lk.ijse.demokushan.dao.custom.HairCutDAO;
import lk.ijse.demokushan.dao.custom.ProductDAO;
import lk.ijse.demokushan.dto.ProductDTO;
import lk.ijse.demokushan.entity.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductBOImpl implements ProductBO {


    ProductDAO productDAO = (ProductDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PRODUCT);
    HairCutDAO hairCutDAO = (HairCutDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.HAIRCUT);
    @Override
    public boolean productQtyUpdate(String hairCutId) throws SQLException, ClassNotFoundException {

        return productDAO.productQtyUpdate(hairCutId);
    }



    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {

        return productDAO.generateNewID();
    }

    @Override
    public boolean add(ProductDTO product) throws SQLException, ClassNotFoundException {

        return productDAO.add(new Product(product.getProductId(), product.getName(), product.getUnitPrice(), product.getQtyOnHand()));
    }

    @Override
    public ArrayList<Product> getAll() throws SQLException, ClassNotFoundException {

        return productDAO.getAll();
    }


    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {

        return productDAO.delete(id);
    }

    @Override
    public boolean update(ProductDTO product) throws SQLException, ClassNotFoundException {

        return productDAO.update(new Product(product.getProductId(), product.getName(), product.getUnitPrice(), product.getQtyOnHand()));
    }


    @Override
    public Product search(String id) throws SQLException, ClassNotFoundException {

        return productDAO.search(id);
    }

    @Override
    public ResultSet getProductId(String name) throws SQLException, ClassNotFoundException {

        return productDAO.getProductId(name);


    }
}