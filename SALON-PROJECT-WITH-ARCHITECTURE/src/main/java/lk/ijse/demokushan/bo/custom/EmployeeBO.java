package lk.ijse.demokushan.bo.custom;

import lk.ijse.demokushan.bo.SuperBO;
import lk.ijse.demokushan.dto.EmployeeDTO;
import lk.ijse.demokushan.entity.Employee;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface EmployeeBO extends SuperBO {
    int getEmployeeCount() throws SQLException, ClassNotFoundException;

    String generateNewID() throws SQLException, ClassNotFoundException;

    List<String> getEmployeeId() throws SQLException, ClassNotFoundException;

    List<String> getEmployeeId(String id) throws SQLException, ClassNotFoundException;

    boolean add(Employee employee) throws SQLException, ClassNotFoundException;

    ArrayList<Employee> getAll() throws SQLException, ClassNotFoundException;

    Employee search(String id) throws SQLException, ClassNotFoundException;

    boolean delete(String id) throws SQLException, ClassNotFoundException;

    boolean update(EmployeeDTO employee) throws SQLException, ClassNotFoundException;
}
