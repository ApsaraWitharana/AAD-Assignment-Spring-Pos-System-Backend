package lk.ijse.gdse68.springpossystembackend.dao;

import lk.ijse.gdse68.springpossystembackend.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersDAO extends JpaRepository<Orders,String> {
}
