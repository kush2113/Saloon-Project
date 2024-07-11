package lk.ijse.demokushan.bo.custom.impl;

import lk.ijse.demokushan.bo.custom.HairCutBO;
import lk.ijse.demokushan.dao.DAOFactory;
import lk.ijse.demokushan.dao.custom.HairCutDAO;
import lk.ijse.demokushan.dao.custom.ProductDAO;
import lk.ijse.demokushan.db.DbConnection;
import lk.ijse.demokushan.dto.HairCutDTO;
import lk.ijse.demokushan.entity.AddNewHairCut;
import lk.ijse.demokushan.entity.HairCut;
import lk.ijse.demokushan.entity.HairCutDetails;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HairCutBOImpl implements HairCutBO {


    HairCutDAO hairCutDAO = (HairCutDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.HAIRCUT);
    ProductDAO productDAO = (ProductDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PRODUCT);
    @Override
    public String getHairCutPrice(String style) throws SQLException, ClassNotFoundException {

        return hairCutDAO.getHairCutPrice(style);
    }

    @Override
    public boolean placeHairCut(AddNewHairCut po) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        try {
            boolean isHairCutUpdate = addNewHairCut(po.getHairCut());
            System.out.println(isHairCutUpdate);
            System.out.println("isHairCutUpdate");
            if (isHairCutUpdate) {
                System.out.println("isHairCutDetailsUpdate");
                boolean isHairCutDetailsUpdate = saveHairCutDetailsTable(po.getHdList());
                System.out.println("isHairCutDetailsUpdate");
                if (isHairCutDetailsUpdate) {
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
    public boolean saveHairCutDetailsTable(List<HairCutDetails> hairCutTableDetails) throws SQLException {
        String sql = "INSERT INTO hairCutDetails VALUES (?, ?, ?)";


        try (PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql)) {
            for (HairCutDetails details : hairCutTableDetails) {
                pstm.setString(1, details.getProductId());
                pstm.setString(2, details.getHairCutId());
                pstm.setInt(3, details.getProductQty());
                pstm.addBatch();

            }
            int[] updateCounts = pstm.executeBatch();
            return Arrays.stream(updateCounts).allMatch(count -> count > 0);
        }
    }

    @Override
    public boolean addNewHairCut(HairCut hairCutTableDetails) throws SQLException {

        return hairCutDAO.addNewHairCut(new HairCut(hairCutTableDetails.getHairCutId(),hairCutTableDetails.getStyle(),hairCutTableDetails.getPrice()));

    }

    @Override
    public  String generateNewID() throws SQLException, ClassNotFoundException {

        return hairCutDAO.generateNewID();
    }

    @Override
    public  List<String> getProductDetails(String name) throws SQLException, ClassNotFoundException {

        return hairCutDAO.getProductDetails(name);
    }

    @Override
    public List<String> getProductNames() throws SQLException, ClassNotFoundException { //appointment

        return hairCutDAO.getProductNames();
    }


    @Override
    public  List<String> getHairCutNames() throws SQLException, ClassNotFoundException { //appointment

        return hairCutDAO.getHairCutNames();
    }

    @Override
    public String getHairCutId(String style) throws SQLException, ClassNotFoundException {
        return hairCutDAO.getHairCutId(style);
    }


    @Override
    public boolean add(HairCut hairCut) throws SQLException, ClassNotFoundException {

        return hairCutDAO.add(new HairCut(hairCut.getHairCutId(),hairCut.getStyle(),hairCut.getPrice()));
    }

    @Override
    public  ArrayList<HairCut> getAll() throws SQLException, ClassNotFoundException {

        return hairCutDAO.getAll();
    }


    @Override
    public boolean update(HairCutDTO hairCut) throws SQLException, ClassNotFoundException {
        return hairCutDAO.update(new HairCut(hairCut.getHairCutId(), hairCut.getStyle(), hairCut.getPrice()));
    }


    @Override
    public  boolean delete(String id) throws SQLException, ClassNotFoundException {

        return hairCutDAO.delete(id);
    }

    @Override
    public HairCut search(String id) throws SQLException, ClassNotFoundException {

        return hairCutDAO.search(id);
    }

    @Override
    public ResultSet getProductId(String name) throws SQLException, ClassNotFoundException {
        return productDAO.getProductId(name);
    }

}
