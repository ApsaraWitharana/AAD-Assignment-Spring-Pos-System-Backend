package lk.ijse.gdse68.springpossystembackend.dto;

import lk.ijse.gdse68.springpossystembackend.customerObj.ItemResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
/**
 * @author : sachini
 * @date : 2024-10-11
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemDTO implements Serializable, ItemResponse {
    private String code;
    private String name;
    private BigDecimal price;
    private int qty;
}
