package lk.ijse.demokushan.dto;

public class EmployeeDTO {
    private String employeeId;
    private String name;
    private String contactNumber;
    private String position;

    private String salary;

    public EmployeeDTO() {

    }

    public EmployeeDTO(String employeeId, String name, String contactNumber, String position, String salary) {
        this.employeeId = employeeId;
        this.name = name;
        this.contactNumber = contactNumber;
        this.position = position;
        this.salary = salary;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }
}
