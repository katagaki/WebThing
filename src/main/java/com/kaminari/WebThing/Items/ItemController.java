package com.kaminari.WebThing.Items;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ItemController {
	@Autowired private ItemService itemManager = new ItemService();

	@GetMapping("/items")
	public List<Item> allItems() {
		return itemManager.allItems();
	}

	@PostMapping("/items/new")
	public boolean createItem(@RequestBody Item item) {
		return itemManager.insert(item);
	}

	@GetMapping("/items/{id}")
	public Item getItem(@PathVariable Integer id) {
		return itemManager.item(id);
	}

	@GetMapping("/items/forBrand/{brand}")
	public List<Item> getItemsForBrand(@PathVariable String brand) {
		return itemManager.itemsForBrand(brand);
	}

	@PutMapping("/items/{id}")
	public boolean modifyItem(@PathVariable Integer id, @RequestBody Item item) {
		return itemManager.modify(id, item);
	}

	@DeleteMapping("/items/{id}")
	public boolean deleteItem(@PathVariable Integer id) {
		return itemManager.delete(id);
	}
}
