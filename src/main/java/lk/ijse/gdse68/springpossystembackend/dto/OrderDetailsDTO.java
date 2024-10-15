package lk.ijse.gdse68.springpossystembackend.dto;

import lk.ijse.gdse68.springpossystembackend.customerObj.PlaceOrderResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
/**
 * @author : sachini
 * @date : 2024-10-12
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class OrderDetailsDTO implements Serializable, PlaceOrderResponse {
    private String order_id;
    private String item_code;
    private int qty;
    private BigDecimal price;
    private double total;
}
