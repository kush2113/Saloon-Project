package lk.ijse.demokushan.bo.custom;

import lk.ijse.demokushan.bo.SuperBO;
import lk.ijse.demokushan.dto.HairCutDTO;
import lk.ijse.demokushan.entity.AddNewHairCut;
import lk.ijse.demokushan.entity.HairCut;
import lk.ijse.demokushan.entity.HairCutDetails;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface HairCutBO extends SuperBO {


    String getHairCutPrice(String style) throws SQLException, ClassNotFoundException;

    boolean placeHairCut(AddNewHairCut po) throws SQLException;

    boolean saveHairCutDetailsTable(List<HairCutDetails> hairCutTableDetails) throws SQLException;

    boolean addNewHairCut(HairCut hairCutTableDetails) throws SQLException;

    String generateNewID() throws SQLException, ClassNotFoundException;

    List<String> getProductDetails(String name) throws SQLException, ClassNotFoundException;

    List<String> getProductNames() throws SQLException, ClassNotFoundException;

    List<String> getHairCutNames() throws SQLException, ClassNotFoundException;

    String getHairCutId(String style) throws SQLException, ClassNotFoundException;

    boolean add(HairCut hairCut) throws SQLException, ClassNotFoundException;

    ArrayList<HairCut> getAll() throws SQLException, ClassNotFoundException;

    boolean update(HairCutDTO hairCut) throws SQLException, ClassNotFoundException;

    boolean delete(String id) throws SQLException, ClassNotFoundException;

    HairCut search(String id) throws SQLException, ClassNotFoundException;

    ResultSet getProductId(String name) throws SQLException, ClassNotFoundException;
}
