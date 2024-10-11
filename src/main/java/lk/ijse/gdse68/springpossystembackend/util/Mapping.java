package lk.ijse.gdse68.springpossystembackend.util;

import lk.ijse.gdse68.springpossystembackend.dto.CustomerDTO;
import lk.ijse.gdse68.springpossystembackend.dto.ItemDTO;
import lk.ijse.gdse68.springpossystembackend.entity.Customer;
import lk.ijse.gdse68.springpossystembackend.entity.Item;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
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
    public ItemDTO convertToDTO(Item item){
        return modelMapper.map(item,ItemDTO.class);
    }

    public Item convertToEntity(ItemDTO itemDTO){
        return modelMapper.map(itemDTO,Item.class);
    }
}
