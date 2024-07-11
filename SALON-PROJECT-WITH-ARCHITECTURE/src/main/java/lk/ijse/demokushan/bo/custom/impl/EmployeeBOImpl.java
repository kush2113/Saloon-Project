package lk.ijse.demokushan.bo.custom.impl;

import lk.ijse.demokushan.bo.custom.EmployeeBO;
import lk.ijse.demokushan.dao.DAOFactory;
import lk.ijse.demokushan.dao.custom.EmployeeDAO;
import lk.ijse.demokushan.db.DbConnection;
import lk.ijse.demokushan.dto.EmployeeDTO;
import lk.ijse.demokushan.entity.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeBOImpl implements EmployeeBO {

    EmployeeDAO employeeDAO = (EmployeeDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.EMPLOYEE);
    @Override
    public  int getEmployeeCount() throws SQLException, ClassNotFoundException {

        return employeeDAO.getEmployeeCount();
    }

    @Override
    public  String generateNewID() throws SQLException, ClassNotFoundException {

        return employeeDAO.generateNewID();
    }
    @Override
    public List<String> getEmployeeId() throws SQLException, ClassNotFoundException {

        return employeeDAO.getEmployeeId();
    }

    @Override
    public List<String> getEmployeeId(String id) throws SQLException, ClassNotFoundException {

        return employeeDAO.getEmployeeId(id);
    }
    @Override
    public boolean add(Employee employee) throws SQLException, ClassNotFoundException {

        return employeeDAO.add(new Employee(employee.getEmployeeId(),employee.getName(),employee.getContactNumber(),employee.getPosition(),employee.getSalary()));
    }
    @Override
    public ArrayList<Employee> getAll() throws SQLException, ClassNotFoundException {

        return employeeDAO.getAll();
    }
    @Override
    public Employee search(String id) throws SQLException, ClassNotFoundException {

            return employeeDAO.search(id) ;

    }
    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {

        return employeeDAO.delete(id);
    }
    @Override
    public boolean update(EmployeeDTO employee) throws SQLException, ClassNotFoundException {
        return employeeDAO.update(new Employee(employee.getEmployeeId(), employee.getName(), employee.getContactNumber(), employee.getPosition(),employee.getSalary()));
    }

}
