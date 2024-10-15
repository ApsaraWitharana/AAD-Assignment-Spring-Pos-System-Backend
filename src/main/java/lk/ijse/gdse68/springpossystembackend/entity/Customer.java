package lk.ijse.gdse68.springpossystembackend.entity;

import jakarta.persistence.*;
import lk.ijse.gdse68.springpossystembackend.dto.SuperDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author : sachini
 * @date : 2024-10-11
 **/
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Customer")
public class Customer implements SuperDTO {
    @Id
    @Column(name = "id", length = 50)
    private String id;
    @Column(name = "name", length = 50)
    private String name;
    @Column(name = "address", length = 50)
    private String address;
    @Column(name = "salary")
    private Double salary;
    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Orders> orders;
}
