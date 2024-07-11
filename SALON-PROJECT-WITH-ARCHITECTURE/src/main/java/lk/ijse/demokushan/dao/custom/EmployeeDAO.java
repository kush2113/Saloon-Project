package lk.ijse.demokushan.dao.custom;

import lk.ijse.demokushan.dao.CrudDAO;
import lk.ijse.demokushan.entity.Employee;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeDAO extends CrudDAO<Employee> {
    int getEmployeeCount() throws SQLException, ClassNotFoundException;

    List<String> getEmployeeId() throws SQLException, ClassNotFoundException;

    List<String> getEmployeeId(String id) throws SQLException, ClassNotFoundException;
}
