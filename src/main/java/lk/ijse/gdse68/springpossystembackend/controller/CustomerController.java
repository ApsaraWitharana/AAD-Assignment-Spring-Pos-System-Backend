package lk.ijse.gdse68.springpossystembackend.controller;

import lk.ijse.gdse68.springpossystembackend.customerObj.CustomerErrorResponse;
import lk.ijse.gdse68.springpossystembackend.customerObj.CustomerResponse;
import lk.ijse.gdse68.springpossystembackend.dto.CustomerDTO;
import lk.ijse.gdse68.springpossystembackend.entity.Customer;
import lk.ijse.gdse68.springpossystembackend.exception.CustomerNoteFound;
import lk.ijse.gdse68.springpossystembackend.exception.DataPersisFailedException;
import lk.ijse.gdse68.springpossystembackend.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    Logger logger = LoggerFactory.getLogger(Customer.class);    //TODO:Customer CRUD Implement

    //TODO: Save Customer
@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> saveCustomer(@RequestBody CustomerDTO customerDTO){
              logger.info("connection initialized");
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
            logger.info("Customer Save Successfully!!");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (DataPersisFailedException e){
            System.err.println("Data persistence failed: " + e.getMessage());
            return new ResponseEntity<>("Customer data could not be saved, data persistence failed.",HttpStatus.NOT_FOUND);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Internal server error occurred while saving the customer.",HttpStatus.INTERNAL_SERVER_ERROR);
        }
     }
          //TODO: Update Customer

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateCustomer(@PathVariable ("id") String id , @RequestBody CustomerDTO customerDTO) {
        //TODO : Validate

        if (customerDTO.getName() == null || !customerDTO.getName().matches("^[A-Za-z ]{4,}$")) {
            return new ResponseEntity<>("Customer Name is empty or invalid! It should contain at least 4 alphabetic characters.", HttpStatus.BAD_REQUEST);
        }

        if (customerDTO.getAddress() == null || !customerDTO.getAddress().matches("^[A-Za-z0-9., -]{5,}$")) {
            return new ResponseEntity<>("Customer Address is empty or invalid! It should contain at least 5 alphanumeric characters.", HttpStatus.BAD_REQUEST);
        }

        if (customerDTO.getSalary() <= 0) {
            return new ResponseEntity<>("Customer Salary is empty or invalid! It must be greater than 0.", HttpStatus.BAD_REQUEST);
        }
        try {
            customerService.updateCustomer(id, customerDTO);
            logger.info("Customer Update Successfully!!");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (CustomerNoteFound e) {
            return new ResponseEntity<>("Customer not found!",HttpStatus.NO_CONTENT);// Return 404 if customer is not found
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //TODO:Delete customer
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable ("id") String id){
     try {
         customerService.deleteCustomer(id);
         logger.info("Customer Delete Successfully!!");
         return new ResponseEntity<>(HttpStatus.NO_CONTENT);
     }catch (CustomerNoteFound e){
         return new ResponseEntity<>("Customer Delete not found!",HttpStatus.NO_CONTENT); // Customer does not exist
     }catch (Exception e){
         return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

      }
    }
    //TODO: GetSelect ID
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerResponse> getSelectCustomer(@PathVariable("id") String id) {
        // Check if the ID exists in the DB and return the corresponding response
        CustomerResponse response = customerService.getSelectedCustomer(id);

        // If it's a success response (CustomerDTO), return OK (200)
        if (response instanceof CustomerDTO) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        // If it's an error response (CustomerErrorResponse), return NOT_FOUND (404)
        else if (response instanceof CustomerErrorResponse) {
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        logger.error("Error fetching customer: ");
        // If neither, something unexpected happened, return INTERNAL_SERVER_ERROR (500)
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }




    //TODO:GetAll Customers
    @GetMapping
    public ResponseEntity<List<CustomerDTO>> getAllCustomer(){
    List<CustomerDTO> customerDTOS = customerService.getAllCustomers();
        if (!customerDTOS.isEmpty()){
            return new ResponseEntity<>(customerDTOS,HttpStatus.OK);
        }else {
            logger.error("Get All Customer!!");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
         }
    }

}


