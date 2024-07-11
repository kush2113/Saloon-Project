package lk.ijse.demokushan.Util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {

    public static boolean isTextFieldValid(TextField textField, String text) {
        String field = "";

        switch (textField) {
            case TYPE:
            case STYLE:
            case POSITION:
            case ADDRESS:
            case NAME:
                field = "^[A-Za-z\\s]{4,}$";
                break;

            case PASSWORD:
                field = "^(\\+94|0)?[1-9][0-9]{4}$";
                break;

            case CUSID:
                field = "^C\\d+$";
                break;
            case PHONENUMBER:
                field = "^\\d{10}$";
                break;
            case EMAIL:
                field = "^([A-z])([A-z0-9.]){1,}[@]([A-z0-9]){1,10}[.]([A-z]){2,5}$";
                break;

            case EID:
                field = "^E\\d+$";
                break;

            case AMOUNT:
            case PRICE:
            case SALARY:
                field = "^\\d{3,5}$";
                break;

            case APID:
                field = "^A\\d+$";
                break;

//            case PID:
//                field="^P\\d+$";
//                break;

            case QTYONHAND:
                field="^[0-9]{1,2}$";
                break;

            case HID:
                field="^HC\\d+$";
                break;

            case PAID:
                field="^PA\\d+$";
                break;

            case SID:
                field="^S\\d+$";
                break;

            case NIC:
                field="^([0-9]{9}[x|X|v|V]|[0-9]{12})$";
                break;

            case COLOR:
                field = "^^\\d+(100)100$";
                break;
        }


        Pattern pattern = Pattern.compile(field);

        if (text != null) {
            if (text.trim().isEmpty()) {
                return false;
            }
        } else {
            return false;
        }

        Matcher matcher = pattern.matcher(text);

        if (matcher.matches()) {
            return true;
        }
        return false;
    }

    public static boolean setTextColor(TextField location, javafx.scene.control.TextField textField) {
        if (Regex.isTextFieldValid(location, textField.getText())) {
            textField.setStyle("-fx-focus-color: green; -fx-unfocus-color: green; -fx-border-width: 1px; -fx-border-color: green;-fx-text-fill: green; ");

            return true;

        } else {
            textField.setStyle("-fx-focus-color: red; -fx-unfocus-color: red; -fx-border-width: 1px; -fx-border-color: red;green;-fx-text-fill: red;");

            return false;

        }
    }
}
