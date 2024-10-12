package lk.ijse.gdse68.springpossystembackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class OrdersDTO {
    private String order_id;
    private LocalDate date;
    private String customer_id;
    private List<OrderDetailsDTO> orderDetails;

}
