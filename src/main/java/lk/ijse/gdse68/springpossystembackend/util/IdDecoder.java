package lk.ijse.gdse68.springpossystembackend.util;

import java.util.Base64;

public class IdDecoder {
    public String decodeId(String encodedId) {
        return new String(Base64.getDecoder().decode(encodedId));
    }
}

