package lk.ijse.demokushan.dao.custom.impl;

import lk.ijse.demokushan.dao.SQLUtil;
import lk.ijse.demokushan.dao.custom.HairCutDAO;
import lk.ijse.demokushan.db.DbConnection;
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

public class HairCutDAOImpl implements HairCutDAO {


    @Override
    public String getHairCutPrice(String style) throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT price FROM hairCut WHERE hairCutStyle = ?",style);
        if(resultSet.next()) {
            String price = resultSet.getString(1);
            return price;
        }
        return null;
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
        String sql = "INSERT INTO hairCut VALUES  (?, ? ,?)";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setString(1, hairCutTableDetails.getHairCutId());
        pstm.setString(2, hairCutTableDetails.getStyle());
        pstm.setDouble(3, hairCutTableDetails.getPrice());

        return pstm.executeUpdate() > 0;

    }


    @Override
    public  String generateNewID() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT hairCutId FROM hairCut ORDER BY hairCutId DESC LIMIT 1");
        if(resultSet.next()) {
            String hairCutId = resultSet.getString(1);
            return hairCutId;
        }
        return null;
    }


    @Override
    public  List<String> getProductDetails(String name) throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT productId, unitPrice, qtyOnHand FROM product WHERE productName = ?",name);
        List<String> detailList = new ArrayList<>();
        if (resultSet.next()) {
            detailList.add(resultSet.getString(1));
            detailList.add(resultSet.getString(2));
            detailList.add(resultSet.getString(3));
        }
        return detailList;
    }


    @Override
    public List<String> getProductNames() throws SQLException, ClassNotFoundException { //appointment

        ResultSet resultSet = SQLUtil.execute("SELECT productName FROM product");

        List<String> nameList = new ArrayList<>();
        while (resultSet.next()) {
            nameList.add(resultSet.getString(1));
        }
        return nameList;
    }


    @Override
    public  List<String> getHairCutNames() throws SQLException, ClassNotFoundException { //appointment

        ResultSet resultSet = SQLUtil.execute("SELECT hairCutStyle FROM hairCut");

        List<String> nameList = new ArrayList<>();
        while (resultSet.next()) {
            nameList.add(resultSet.getString(1));
        }
        return nameList;
    }


    @Override
    public String getHairCutId(String style) throws SQLException, ClassNotFoundException {

       ResultSet resultSet = SQLUtil.execute("SELECT hairCutId FROM hairCut WHERE hairCutStyle = ?",style);

       resultSet.next();
       return resultSet.getString("hairCutId");
    }


    @Override
    public boolean add(HairCut hairCut) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("INSERT INTO hairCut VALUES(?, ?, ?)",hairCut.getHairCutId(),hairCut.getStyle(),hairCut.getPrice());
    }

    @Override
    public  ArrayList<HairCut> getAll() throws SQLException, ClassNotFoundException {

        ArrayList<HairCut> allHairCut = new ArrayList<>();
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM hairCut");


        while (resultSet.next()) {
            String hc_Id = resultSet.getString(1);
            String style = resultSet.getString(2);
            double price = Double.parseDouble(resultSet.getString(3));

            HairCut hairCut = new HairCut(hc_Id, style, price);
            allHairCut.add(hairCut);
        }
        return allHairCut;
    }


    @Override
    public boolean update(HairCut hairCut) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE haircut SET  hairCutStyle = ?, price = ? WHERE hairCutId  = ?",hairCut.getStyle(),hairCut.getPrice(),hairCut.getHairCutId());
    }


    @Override
    public  boolean delete(String id) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("DELETE FROM haircut WHERE hairCutId = ?",id);
    }

    @Override
    public HairCut search(String id) throws SQLException, ClassNotFoundException {

        ResultSet resultSet =SQLUtil.execute("SELECT * FROM hairCut WHERE hairCutId = ?",id);

        if (resultSet.next()) {
            String hc_id = resultSet.getString(1);
            String hc_style = resultSet.getString(2);
            double price = Double.parseDouble(resultSet.getString(3));

            HairCut hairCut = new HairCut(hc_id, hc_style, price);

            return hairCut;
        }

        return null;
    }

    @Override
    public ResultSet getProductId(String name) throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT productId FROM product WHERE productName = ?",name);

        return resultSet;

    }
}
