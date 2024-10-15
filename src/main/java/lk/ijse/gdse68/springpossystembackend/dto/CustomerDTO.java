package lk.ijse.gdse68.springpossystembackend.dto;

import lk.ijse.gdse68.springpossystembackend.customerObj.CustomerResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * @author : sachini
 * @date : 2024-10-11
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerDTO implements SuperDTO, CustomerResponse {
    private String id;
    private String name;
    private String address;
    private Double salary;
}
