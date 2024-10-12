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
@IdClass(OrderItem_PK.class)
public class OrderDetails implements Serializable, PlaceOrderResponse {
    @Id
    private String item_code;
    @Id
    private String order_id;
    private int qty;
    private BigDecimal unitPrice;
    @ManyToOne
    @JoinColumn(name = "order_id",referencedColumnName = "order_id",insertable = false,updatable = false)
    private Orders orders;
    @ManyToOne
    @JoinColumn(name = "Item_code",referencedColumnName = "code",insertable = false,updatable = false)
    private Item item;
}
