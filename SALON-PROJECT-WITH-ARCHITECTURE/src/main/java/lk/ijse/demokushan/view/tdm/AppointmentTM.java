package lk.ijse.demokushan.view.tdm;

import java.time.LocalDate;

public class AppointmentTM extends ProductTM {

  private String appointmentId;

  private String time;
  private String date;
  private String  employeeId;
  private String customerId;
  private String hairCutId;
    private String status;


    public  AppointmentTM(){}

    public AppointmentTM(String appointmentId, String time, String date, String employeeId, String customerId, String hairCutId, String status) {
        this.appointmentId = appointmentId;
        this.time = time;
        this.date = date;
        this.employeeId = employeeId;
        this.customerId = customerId;
        this.hairCutId = hairCutId;
        this.status = status;
    }

    public AppointmentTM(String appointmentId, String time, LocalDate date, String employeeId, String customerId, String hairCutId, String status) {
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
        String DatePicker = date;
        return "appointmentTm{" +
                "appointmentId='" + appointmentId + '\'' +
                ", time='" + time + '\'' +
                ", date='" + DatePicker + '\'' +
                ", employeeId='" + employeeId + '\'' +
                ", customerId='" + customerId + '\'' +
                ", hairCutId='" + hairCutId + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
