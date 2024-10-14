package lk.ijse.gdse68.springpossystembackend.controller;

import lk.ijse.gdse68.springpossystembackend.customerObj.PlaceOrderErrorResponse;
import lk.ijse.gdse68.springpossystembackend.customerObj.PlaceOrderResponse;
import lk.ijse.gdse68.springpossystembackend.dto.OrdersDTO;
import lk.ijse.gdse68.springpossystembackend.exception.DataPersisFailedException;
import lk.ijse.gdse68.springpossystembackend.service.PlaceOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
/**
 * @author : sachini
 * @date : 2024-10-12
 **/
@RestController
@RequestMapping("api/v1/place_order")
@RequiredArgsConstructor
public class PlaceOrderController {
    @Autowired
    PlaceOrderService placeOrderService;
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
     public ResponseEntity<String> placeOrder(@RequestBody OrdersDTO ordersDTO){
        try {
            placeOrderService.placeOrder(ordersDTO);
            return new  ResponseEntity<>(HttpStatus.CREATED);
        }catch (DataPersisFailedException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
