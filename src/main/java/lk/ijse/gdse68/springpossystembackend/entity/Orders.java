package lk.ijse.gdse68.springpossystembackend.entity;

import jakarta.persistence.*;
import lk.ijse.gdse68.springpossystembackend.customerObj.PlaceOrderResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
/**
 * @author : sachini
 * @date : 2024-10-12
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Orders implements Serializable, PlaceOrderResponse {
    @Id
    private String order_id;
    @ManyToOne
    @JoinColumn(name = "customer_id",referencedColumnName = "id",nullable = false)
    private Customer customer;
    private LocalDate date;
    private double total;
    private double txtCash;
    private double tatDiscount;
    @OneToMany(mappedBy = "orders",cascade = CascadeType.ALL)
    private List<OrderDetails> orderDetails;

}
