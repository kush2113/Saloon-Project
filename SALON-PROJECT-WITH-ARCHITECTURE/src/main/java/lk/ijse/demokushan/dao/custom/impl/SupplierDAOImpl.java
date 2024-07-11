package lk.ijse.demokushan.dao.custom.impl;


import lk.ijse.demokushan.dao.SQLUtil;
import lk.ijse.demokushan.dao.custom.SupplierDAO;
import lk.ijse.demokushan.entity.Supplier;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierDAOImpl implements SupplierDAO {


    @Override
    public  int getSupplierCount() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("select count(*) from supplier ");

        if(resultSet.next()) {
            return resultSet.getInt(1);
        }
        return 0;
    }

    @Override
    public List<String> getSupplierId() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT supplierID FROM supplier");
        List<String> idList = new ArrayList<>();
        while (resultSet.next()) {
            idList.add(resultSet.getString(1));
        }
        return idList;
    }

    @Override
    public  String generateNewID() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT supplierId FROM supplier ORDER BY supplierId DESC LIMIT 1");
        if(resultSet.next()) {
            String supplierId = resultSet.getString(1);
            return supplierId;
        }
        return null;
    }

    @Override
    public  boolean add(Supplier supplier) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("INSERT INTO supplier VALUES(?, ?, ?, ?)",supplier.getSupplierId(),supplier.getName(),supplier.getNic(),supplier.getPhoneNumber());
    }

    @Override
    public  ArrayList<Supplier> getAll() throws SQLException, ClassNotFoundException {

        ArrayList<Supplier> allSupplier = new ArrayList<>();
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM supplier");

        while (resultSet.next()) {
            String sId = resultSet.getString(1);
            String name = resultSet.getString(2);
            String nic = resultSet.getString(3);
            String phoneNumber = resultSet.getString(4);


            Supplier supplier = new Supplier(sId, name, nic, phoneNumber);
            allSupplier.add(supplier);
        }
        return allSupplier;
    }

    @Override
    public  boolean delete(String nic) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("DELETE FROM supplier WHERE NIC = ?",nic);
    }

    @Override
    public  Supplier search(String nic) throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT * FROM supplier WHERE NIC = ?",nic);
        if (resultSet.next()) {
            String sup_id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String nic1 = resultSet.getString(3);
            String phoneNumber = resultSet.getString(4);

            Supplier supplier = new Supplier(sup_id, name, nic1, phoneNumber);

            return supplier;
        }

        return null;
    }

    @Override
    public  List<String> getNames() throws SQLException, ClassNotFoundException {

        ResultSet resultSet =SQLUtil.execute("SELECT s_name FROM supplier");
        List<String> nameList = new ArrayList<>();

        while (resultSet.next()) {
            nameList.add(resultSet.getString(1));
        }
        return nameList;
    }

    @Override
    public boolean update(Supplier supplier) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("UPDATE supplier SET supplierName = ?, NIC = ?, phoneNumber = ?  WHERE supplierId = ?",supplier.getName(),supplier.getNic(),supplier.getPhoneNumber(),supplier.getSupplierId());

    }


}
