package lk.ijse.demokushan.dao.custom;

import lk.ijse.demokushan.dao.CrudDAO;
import lk.ijse.demokushan.entity.AddNewHairCut;
import lk.ijse.demokushan.entity.HairCut;
import lk.ijse.demokushan.entity.HairCutDetails;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface HairCutDAO extends CrudDAO<HairCut> {


    String getHairCutPrice(String style) throws SQLException, ClassNotFoundException;

    boolean saveHairCutDetailsTable(List<HairCutDetails> hairCutTableDetails) throws SQLException;

    boolean addNewHairCut(HairCut hairCutTableDetails) throws SQLException;

    String generateNewID() throws SQLException, ClassNotFoundException;

    List<String> getProductDetails(String name) throws SQLException, ClassNotFoundException;

    List<String> getProductNames() throws SQLException, ClassNotFoundException;

    List<String> getHairCutNames() throws SQLException, ClassNotFoundException;

    String getHairCutId(String id) throws SQLException, ClassNotFoundException;

    ResultSet getProductId(String name) throws SQLException, ClassNotFoundException;


//    ResultSet getProductId(String name) throws SQLException, ClassNotFoundException;
}
