package lk.ijse.demokushan.dao.custom;

import lk.ijse.demokushan.dao.CrudDAO;
import lk.ijse.demokushan.entity.Appointment;
import lk.ijse.demokushan.entity.Feedback;
import lk.ijse.demokushan.entity.Payment;

import java.sql.SQLException;
import java.util.List;

public interface AppointmentDAO extends CrudDAO<Appointment> {

    public boolean add(Appointment appointment) throws SQLException, ClassNotFoundException;

    boolean updateAppointmentStatus(String id) throws SQLException, ClassNotFoundException;

    public String generateNewID() throws SQLException, ClassNotFoundException;

    List<String> getAppointmentId() throws SQLException, ClassNotFoundException;
    int getCompleteAppointmentCount() throws SQLException, ClassNotFoundException;

    int getIncompleteAppointmentCount() throws SQLException, ClassNotFoundException;

    List<String> getAppointmentId(String appointmentId) throws SQLException, ClassNotFoundException;

    Appointment search(String id) throws SQLException, ClassNotFoundException;

    String getHairCutStyle(String id) throws SQLException, ClassNotFoundException;

    List<String> getStatus() throws SQLException, ClassNotFoundException;

    List<String> getStatus(String status) throws SQLException, ClassNotFoundException;
}
