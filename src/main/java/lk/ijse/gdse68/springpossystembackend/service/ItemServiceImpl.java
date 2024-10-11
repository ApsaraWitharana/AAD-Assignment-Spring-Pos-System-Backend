package lk.ijse.gdse68.springpossystembackend.service;

import lk.ijse.gdse68.springpossystembackend.customerObj.CustomerResponse;
import lk.ijse.gdse68.springpossystembackend.dao.ItemDAO;
import lk.ijse.gdse68.springpossystembackend.dto.CustomerDTO;
import lk.ijse.gdse68.springpossystembackend.dto.ItemDTO;
import lk.ijse.gdse68.springpossystembackend.exception.DataPersisFailedException;
import lk.ijse.gdse68.springpossystembackend.util.AppUtil;
import lk.ijse.gdse68.springpossystembackend.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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