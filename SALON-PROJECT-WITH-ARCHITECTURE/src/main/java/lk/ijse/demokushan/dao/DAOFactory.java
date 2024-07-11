package lk.ijse.demokushan.dao;

import lk.ijse.demokushan.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getDaoFactory() {
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOTypes {
        CUSTOMER,PAYMENT,EMPLOYEE, APPOINTMENT,SUPPLIER,FEEDBACK,PRODUCT,PRODUCTDETAILS,HAIRCUT,QUERY
    }

    public SuperDAO getDAO(DAOTypes types){
        switch (types) {
            case CUSTOMER:
                return new CustomerDAOImpl();
            case EMPLOYEE:
                return new EmployeeDAOImpl();
            case APPOINTMENT:
                return new AppointmentDAOImpl();
            case PAYMENT:
                return new PaymentDAOImpl();
            case SUPPLIER:
                return new SupplierDAOImpl();
            case FEEDBACK:
                return new FeedbackDAOImpl();
            case PRODUCT:
                return new ProductDAOImpl();
            case PRODUCTDETAILS:
                return new ProductDetailsDAOImpl();
            case HAIRCUT:
                return new HairCutDAOImpl();
            case QUERY:
                return new QueryDAOImpl();
            default:
                return null;
        }
    }


}
