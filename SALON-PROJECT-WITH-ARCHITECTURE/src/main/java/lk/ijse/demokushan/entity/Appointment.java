package lk.ijse.demokushan.entity;

public class Appointment {
    private String appointmentId;
    private String time;
    private String date;
    private String employeeId;
    private String customerId;
    private String hairCutId;

    private String status;
    public Appointment() {
    }
    
    public Appointment(String appointmentId, String time, String date, String employeeId, String customerId, String hairCutId, String status) {
        this.appointmentId = appointmentId;
        this.time = time;
        this.date = date;
        this.employeeId = employeeId;
        this.customerId = customerId;
        this.hairCutId = hairCutId;
        this.status = status;
    }


    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getHairCutId() {
        return hairCutId;
    }

    public void setHairCutId(String hairCutId) {
        this.hairCutId = hairCutId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    @Override
    public String toString() {
        return "Appointment{" +
                "appointmentId='" + appointmentId + '\'' +
                ", time='" + time + '\'' +
                ", date='" + date + '\'' +
                ", employeeId='" + employeeId + '\'' +
                ", customerId='" + customerId + '\'' +
                ", hairCutId='" + hairCutId + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

}