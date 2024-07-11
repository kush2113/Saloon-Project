package lk.ijse.demokushan.bo;

import lk.ijse.demokushan.bo.custom.impl.*;
//import lk.ijse.demokushan.bo.custom.impl.EmployeeBOImpl;

public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory(){
    }
    public static BOFactory getBoFactory(){
        return (boFactory==null)? boFactory=new BOFactory() : boFactory;
    }

    public enum BOTypes{
        CUSTOMER,PAYMENT,EMPLOYEE,APPOINTMENT,SUPPLIER,FEEDBACK,PRODUCT,PRODUCTDETAILS,HAIRCUT,

    }

    //Object creation logic for BO objects
    public SuperBO getBO(BOTypes types){
        switch (types){
            case CUSTOMER:
                return new CustomerBOImpl();
            case EMPLOYEE:
                return new EmployeeBOImpl();
            case APPOINTMENT:
                return new AppointmentBOImpl();
            case PAYMENT:
                return new PaymentBOImpl();
            case SUPPLIER:
                return new SupplierBOImpl();
            case FEEDBACK:
                return new FeedbackBOImpl();
            case PRODUCT:
                return new ProductBOImpl();
            case PRODUCTDETAILS:
                return  new ProductDetailsBOImpl();
            case HAIRCUT:
                return new HairCutBOImpl();
            default:
                return null;
        }
    }

}
