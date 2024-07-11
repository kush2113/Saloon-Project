package lk.ijse.demokushan.bo.custom.impl;

import lk.ijse.demokushan.bo.custom.FeedbackBO;
import lk.ijse.demokushan.dao.DAOFactory;
import lk.ijse.demokushan.dao.custom.FeedbackDAO;
import lk.ijse.demokushan.dto.FeedbackDTO;
import lk.ijse.demokushan.entity.Feedback;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FeedbackBOImpl implements FeedbackBO {

    FeedbackDAO feedbackDAO =(FeedbackDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.FEEDBACK);

    @Override
    public  String generateNewID() throws SQLException, ClassNotFoundException {

        return feedbackDAO.generateNewID();
    }

    @Override
    public boolean add(FeedbackDTO feedback) throws SQLException, ClassNotFoundException {
        return feedbackDAO.add(new Feedback(feedback.getFeedbackId(),feedback.getComment(),feedback.getAppointmentId()));
    }

    @Override
    public ArrayList<Feedback> getAll() throws SQLException, ClassNotFoundException {

        return feedbackDAO.getAll();
    }

}
