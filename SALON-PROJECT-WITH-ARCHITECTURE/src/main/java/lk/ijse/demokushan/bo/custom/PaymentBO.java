package lk.ijse.demokushan.bo.custom;

import lk.ijse.demokushan.bo.SuperBO;
import lk.ijse.demokushan.dto.PaymentDTO;
import lk.ijse.demokushan.entity.Payment;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PaymentBO extends SuperBO {


    String generateNewID() throws SQLException, ClassNotFoundException;

    boolean add(PaymentDTO payment) throws SQLException, ClassNotFoundException;

    ArrayList<Payment> getAll() throws SQLException, ClassNotFoundException;

    boolean delete(String id) throws SQLException, ClassNotFoundException;

    Payment search(String id) throws SQLException, ClassNotFoundException;

    double getAllPaymentCount() throws SQLException, ClassNotFoundException;
}
