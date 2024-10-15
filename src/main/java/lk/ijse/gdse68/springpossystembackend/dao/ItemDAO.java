package lk.ijse.gdse68.springpossystembackend.dao;

import lk.ijse.gdse68.springpossystembackend.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemDAO extends JpaRepository<Item,String> {
}
