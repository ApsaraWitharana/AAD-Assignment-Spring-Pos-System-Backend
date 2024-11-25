package lk.ijse.gdse68.springpossystembackend.entity;

import jakarta.persistence.*;
import lk.ijse.gdse68.springpossystembackend.customerObj.PlaceOrderResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.query.Order;

import java.io.Serializable;
import java.math.BigDecimal;
/**
 * @author : sachini
 * @date : 2024-10-12
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class OrderDetails implements Serializable, PlaceOrderResponse {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @ManyToOne
    @JoinColumn(name = "order_id",referencedColumnName = "order_id",nullable = false)
    private Orders orders;
    @ManyToOne
    @JoinColumn(name = "item_code",referencedColumnName = "code",nullable = false)
    private Item item;
    private BigDecimal price;
    private int qty;
    private double total;
}
