package lk.ijse.gdse68.springpossystembackend.service;

import lk.ijse.gdse68.springpossystembackend.customerObj.CustomerResponse;
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

import java.util.List;
import java.util.Optional;

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

    }

    @Override
    public CustomerResponse getSelectedItem(String code) {
        return null;
    }

    @Override
    public List<ItemDTO> getAllItem() {
        return null;
    }
}