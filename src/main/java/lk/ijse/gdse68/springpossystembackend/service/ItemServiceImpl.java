package lk.ijse.gdse68.springpossystembackend.service;

import lk.ijse.gdse68.springpossystembackend.customerObj.CustomerResponse;
import lk.ijse.gdse68.springpossystembackend.customerObj.ItemResponse;
import lk.ijse.gdse68.springpossystembackend.dao.ItemDAO;
import lk.ijse.gdse68.springpossystembackend.dto.CustomerDTO;
import lk.ijse.gdse68.springpossystembackend.dto.ItemDTO;
import lk.ijse.gdse68.springpossystembackend.entity.Item;
import lk.ijse.gdse68.springpossystembackend.exception.DataPersisFailedException;
import lk.ijse.gdse68.springpossystembackend.exception.ItemNoteFound;
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
public class ItemServiceImpl implements ItemService {
    @Autowired
    private Mapping mapping;

    @Autowired
    private ItemDAO itemDAO;

    @Override
    public void saveItem(ItemDTO itemDTO) {
        itemDTO.setCode(AppUtil.createItemCode());
        var Item = mapping.convertToEntity(itemDTO);
        var saveItem = itemDAO.save(Item);
        System.out.println(itemDTO);

        if (saveItem == null){
            throw new DataPersisFailedException("Customer save Note found!");
        }
    }

    @Override
    public void updateItem(String code, ItemDTO itemDTO) {

        Optional<Item> tmpItemEntity = itemDAO.findById(code);
        if (!tmpItemEntity.isPresent()){
            throw new ItemNoteFound("Item update not found!");
        }else {
            Item item = tmpItemEntity.get();
            item.setName(itemDTO.getName());
            item.setPrice(itemDTO.getPrice());
            item.setQty(item.getQty());
            itemDAO.save(item);
        }
    }

    @Override
    public void deleteItem(String code) {
     Optional<Item> findId = itemDAO.findById(code);
     if (!findId.isPresent()){
         throw new RuntimeException("Item update not found!");
     }else {
         itemDAO.deleteById(code);
     }
    }

    @Override
    public ItemResponse getSelectedItem(String code) {
       if (itemDAO.existsById(code)){
           return mapping.convertToDTO(itemDAO.getReferenceById(code));
       }else {
           throw new ItemNoteFound("Item not found!");
       }
    }

    @Override
    public List<ItemDTO> getAllItem() {
        List<Item> items = itemDAO.findAll();
        List<ItemDTO> itemDTOS = new ArrayList<>();
        for (Item item :items){
            ItemDTO itemDTO = new ItemDTO();
            itemDTO.setCode(item.getCode());
            itemDTO.setName(item.getName());
            itemDTO.setPrice(item.getPrice());
            itemDTO.setQty(item.getQty());
            itemDTOS.add(itemDTO);
        }
        return itemDTOS;
    }
}