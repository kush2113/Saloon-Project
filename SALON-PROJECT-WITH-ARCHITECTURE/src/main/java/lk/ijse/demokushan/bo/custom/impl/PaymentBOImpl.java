package lk.ijse.demokushan.bo.custom.impl;

import lk.ijse.demokushan.bo.custom.PaymentBO;
import lk.ijse.demokushan.dao.DAOFactory;
import lk.ijse.demokushan.dao.custom.PaymentDAO;
import lk.ijse.demokushan.db.DbConnection;
import lk.ijse.demokushan.dto.PaymentDTO;
import lk.ijse.demokushan.entity.Payment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentBOImpl implements PaymentBO {

    PaymentDAO paymentDAO =(PaymentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PAYMENT);
    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {

        return paymentDAO.generateNewID();
    }

    @Override
    public  boolean add(PaymentDTO payment) throws SQLException, ClassNotFoundException {

        return paymentDAO.add(new Payment(payment.getPaymentId(),payment.getPaymentType(),payment.getAppintmentId(),payment.getAmount()));
    }
    @Override
    public  ArrayList<Payment> getAll() throws SQLException, ClassNotFoundException {

        return paymentDAO.getAll();
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {

        return paymentDAO.delete(id);
    }
    @Override
    public Payment search(String id) throws SQLException, ClassNotFoundException {

        return paymentDAO.search(id);
    }

    @Override
    public  double getAllPaymentCount() throws SQLException, ClassNotFoundException {

        return paymentDAO.getAllPaymentCount();
    }

}
