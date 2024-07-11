package lk.ijse.demokushan.dao.custom;

import lk.ijse.demokushan.dao.SQLUtil;
import lk.ijse.demokushan.dao.SuperDAO;
import lk.ijse.demokushan.view.tdm.MostAppointmentTM;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface QueryDAO extends SuperDAO {


    boolean productQtyUpdate(String hairCutId) throws SQLException, ClassNotFoundException;

    List<MostAppointmentTM> getMostSellItem() throws SQLException, ClassNotFoundException;
}
