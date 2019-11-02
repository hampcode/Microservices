package com.hampcode.service;

import com.hampcode.model.entity.Item;

public interface ItemService extends CrudService<Item, Long> {

	Item shoppingCart(Long productId, Integer quantity);
}
