package lk.ijse.gdse68.springpossystembackend.service;

import lk.ijse.gdse68.springpossystembackend.dao.ItemDAO;
import lk.ijse.gdse68.springpossystembackend.dao.OrderDetailsDAO;
import lk.ijse.gdse68.springpossystembackend.dao.OrdersDAO;
import lk.ijse.gdse68.springpossystembackend.dto.OrdersDTO;
import lk.ijse.gdse68.springpossystembackend.entity.Item;
import lk.ijse.gdse68.springpossystembackend.entity.OrderDetails;
import lk.ijse.gdse68.springpossystembackend.entity.Orders;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PlaceOrderServiceImpl implements PlaceOrderService{
    @Autowired
    OrdersDAO ordersDAO;
    @Autowired
    OrderDetailsDAO orderDetailsDAO;
    @Autowired
    ItemDAO itemDAO;
    @Autowired
    ModelMapper modelMapper;
    @Override
    public void placeOrder(OrdersDTO ordersDTO) {
        if (ordersDAO.existsById(ordersDTO.getOrder_id())){
            throw new RuntimeException(ordersDTO.getOrder_id()+"Order Id already available!!");
        }
        Orders orders = modelMapper.map(ordersDTO,Orders.class);
        ordersDAO.save(orders);

        //TODO: Update Item Qty
        for (OrderDetails orderDetails : orders.getOrderDetails()){
            Item item = itemDAO.findById(orderDetails.getItem_code()).get();
            item.setQty(item.getQty()-orderDetails.getQty());
            itemDAO.save(item);
        }
    }
}
