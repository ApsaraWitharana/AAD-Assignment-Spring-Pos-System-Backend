package lk.ijse.gdse68.springpossystembackend.service;

import lk.ijse.gdse68.springpossystembackend.dto.OrdersDTO;
import lk.ijse.gdse68.springpossystembackend.entity.OrderDetails;

import java.util.List;

public interface PlaceOrderService  {
    String placeOrder(OrdersDTO ordersDTO);
}
