package lk.ijse.gdse68.springpossystembackend.controller;

import jakarta.persistence.ManyToOne;
import lk.ijse.gdse68.springpossystembackend.customerObj.CustomerErrorResponse;
import lk.ijse.gdse68.springpossystembackend.customerObj.ItemResponse;
import lk.ijse.gdse68.springpossystembackend.dto.CustomerDTO;
import lk.ijse.gdse68.springpossystembackend.dto.ItemDTO;
import lk.ijse.gdse68.springpossystembackend.entity.Customer;
import lk.ijse.gdse68.springpossystembackend.exception.DataPersisFailedException;
import lk.ijse.gdse68.springpossystembackend.exception.ItemNoteFound;
import lk.ijse.gdse68.springpossystembackend.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author : sachini
 * @date : 2024-10-11
 **/
@RestController
@RequestMapping("api/v1/item")
@RequiredArgsConstructor
public class ItemController {
    //TODO: Crud
    @Autowired
    private final ItemService itemService;

    Logger logger = LoggerFactory.getLogger(Customer.class);

    //TODO: ItemSave

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> saveItem(@RequestBody ItemDTO itemDTO){
        //TODO : Validate
        logger.info("connection initialized");
        // Validate code (pattern: ITM-000)
        if (itemDTO.getCode() == null || !itemDTO.getCode().matches("^ITM-[0-9]{3}$")) {
            return new ResponseEntity<>("Item code is empty or invalid! It should match 'ITM-000' format.", HttpStatus.BAD_REQUEST);
        }
        // Validate name (at least 4 alphabetic characters)
        if (itemDTO.getName() == null || !itemDTO.getName().matches("^[A-Za-z ]{4,}$")) {
            return new ResponseEntity<>("Item name is empty or invalid! It should contain at least 4 alphabetic characters.", HttpStatus.BAD_REQUEST);
        }
        // Validate price (should be greater than 0)
        if (itemDTO.getPrice() == null || itemDTO.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            return new ResponseEntity<>("Item price is empty or invalid! It must be a positive decimal.", HttpStatus.BAD_REQUEST);
        }
        // Validate quantity (should be greater than 0)
        if (itemDTO.getQty() <= 0) {
            return new ResponseEntity<>("Item quantity is empty or invalid! It must be greater than 0.", HttpStatus.BAD_REQUEST);
        }
        try {
            itemService.saveItem(itemDTO);
            logger.info("Item Save Successfully!!");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (DataPersisFailedException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //TODO:Item Update
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping(value = "/{code}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateItem(@PathVariable ("code") String code,@RequestBody ItemDTO itemDTO){
        if (itemDTO.getName() == null || !itemDTO.getName().matches("^[A-Za-z ]{4,}$")) {
            return new ResponseEntity<>("Item name is empty or invalid! It should contain at least 4 alphabetic characters.", HttpStatus.BAD_REQUEST);
        }
        if (itemDTO.getPrice() == null || itemDTO.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            return new ResponseEntity<>("Item price is empty or invalid! It must be a positive decimal.", HttpStatus.BAD_REQUEST);
        }

        if (itemDTO.getQty() <= 0) {
            return new ResponseEntity<>("Item quantity is empty or invalid! It must be greater than 0.", HttpStatus.BAD_REQUEST);
        }
        try {
            itemService.updateItem(code,itemDTO);
            logger.info("Item Update Successfully!!");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (ItemNoteFound e){
            return new ResponseEntity<>("Item not found!",HttpStatus.NOT_FOUND); ////return 404 item is not found
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //TODO:Delete item
    @DeleteMapping(value = "/{code}")
    public ResponseEntity<String> deleteItem(@PathVariable("code") String code){
        try {
            itemService.deleteItem(code);
            logger.info("Item Delete Successfully!!");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (ItemNoteFound e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //TODO:GetSelected id
    @GetMapping(value = "/{code}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ItemResponse>  getSelectedById(@PathVariable("code") String code){
        ItemResponse itemResponse = itemService.getSelectedItem(code);
        if (itemResponse instanceof ItemDTO) {
            return new ResponseEntity<>(itemResponse, HttpStatus.OK);
        }
        else if (itemResponse instanceof CustomerErrorResponse) {
            return new ResponseEntity<>(itemResponse, HttpStatus.NOT_FOUND);
        }
        logger.info("Get selected items Successfully!!");
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    //TODO:Item getAll
    @GetMapping
    public ResponseEntity<List<ItemDTO>> getAllItem(){
        List<ItemDTO> itemDTOS = itemService.getAllItem();
        if (!itemDTOS.isEmpty()){
            return new ResponseEntity<>(itemDTOS,HttpStatus.OK);
        }else {
            logger.info("Get all items Successfully!!");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
