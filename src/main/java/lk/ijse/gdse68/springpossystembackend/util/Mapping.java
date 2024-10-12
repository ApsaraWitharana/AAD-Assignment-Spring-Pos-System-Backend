package lk.ijse.gdse68.springpossystembackend.util;

import lk.ijse.gdse68.springpossystembackend.dto.CustomerDTO;
import lk.ijse.gdse68.springpossystembackend.dto.ItemDTO;
import lk.ijse.gdse68.springpossystembackend.dto.OrderDetailsDTO;
import lk.ijse.gdse68.springpossystembackend.dto.OrdersDTO;
import lk.ijse.gdse68.springpossystembackend.entity.Customer;
import lk.ijse.gdse68.springpossystembackend.entity.Item;
import lk.ijse.gdse68.springpossystembackend.entity.OrderDetails;
import lk.ijse.gdse68.springpossystembackend.entity.Orders;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

/**
 * @author : sachini
 * @date : 2024-10-11
 **/
@Component
public class Mapping {
    @Autowired
    private ModelMapper modelMapper;

    public CustomerDTO convertToDTO(Customer customer){
        return modelMapper.map(customer,CustomerDTO.class);
    }
    public Customer convertToEntity(CustomerDTO customerDTO){
        return modelMapper.map(customerDTO,Customer.class);
    }

    public List<CustomerDTO> convertToCustomerEntity(List<Customer> customers){
        return modelMapper.map(customers, List.class);
    }
    public ItemDTO convertToDTO(Item item){
        return modelMapper.map(item,ItemDTO.class);
    }

    public Item convertToEntity(ItemDTO itemDTO){
        return modelMapper.map(itemDTO,Item.class);
    }

    public List<ItemDTO> convertToItemEntity(List<Item> items){
        return modelMapper.map(items, List.class);
    }
    public Orders convertToOrderEntity(OrdersDTO ordersDTO){
        return modelMapper.map(ordersDTO, Orders.class);
    }
    public OrderDetails convertToOrderDetailEntity(OrderDetailsDTO orderDetailsDTO){
        return modelMapper.map(orderDetailsDTO,OrderDetails.class);
    }
    }

