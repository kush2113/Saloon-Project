package lk.ijse.demokushan.dao.custom.impl;

import lk.ijse.demokushan.dao.SQLUtil;
import lk.ijse.demokushan.dao.custom.PaymentDAO;
import lk.ijse.demokushan.entity.Payment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAOImpl implements PaymentDAO {


    @Override
    public  String generateNewID() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT paymentId FROM payment ORDER BY paymentId DESC LIMIT 1");
        if(resultSet.next()) {
            String paymentId = resultSet.getString(1);
            return paymentId;
        }
        return null;
    }

    @Override
    public  boolean add(Payment payment) throws SQLException, ClassNotFoundException {


        return SQLUtil.execute("INSERT INTO payment VALUES(?, ?, ?, ?)",payment.getPaymentId(),payment.getPaymentType(),payment.getAppintmentId(),payment.getAmount());
    }

    @Override
    public boolean update(Payment entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public  ArrayList<Payment> getAll() throws SQLException, ClassNotFoundException {

        ArrayList<Payment> allPayment = new ArrayList<>();
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM payment");

        while (resultSet.next()) {
            String paymentId = resultSet.getString(1);
            String paymentType = resultSet.getString(2);
            String appointmetId = resultSet.getString(3);
            String amount = resultSet.getString(4);


            Payment payment = new Payment(paymentId, paymentType,appointmetId, amount);
           allPayment.add(payment);
        }
        return allPayment;
    }

    @Override
    public  boolean delete(String id) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("DELETE FROM payment WHERE paymentId = ?",id);
    }

    @Override
    public  Payment search(String id) throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT * FROM payment WHERE paymentId = ?",id);
        if (resultSet.next()) {
            String paymentId = resultSet.getString(1);
            String paymentType = resultSet.getString(2);
            String appointmentId = resultSet.getString(3);
            String amount = resultSet.getString(4);

            Payment payment = new Payment(paymentId, paymentType,appointmentId, amount);

            return payment ;
        }

        return null;
    }

    @Override
    public  double getAllPaymentCount() throws SQLException, ClassNotFoundException {

        ResultSet resultSet =SQLUtil.execute("SELECT SUM(amount) FROM payment");
        if (resultSet.next()) {
            return resultSet.getDouble(1) ;
        }
        return 0;
    }
}
