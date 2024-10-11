package lk.ijse.gdse68.springpossystembackend.service;

import lk.ijse.gdse68.springpossystembackend.customerObj.CustomerErrorResponse;
import lk.ijse.gdse68.springpossystembackend.customerObj.CustomerResponse;
import lk.ijse.gdse68.springpossystembackend.dao.CustomerDAO;
import lk.ijse.gdse68.springpossystembackend.dto.CustomerDTO;
import lk.ijse.gdse68.springpossystembackend.entity.Customer;
import lk.ijse.gdse68.springpossystembackend.exception.CustomerNoteFound;
import lk.ijse.gdse68.springpossystembackend.exception.DataPersisFailedException;
import lk.ijse.gdse68.springpossystembackend.util.AppUtil;
import lk.ijse.gdse68.springpossystembackend.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author : sachini
 * @date : 2024-10-11
 **/
@Service
@Transactional
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService{
    @Autowired
    private Mapping mapping;
    @Autowired
    private CustomerDAO customerDAO;
    @Override
    public void saveCustomer(CustomerDTO customerDTO) {
        customerDTO.setId(AppUtil.createCustomerId());
        var Customer = mapping.convertToEntity(customerDTO);
        var saveCustomer = customerDAO.save(Customer);
        System.out.println(customerDTO);

        if (saveCustomer == null){
            throw new DataPersisFailedException("Customer save Note found!");
        }
    }

    @Override
    public void updateCustomer(String id, CustomerDTO customerDTO) {
        Optional<Customer> tmpCustomerEntity = customerDAO.findById(id);
        if (!tmpCustomerEntity.isPresent()){
            System.out.println("Customer not found");
            throw new CustomerNoteFound("Customer update not found!");
        }else {
            Customer customer = tmpCustomerEntity.get();
            customer.setName(customerDTO.getName());
            customer.setAddress(customerDTO.getAddress());
            customer.setSalary(customerDTO.getSalary());
            customerDAO.save(customer);// Save the updated entity back to the database
            System.out.println("Customer updated : " + customerDTO);
        }
    }

    @Override
    public void deleteCustomer(String id) {
        Optional<Customer> findId = customerDAO.findById(id);
        if (!findId.isPresent()){
            throw new CustomerNoteFound("Customer not found!");
        }else {
            customerDAO.deleteById(id);
        }
    }

    @Override
    public CustomerResponse getSelectedCustomer(String id) {

        if (customerDAO.existsById(id)) {
            Customer customer = customerDAO.getReferenceById(id);
            System.out.println("Customer found : " + customer);
            CustomerDTO customerDTO = mapping.convertToDTO(customer);
            customerDTO.setName(customerDTO.getName().split(" ")[0]);
            return customerDTO;
        } else {
            System.out.println("Customer not found");
            return new CustomerErrorResponse(0, "Customer not found");
        }
    }






    @Override
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customers = customerDAO.findAll();
        List<CustomerDTO> customerDTOS = new ArrayList<>();
        for (Customer customer : customers){
            CustomerDTO customerDTO = new CustomerDTO();
            customerDTO.setId(customer.getId());
            customerDTO.setName(customer.getName());
            customerDTO.setSalary(customer.getSalary());
            customerDTOS.add(customerDTO);
            System.out.println(customerDTOS);
        }
        return customerDTOS;
    }
}
