package lk.ijse.gdse68.springpossystembackend.service;

import lk.ijse.gdse68.springpossystembackend.customerObj.ItemResponse;
import lk.ijse.gdse68.springpossystembackend.dao.CustomerDAO;
import lk.ijse.gdse68.springpossystembackend.dao.ItemDAO;
import lk.ijse.gdse68.springpossystembackend.dao.OrdersDAO;
import lk.ijse.gdse68.springpossystembackend.dto.ItemDTO;
import lk.ijse.gdse68.springpossystembackend.dto.OrderDetailsDTO;
import lk.ijse.gdse68.springpossystembackend.dto.OrdersDTO;
import lk.ijse.gdse68.springpossystembackend.entity.Customer;
import lk.ijse.gdse68.springpossystembackend.entity.Item;
import lk.ijse.gdse68.springpossystembackend.entity.OrderDetails;
import lk.ijse.gdse68.springpossystembackend.entity.Orders;
import lk.ijse.gdse68.springpossystembackend.exception.DataPersisFailedException;
import lk.ijse.gdse68.springpossystembackend.exception.ItemNoteFound;
import lk.ijse.gdse68.springpossystembackend.util.AppUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class PlaceOrderServiceImpl implements PlaceOrderService{
    @Autowired
    private final OrdersDAO ordersDAO;
    @Autowired
    private final ItemDAO itemDAO;
    @Autowired
    CustomerDAO customerDAO;
    @Autowired
     ModelMapper modelMapper;
    @Override
    public String placeOrder(OrdersDTO ordersDTO) {

        Customer customer = customerDAO.findById(ordersDTO.getCustomer_id())
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        Orders order = new Orders();
        order.setCustomer(customer);  // Set the customer here
        // Set other properties of the order

        order.setOrder_id(AppUtil.createOrderId());
        order.setDate(AppUtil.getCurrentDate());
        order.setTotal(ordersDTO.getTotal());
        order.setTxtCash(ordersDTO.getTxtCash());
        order.setTatDiscount(ordersDTO.getTxtDiscount());
        ordersDAO.save(order);
        Orders orders = modelMapper.map(ordersDTO, Orders.class);


        List<OrderDetails> orderDetailsEntities = ordersDTO.getOrderDetails().stream().map(detail->{
            OrderDetails orderDetailsEntity = modelMapper.map(detail, OrderDetails.class);
            orderDetailsEntity.setOrderQty(ordersDTO.getOrderQty());
            orderDetailsEntity.setOrders(orders);
            return orderDetailsEntity;
        })
                .collect(Collectors.toList());
        orders.setOrderDetails(orderDetailsEntities);
//        boolean allItemsUpdate = ordersDTO.getOrderDetails().stream().allMatch(this::updateItemQty);

//        if (allItemsUpdate){
            ordersDAO.save(orders);
            return "PlaceOrder  Successfully!!";
      //  }else {
           // throw new DataPersisFailedException("PlaceOrder UnSuccessfully push!");
      //  }
    }

    private boolean updateItemQty(OrderDetailsDTO orderDetailsDTO) {
        String itemId = orderDetailsDTO.getItem_code();
        Optional<Item> optionalItem = itemDAO.findById(itemId);

        if (optionalItem.isPresent()) {
            Item item = optionalItem.get();

            item.setQty(item.getQty() - orderDetailsDTO.getQty());
            itemDAO.save(item);
            return true;
        } else {
            // Throw custom exception if the item is not found
            throw new ItemNoteFound("Item with id " + itemId + " not found!");
        }
    }


}
