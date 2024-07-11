package lk.ijse.demokushan.dao.custom.impl;

import lk.ijse.demokushan.dao.SQLUtil;
import lk.ijse.demokushan.dao.custom.QueryDAO;
import lk.ijse.demokushan.view.tdm.MostAppointmentTM;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QueryDAOImpl implements QueryDAO {
    @Override
    public  boolean productQtyUpdate(String hairCutId) throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT p.productId, hd.productQty FROM product p JOIN hairCutDetails hd ON p.productId = hd.productId WHERE hd.hairCutId = ?",hairCutId);
        boolean updated = false;
        while (resultSet.next()) {
            String productId = resultSet.getString("productId");
            int productQty = resultSet.getInt("productQty");

            updated = SQLUtil.execute("UPDATE product SET qtyOnHand = qtyOnHand - ? WHERE productId = ?",productQty,productId);
            // Placeholder for actual update logic
        }
        return updated;
    }

    @Override
    public List<MostAppointmentTM> getMostSellItem() throws SQLException, ClassNotFoundException {
        List<MostAppointmentTM> sellItems = new ArrayList<>();

        ResultSet resultSet = SQLUtil.execute("SELECT c.customerId, c.customerName, COUNT(a.appointmentId) AS visitCount " +
                "FROM customer c LEFT JOIN appointment a ON c.customerId = a.customerId " +
                "GROUP BY c.customerId, c.customerName");
        try {

            while (resultSet.next()) {
                String id = resultSet.getString("customerId");
                String name = resultSet.getString("customerName");
                int visitCount = resultSet.getInt("visitCount");

                MostAppointmentTM mostAppointmentTM = new MostAppointmentTM(id, name, visitCount);
                sellItems.add(mostAppointmentTM);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately, e.g., log it
        }

        return sellItems;
    }

}
