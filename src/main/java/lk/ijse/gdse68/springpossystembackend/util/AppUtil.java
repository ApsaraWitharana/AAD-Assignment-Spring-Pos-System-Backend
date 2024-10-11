package lk.ijse.gdse68.springpossystembackend.util;

import java.util.UUID;
/**
 * @author : sachini
 * @date : 2024-10-11
 **/
public class AppUtil {
    public static  String createCustomerId(){
        return "CUS-"+ UUID.randomUUID();
    }
}
