package lk.ijse.gdse68.springpossystembackend.dao;

import lk.ijse.gdse68.springpossystembackend.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * @author : sachini
 * @date : 2024-10-11
 **/
@Repository
public interface CustomerDAO extends JpaRepository<Customer,String> {
}
