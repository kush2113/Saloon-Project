package lk.ijse.demokushan.dao.custom.impl;

import lk.ijse.demokushan.dao.SQLUtil;
import lk.ijse.demokushan.dao.custom.FeedbackDAO;
import lk.ijse.demokushan.entity.Feedback;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FeedbackDAOImpl implements FeedbackDAO {


    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT feedbackId FROM  feedback ORDER BY feedbackId DESC LIMIT 1");
        if(resultSet.next()) {
            String feedbackId = resultSet.getString(1);
            return feedbackId;
        }
        return null;
    }

    @Override
    public boolean add(Feedback feedback) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO feedback VALUES(?, ?, ?)",feedback.getFeedbackId(),feedback.getComment(),feedback.getAppointmentId());
    }

    @Override
    public ArrayList<Feedback> getAll() throws SQLException, ClassNotFoundException {

        ArrayList<Feedback> allFeedback = new ArrayList<>();
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM feedback");

        while (resultSet.next()) {
            String feedbackId = resultSet.getString(1);
            String comment = resultSet.getString(2);
            String appointmentId = resultSet.getString(3);

            Feedback feedback = new Feedback(feedbackId, comment, appointmentId );
            allFeedback.add(feedback);
        }
        return allFeedback;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Feedback search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean update(Feedback entity) throws SQLException, ClassNotFoundException {
        return false;
    }

}
