package lk.ijse.gdse68.springpossystembackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Orders {
    @Id
    private String order_id;
    @ManyToOne(cascade = {CascadeType.REFRESH,CascadeType.DETACH})
    @JoinColumn(name = "customer_id",referencedColumnName = "id",nullable = false)
    private Customer customer_id;
    private LocalDate date;
    @OneToMany(mappedBy = "orders",cascade = CascadeType.ALL)
    private List<OrderDetails> orderDetails;
}
