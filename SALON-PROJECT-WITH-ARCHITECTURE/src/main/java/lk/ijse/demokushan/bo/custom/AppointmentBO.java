package lk.ijse.demokushan.bo.custom;

import lk.ijse.demokushan.bo.SuperBO;
import lk.ijse.demokushan.dto.AppointmentDTO;
import lk.ijse.demokushan.dto.FeedbackDTO;
import lk.ijse.demokushan.dto.PaymentDTO;
import lk.ijse.demokushan.entity.Appointment;
import lk.ijse.demokushan.entity.Feedback;
import lk.ijse.demokushan.entity.Payment;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface AppointmentBO extends SuperBO {


    boolean completeAppointment(PaymentDTO payment, FeedbackDTO feedback, String hairCutId) throws SQLException;

    boolean updateAppointmentStatus(String id) throws SQLException, ClassNotFoundException;

    String  generateNewID() throws SQLException, ClassNotFoundException;

    List<String> getAppointmentId() throws SQLException, ClassNotFoundException;


    boolean add(AppointmentDTO appointment) throws SQLException, ClassNotFoundException;

    int getCompleteAppointmentCount() throws SQLException, ClassNotFoundException;

    int getIncompleteAppointmentCount() throws SQLException, ClassNotFoundException;

    ArrayList<Appointment> getAll() throws SQLException, ClassNotFoundException;

    boolean delete(String id) throws SQLException, ClassNotFoundException;

    List<String> getAppointmentId(String appointmentId) throws SQLException, ClassNotFoundException;


    Appointment search(String id) throws SQLException, ClassNotFoundException;

    String getHairCutStyle(String id) throws SQLException, ClassNotFoundException;

    List<String> getStatus() throws SQLException, ClassNotFoundException;

    List<String> getStatus(String status) throws SQLException, ClassNotFoundException;


    boolean update(AppointmentDTO appointment) throws SQLException, ClassNotFoundException;
}
