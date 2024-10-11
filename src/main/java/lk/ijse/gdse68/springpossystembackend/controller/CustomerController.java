package lk.ijse.gdse68.springpossystembackend.controller;

import lk.ijse.gdse68.springpossystembackend.dto.CustomerDTO;
import lk.ijse.gdse68.springpossystembackend.exception.DataPersisFailedException;
import lk.ijse.gdse68.springpossystembackend.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
/**
 * @author : sachini
 * @date : 2024-10-11
 **/
@RestController
@RequestMapping("api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {
    @Autowired
    private final CustomerService customerService;

    //TODO:Customer CRUD Implement

    //TODO: Save Customer
@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> saveCustomer(@RequestBody CustomerDTO customerDTO){
        //TODO : Validate
        if (customerDTO.getId() == null || !customerDTO.getId().matches("^CUS-[0-9]{3}$")) {
            return new ResponseEntity<>("Customer ID is empty or invalid! It should match 'CUS-000' format.", HttpStatus.BAD_REQUEST);
        }

        if (customerDTO.getName() == null || !customerDTO.getName().matches("^[A-Za-z ]{4,}$")) {
            return new ResponseEntity<>("Customer Name is empty or invalid! It should contain at least 4 alphabetic characters.", HttpStatus.BAD_REQUEST);
        }

        if (customerDTO.getAddress() == null || !customerDTO.getAddress().matches("^[A-Za-z0-9., -]{5,}$")) {
            return new ResponseEntity<>("Customer Address is empty or invalid! It should contain at least 5 alphanumeric characters.", HttpStatus.BAD_REQUEST);
        }

        if (customerDTO.getSalary() <= 0) {
            return new ResponseEntity<>("Customer Salary is empty or invalid! It must be greater than 0.", HttpStatus.BAD_REQUEST);
        }
        //TODO :If all validations pass, save the customer
    try {
        customerService.saveCustomer(customerDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }catch (DataPersisFailedException e){
        return new ResponseEntity<>("Customer data could not be saved, data persistence failed.",HttpStatus.NOT_FOUND);
    }catch (Exception e){
        return new ResponseEntity<>("Internal server error occurred while saving the customer.",HttpStatus.INTERNAL_SERVER_ERROR);
    }
    }
}
