package lk.ijse.gdse68.springpossystembackend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * @author : sachini
 * @date : 2024-10-11
 **/
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Customer")
public class Customer {
    @Id
    @Column(name = "id", length = 50)
    private String id;
    @Column(name = "name", length = 50)
    private String name;
    @Column(name = "address", length = 50)
    private String address;
    @Column(name = "salary")
    private Double salary;
}
