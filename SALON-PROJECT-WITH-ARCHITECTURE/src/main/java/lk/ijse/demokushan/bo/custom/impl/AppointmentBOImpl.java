package lk.ijse.demokushan.bo.custom.impl;

import lk.ijse.demokushan.bo.custom.AppointmentBO;
import lk.ijse.demokushan.bo.custom.PaymentBO;
import lk.ijse.demokushan.dao.DAOFactory;
import lk.ijse.demokushan.dao.custom.AppointmentDAO;
import lk.ijse.demokushan.dao.custom.FeedbackDAO;
import lk.ijse.demokushan.dao.custom.PaymentDAO;
import lk.ijse.demokushan.dao.custom.ProductDAO;
import lk.ijse.demokushan.db.DbConnection;
import lk.ijse.demokushan.dto.AppointmentDTO;
import lk.ijse.demokushan.dto.FeedbackDTO;
import lk.ijse.demokushan.dto.PaymentDTO;
import lk.ijse.demokushan.entity.Appointment;
import lk.ijse.demokushan.entity.Feedback;
import lk.ijse.demokushan.entity.Payment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AppointmentBOImpl implements AppointmentBO {

    AppointmentDAO appointmentDAO =(AppointmentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.APPOINTMENT);
    PaymentDAO paymentDAO =(PaymentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PAYMENT);

    FeedbackDAO feedbackDAO =(FeedbackDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.FEEDBACK);

    ProductDAO productDAO =(ProductDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PRODUCT);
    @Override
    public  boolean completeAppointment(PaymentDTO payment, FeedbackDTO feedback, String hairCutId) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        System.out.println(feedback.getFeedbackId() + " " +  feedback.getComment() + " " + feedback.getAppointmentId());

        System.out.println(hairCutId);
        try {

            boolean isPaymentUpdate = paymentDAO.add(new Payment(payment.getPaymentId(), payment.getPaymentType(), payment.getAppintmentId(), payment.getAmount()));
            System.out.println(isPaymentUpdate + " isPaymentUpdate");

            if (isPaymentUpdate) {
                boolean isFeedbackUpdate = feedbackDAO.add(new Feedback(feedback.getFeedbackId(), feedback.getComment(), feedback.getAppointmentId()));
                System.out.println(isFeedbackUpdate + " isFeedbackUpdate");

                if (isFeedbackUpdate) {
                    boolean isProductQtyUpdate = productDAO.productQtyUpdate(hairCutId);
                    System.out.println(isProductQtyUpdate + " isProductQtyUpdate");

                    if (isProductQtyUpdate) {
                        boolean isStatusUpdate = updateAppointmentStatus(feedback.getAppointmentId());
                        System.out.println(isStatusUpdate + " isStatusUpdate");

                        if (isStatusUpdate) {
                            System.out.println("commit");
                            connection.commit();
                            return true;
                        }
                    }
                }
            }
            connection.rollback();
            return false;
        } catch (Exception e) {
            connection.rollback();
            return false;
        } finally {
            connection.setAutoCommit(true);
//            return pstm.executeUpdate() > 0;
        }


    }

    @Override
    public boolean updateAppointmentStatus(String id) throws SQLException, ClassNotFoundException {

        return appointmentDAO.updateAppointmentStatus(id);

    }
    @Override
    public  String  generateNewID() throws SQLException, ClassNotFoundException {

        return  appointmentDAO.generateNewID();
    }
    @Override
    public  List<String> getAppointmentId() throws SQLException, ClassNotFoundException {

        return appointmentDAO.getAppointmentId();
    }
    @Override
    public  boolean add(AppointmentDTO appointment) throws SQLException, ClassNotFoundException {

        return appointmentDAO.add(new Appointment(appointment.getAppointmentId(),appointment.getTime(),appointment.getDate(),appointment.getEmployeeId(),appointment.getCustomerId(),appointment.getHairCutId(),appointment.getStatus()));

    }
    @Override
    public  int getCompleteAppointmentCount() throws SQLException, ClassNotFoundException {

        return appointmentDAO.getCompleteAppointmentCount();
    }
    @Override
    public  int getIncompleteAppointmentCount() throws SQLException, ClassNotFoundException {

        return appointmentDAO.getIncompleteAppointmentCount();
    }

    @Override
    public  ArrayList<Appointment> getAll() throws SQLException, ClassNotFoundException {

        return appointmentDAO.getAll();
    }
    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {

        return appointmentDAO.delete(id);
    }
    @Override
    public List<String> getAppointmentId(String appointmentId) throws SQLException, ClassNotFoundException {

        return appointmentDAO.getAppointmentId(appointmentId);
    }
    @Override
    public Appointment search(String id) throws SQLException, ClassNotFoundException {

        return appointmentDAO.search(id);
    }
    @Override
    public String getHairCutStyle(String id) throws SQLException, ClassNotFoundException {

        return appointmentDAO.getHairCutStyle(id);
    }

    @Override

    public List<String> getStatus() throws SQLException, ClassNotFoundException {

        return appointmentDAO.getStatus();
    }

    @Override
    public List<String> getStatus(String status) throws SQLException, ClassNotFoundException {

        return appointmentDAO.getStatus();
    }

    @Override
    public boolean update(AppointmentDTO appointment) throws SQLException, ClassNotFoundException {
//        String sql = "UPDATE appointment SET time = ?, date = ?, employeeId = ?, customerId =?, hairCutId = ?,  status = ? WHERE appointmentId = ?";
//
//        Connection connection = DbConnection.getInstance().getConnection();
//        PreparedStatement pstm = connection.prepareStatement(sql);
//        pstm.setObject(1, appointment.getTime());
//        pstm.setObject(2, appointment.getDate());
//        pstm.setObject(3, appointment.getEmployeeId());
//        pstm.setObject(4, appointment.getCustomerId());
//        pstm.setObject(5, appointment.getHairCutId());
//        pstm.setObject(6, appointment.getStatus());
//        pstm.setObject(7, appointment.getAppointmentId());

        return appointmentDAO.update(new Appointment(appointment.getTime(),appointment.getDate(),appointment.getEmployeeId(),appointment.getCustomerId(),appointment.getHairCutId(),appointment.getStatus(),appointment.getAppointmentId()));
    }

}
