package lk.ijse.demokushan.bo.custom;

import lk.ijse.demokushan.bo.SuperBO;
import lk.ijse.demokushan.dto.FeedbackDTO;
import lk.ijse.demokushan.entity.Feedback;

import java.sql.SQLException;
import java.util.ArrayList;

public interface FeedbackBO extends SuperBO {

    String generateNewID() throws SQLException, ClassNotFoundException;

    boolean add(FeedbackDTO feedback) throws SQLException, ClassNotFoundException;

    ArrayList<Feedback> getAll() throws SQLException, ClassNotFoundException;
}
