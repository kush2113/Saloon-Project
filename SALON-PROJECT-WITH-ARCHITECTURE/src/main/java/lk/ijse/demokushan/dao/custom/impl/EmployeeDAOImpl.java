package lk.ijse.demokushan.dao.custom.impl;

import lk.ijse.demokushan.dao.SQLUtil;
import lk.ijse.demokushan.dao.custom.EmployeeDAO;
import lk.ijse.demokushan.db.DbConnection;
import lk.ijse.demokushan.entity.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {

    @Override
    public  int getEmployeeCount() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("select count(*) from employee ");

        if(resultSet.next()) {
            return resultSet.getInt(1);
        }
        return 0;
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {

        ResultSet resultSet =SQLUtil.execute("SELECT employeeId FROM employee ORDER BY employeeId DESC LIMIT 1");
        if(resultSet.next()) {
            String employeeId = resultSet.getString(1);
            return employeeId;
        }
        return null;
    }
    @Override
    public  List<String> getEmployeeId() throws SQLException, ClassNotFoundException {

        ResultSet resultSet =SQLUtil.execute("SELECT employeeId FROM employee");

        List<String> idList = new ArrayList<>();
        while (resultSet.next()) {
            idList.add(resultSet.getString(1));
        }
        return idList;
    }

    @Override
    public  List<String> getEmployeeId(String id) throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT employeeId FROM employee WHERE employeeId = ?",id);

        List<String> idList = new ArrayList<>();
        while (resultSet.next()) {
            idList.add(resultSet.getString(1));
        }
        return idList;
    }
    @Override
    public boolean add(Employee employee) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("INSERT INTO employee VALUES(?, ?, ?, ?, ?)",employee.getEmployeeId(),employee.getName(),employee.getContactNumber(),employee.getPosition(),employee.getSalary());
    }
    @Override
    public ArrayList<Employee> getAll() throws SQLException, ClassNotFoundException {

        ArrayList<Employee> allEmployee = new ArrayList<>();
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM employee");
        while (resultSet.next()) {
            String id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String contactNumber = resultSet.getString(3);
            String position = resultSet.getString(4);
            String salary = resultSet.getString(5);

            Employee employee = new Employee(id, name,  contactNumber, position,salary);
            allEmployee.add(employee);
        }
        return allEmployee;
    }
    @Override
    public Employee search(String id) throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT * FROM employee WHERE employeeId = ?",id);
        if (resultSet.next()) {
            String emp_id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String position = resultSet.getString(3);
            String contanctNumber = resultSet.getString(4);
            String salary = resultSet.getString(5);

            Employee employee = new Employee(emp_id, name, position, contanctNumber, salary);

            return employee ;
        }

        return null;
    }
    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("DELETE FROM employee WHERE employeeId = ?",id);
    }
    @Override
    public boolean update(Employee employee) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE employee SET employeeName = ?, phoneNumber = ?, position = ?, salary = ?  WHERE employeeId = ?",employee.getName(),employee.getContactNumber(),employee.getPosition(),employee.getSalary(),employee.getEmployeeId());
    }
}
