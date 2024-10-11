package lk.ijse.gdse68.springpossystembackend.util;

import java.util.UUID;

public class AppUtil {
    public static  String createCustomerId(){
        return "CUS-"+ UUID.randomUUID();
    }
}
