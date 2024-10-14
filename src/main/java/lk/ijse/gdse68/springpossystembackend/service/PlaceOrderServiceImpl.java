package lk.ijse.gdse68.springpossystembackend.service;

import jakarta.transaction.Transactional;
import lk.ijse.gdse68.springpossystembackend.dao.CustomerDAO;
import lk.ijse.gdse68.springpossystembackend.dao.ItemDAO;
import lk.ijse.gdse68.springpossystembackend.dao.OrdersDAO;
import lk.ijse.gdse68.springpossystembackend.dto.OrderDetailsDTO;
import lk.ijse.gdse68.springpossystembackend.dto.OrdersDTO;
import lk.ijse.gdse68.springpossystembackend.entity.Customer;
import lk.ijse.gdse68.springpossystembackend.entity.Item;
import lk.ijse.gdse68.springpossystembackend.entity.OrderDetails;
import lk.ijse.gdse68.springpossystembackend.entity.Orders;
import lk.ijse.gdse68.springpossystembackend.exception.ItemNoteFound;
import lk.ijse.gdse68.springpossystembackend.util.AppUtil;
import lk.ijse.gdse68.springpossystembackend.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class PlaceOrderServiceImpl implements PlaceOrderService {

    @Autowired
    private final OrdersDAO ordersDAO;
    @Autowired
    private final ItemDAO itemDAO;
    @Autowired
    private final CustomerDAO customerDAO;
    @Autowired
    private final Mapping mapping;

    @Override
    public String placeOrder(OrdersDTO ordersDTO) {

        // Find customer by ID
        Customer customer = customerDAO.findById(ordersDTO.getCustomer_id())
                .orElseThrow(() -> new RuntimeException("Customer not found!!"));

        // Create and set order details
        Orders order = new Orders();
        order.setCustomer(customer);
        order.setOrder_id(AppUtil.createOrderId());
        order.setDate(AppUtil.getCurrentDate());
        order.setTotal(ordersDTO.getTotal());
        order.setTxtCash(ordersDTO.getTxtCash());
        order.setTatDiscount(ordersDTO.getTxtDiscount());

        // Convert the orderDTO to an order entity
        Orders orders = mapping.convertToOrderEntity(ordersDTO);

        // Update each item's quantity and save order details
        for (OrderDetailsDTO orderDetailsDTO : ordersDTO.getOrderDetails()) {
            // Update item quantity
            updateItemQty(orderDetailsDTO);

            // Create OrderDetails entity
            OrderDetails orderDetails = new OrderDetails();
            orderDetails.setOrders(order);
            orderDetails.setItem(itemDAO.findById(orderDetailsDTO.getItem_code())
                    .orElseThrow(() -> new RuntimeException("Item not found!!")));
            orderDetails.setOrderQty(orderDetailsDTO.getQty());
            orderDetails.setPrice(orderDetailsDTO.getUnitPrice().doubleValue());

            // Add order details to the order
            orders.getOrderDetails().add(orderDetails);
        }

        // Save the entire order
        ordersDAO.save(order);

        return order.getOrder_id();  // Return order ID after successful save
    }

    private boolean updateItemQty(OrderDetailsDTO orderDetailsDTO) {
        String itemId = orderDetailsDTO.getItem_code();
        Optional<Item> optionalItem = itemDAO.findById(itemId);

        if (optionalItem.isPresent()) {
            Item item = optionalItem.get();
            // Update the quantity of the item
            item.setQty(item.getQty() - orderDetailsDTO.getQty());
            itemDAO.save(item);
            return true;
        } else {
            // Throw custom exception if the item is not found
            throw new ItemNoteFound("Item with id " + itemId + " not found!");
        }
    }
}
