package lk.ijse.demokushan.dao.custom.impl;

import lk.ijse.demokushan.dao.DAOFactory;
import lk.ijse.demokushan.dao.SQLUtil;
import lk.ijse.demokushan.dao.custom.AppointmentDAO;
import lk.ijse.demokushan.dao.custom.FeedbackDAO;
import lk.ijse.demokushan.dao.custom.PaymentDAO;
import lk.ijse.demokushan.dao.custom.ProductDAO;
import lk.ijse.demokushan.db.DbConnection;
import lk.ijse.demokushan.entity.Appointment;
import lk.ijse.demokushan.entity.Customer;
import lk.ijse.demokushan.entity.Feedback;
import lk.ijse.demokushan.entity.Payment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDAOImpl implements AppointmentDAO{

    @Override
    public boolean updateAppointmentStatus(String id) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("UPDATE appointment SET status = 'Complete' WHERE appointmentId = ?", id);

    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT appointmentId FROM appointment ORDER BY appointmentId DESC LIMIT 1");
        if (resultSet.next()) {
            String appointmentId = resultSet.getString(1);
            return appointmentId;
        }
        return null;
    }

    @Override
    public List<String> getAppointmentId() throws SQLException, ClassNotFoundException {


        ResultSet resultSet = SQLUtil.execute("SELECT appointmentId FROM appointment");

        List<String> idList = new ArrayList<>();
        while (resultSet.next()) {
            idList.add(resultSet.getString(1));
        }
        return idList;
    }

    @Override
    public boolean add(Appointment appointment) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("INSERT INTO appointment VALUES(?, ?, ?, ?, ?, ?, ?)", appointment.getAppointmentId(), appointment.getTime(), appointment.getDate(), appointment.getEmployeeId(), appointment.getCustomerId(), appointment.getHairCutId(), appointment.getStatus());

    }

    @Override
    public int getCompleteAppointmentCount() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT COUNT(*) FROM appointment WHERE status = 'Complete'");

        if (resultSet.next()) {
            return resultSet.getInt(1);
        }

        return 0;
    }

    @Override
    public int getIncompleteAppointmentCount() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT COUNT(*) FROM appointment WHERE status = 'Incomplete'");

        if (resultSet.next()) {
            return resultSet.getInt(1);
        }
        return 0;
    }

    @Override
    public  ArrayList<Appointment> getAll() throws SQLException, ClassNotFoundException {

        ArrayList<Appointment> allAppointment =new ArrayList<>();
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM appointment");
        while (resultSet.next()) {
            String appointmentId = resultSet.getString(1);
            String time = resultSet.getString(2);
            String date = resultSet.getString(3);
            String employeeId = resultSet.getString(4);
            String customerId = resultSet.getString(5);
            String hirCutId = resultSet.getString(6);
            String status = resultSet.getString(7);


            Appointment appointment = new Appointment(appointmentId, time, date, employeeId, customerId, hirCutId, status);
            allAppointment.add(appointment);
        }
        return allAppointment;
    }

    @Override
    public  boolean delete(String id) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("DELETE FROM appointment WHERE appointmentId = ?",id);
    }

    @Override
    public  List<String> getAppointmentId(String appointmentId) throws SQLException, ClassNotFoundException {

        ResultSet resultSet =SQLUtil.execute("SELECT appointmentId FROM appointment WHERE appointmentId = ?",appointmentId);

        List<String> idList = new ArrayList<>();
        while (resultSet.next()) {
            idList.add(resultSet.getString(1));
        }
        return idList;
    }

    @Override
    public  Appointment search(String id) throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT * FROM appointment WHERE appointmentId = ?",id);
        if (resultSet.next()) {
            String appoinmentId = resultSet.getString(1);
            String time = resultSet.getString(2);
            String date = resultSet.getString(3);
            String employeeId = resultSet.getString(4);
            String customerId = resultSet.getString(5);

            String style = getHairCutStyle(resultSet.getString(6));

            String status = resultSet.getString(7);

            Appointment appointment = new Appointment(appoinmentId, time, date, employeeId, customerId, style, status);

            return appointment;
        }

        return null;
    }

    @Override
    public  String getHairCutStyle(String id) throws SQLException, ClassNotFoundException {

        ResultSet resultSet =SQLUtil.execute("SELECT hairCutStyle FROM hairCut WHERE hairCutId = ?",id);

        if (resultSet.next()) {
            String hairStye = resultSet.getString(1);
            return hairStye;
        }
        return null;
    }


    @Override
    public List<String> getStatus() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT status FROM appointment");

        List<String> idList = new ArrayList<>();
        while (resultSet.next()) {
            idList.add(resultSet.getString(1));
        }
        return idList;
    }

    @Override
    public List<String> getStatus(String status) throws SQLException, ClassNotFoundException {


        ResultSet resultSet = SQLUtil.execute("SELECT status FROM appointment WHERE status = ?",status);

        List<String> idList = new ArrayList<>();
        while (resultSet.next()) {
            idList.add(resultSet.getString(1));
        }
        return idList;
    }

    @Override
    public boolean update(Appointment appointment) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("UPDATE appointment SET time = ?, date = ?, employeeId = ?, customerId =?,  status = ? WHERE appointmentId = ?",appointment.getTime(),appointment.getDate(),appointment.getEmployeeId(),appointment.getCustomerId(),appointment.getStatus(),appointment.getAppointmentId());
    }
}
