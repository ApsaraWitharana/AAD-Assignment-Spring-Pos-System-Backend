package lk.ijse.gdse68.springpossystembackend.util;

import java.time.LocalDate;
import java.util.UUID;
/**
 * @author : sachini
 * @date : 2024-10-11
 **/
public class AppUtil {
    public static  String createCustomerId(){
        return "CUS-"+ UUID.randomUUID();
    }
    public static String createItemCode(){
        return "ITM-"+UUID.randomUUID();
    }
    public static String createOrderId(){
        return "ORD-"+UUID.randomUUID();
    }

    public static LocalDate getCurrentDate() {
        return LocalDate.now();
    }
}