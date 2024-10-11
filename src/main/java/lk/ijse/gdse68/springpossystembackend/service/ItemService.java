package lk.ijse.gdse68.springpossystembackend.service;

import lk.ijse.gdse68.springpossystembackend.customerObj.ItemResponse;
import lk.ijse.gdse68.springpossystembackend.dto.ItemDTO;

import java.util.List;
/**
 * @author : sachini
 * @date : 2024-10-11
 **/

public interface ItemService {
    void saveItem(ItemDTO itemDTO);
    void updateItem(String code,ItemDTO itemDTO);
    void deleteItem(String code);
    ItemResponse getSelectedItem(String code);

    List<ItemDTO> getAllItem();
}
