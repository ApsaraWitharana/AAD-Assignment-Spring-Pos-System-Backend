package lk.ijse.gdse68.springpossystembackend.entity;

import jakarta.persistence.*;
import lk.ijse.gdse68.springpossystembackend.dto.SuperDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author : sachini
 * @date : 2024-10-11
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "Item")
@Entity
public class Item implements SuperDTO {
    @Id
    private String code;
    private String name;
    private BigDecimal price;
    private int qty;
    @OneToMany(mappedBy = "item",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<OrderDetails> orderItems;
}
