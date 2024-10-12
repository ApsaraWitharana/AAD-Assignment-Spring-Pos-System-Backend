package lk.ijse.gdse68.springpossystembackend.service;

import lk.ijse.gdse68.springpossystembackend.dao.CustomerDAO;
import lk.ijse.gdse68.springpossystembackend.dao.ItemDAO;
import lk.ijse.gdse68.springpossystembackend.dao.OrderDetailsDAO;
import lk.ijse.gdse68.springpossystembackend.dao.OrdersDAO;
import lk.ijse.gdse68.springpossystembackend.dto.OrdersDTO;
import lk.ijse.gdse68.springpossystembackend.entity.Customer;
import lk.ijse.gdse68.springpossystembackend.entity.Item;
import lk.ijse.gdse68.springpossystembackend.entity.OrderDetails;
import lk.ijse.gdse68.springpossystembackend.entity.Orders;
import lk.ijse.gdse68.springpossystembackend.util.AppUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PlaceOrderServiceImpl implements PlaceOrderService{
    @Autowired
    OrdersDAO ordersDAO;
    @Autowired
    OrderDetailsDAO orderDetailsDAO;
    @Autowired
    ItemDAO itemDAO;
    @Autowired
    CustomerDAO customerDAO;
    @Autowired
    ModelMapper modelMapper;
    @Override
    public void placeOrder(OrdersDTO ordersDTO) {
        ordersDTO.setOrder_id(AppUtil.createOrderId());
        ordersDTO.setDate(AppUtil.getCurrentDate());
        ordersDTO.setTotal(ordersDTO.getOrderDetails().stream().mapToDouble(detail-> detail.getQty() * detail.getUnitPrice()).sum());

    }

}
