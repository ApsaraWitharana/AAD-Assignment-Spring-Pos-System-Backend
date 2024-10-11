package lk.ijse.gdse68.springpossystembackend.customerObj;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
/**
 * @author : sachini
 * @date : 2024-10-11
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerErrorResponse implements CustomerResponse, Serializable {
    private int errorCode;
    private String errorMessage;
}
