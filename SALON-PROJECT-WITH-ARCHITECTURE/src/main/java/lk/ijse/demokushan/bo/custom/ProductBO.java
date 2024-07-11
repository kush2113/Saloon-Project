package lk.ijse.demokushan.bo.custom;

import lk.ijse.demokushan.bo.SuperBO;
import lk.ijse.demokushan.dto.ProductDTO;
import lk.ijse.demokushan.entity.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface ProductBO extends SuperBO {
    boolean productQtyUpdate(String hairCutId) throws SQLException, ClassNotFoundException;

    String generateNewID() throws SQLException, ClassNotFoundException;

    boolean add(ProductDTO product) throws SQLException, ClassNotFoundException;

    ArrayList<Product> getAll() throws SQLException, ClassNotFoundException;

    boolean delete(String id) throws SQLException, ClassNotFoundException;

    boolean update(ProductDTO product) throws SQLException, ClassNotFoundException;

    Product search(String id) throws SQLException, ClassNotFoundException;

    ResultSet getProductId(String name) throws SQLException, ClassNotFoundException;
}
