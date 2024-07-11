package lk.ijse.demokushan.view.tdm;

public class EmployeeTM {
    private String employeeId;
    private String name;
    private String contactNumber;
    private String position;

    private String salary;

    public EmployeeTM() {

    }

    public EmployeeTM(String employeeId, String name, String contactNumber, String position, String salary) {
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

    @Override
    public String toString() {
        return "employeeTM{" +
                "employeeId='" + employeeId + '\'' +
                ", name='" + name + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", position='" + position + '\'' +
                ", salary='" + salary + '\'' +
                '}';
    }
}
