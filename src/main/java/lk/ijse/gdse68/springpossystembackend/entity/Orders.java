package lk.ijse.gdse68.springpossystembackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
/**
 * @author : sachini
 * @date : 2024-10-12
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Orders implements Serializable {
    @Id
    private String order_id;
    @ManyToOne(cascade = {CascadeType.REFRESH,CascadeType.DETACH})
    @JoinColumn(name = "customer_id",referencedColumnName = "id",nullable = false)
    private Customer customer_id;
    private LocalDate date;
    private double total;
    @OneToMany(mappedBy = "orders",cascade = CascadeType.ALL)
    private List<OrderDetails> orderDetails;
}
