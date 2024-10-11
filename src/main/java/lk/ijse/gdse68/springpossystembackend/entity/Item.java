package lk.ijse.gdse68.springpossystembackend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "Item")
@Entity
public class Item {
    @Id
    private String code;
    private String name;
    private BigDecimal price;
    private int qty;
}
