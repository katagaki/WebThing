package com.kaminari.WebThing;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

@Service
public class ItemService {
	@Autowired private ItemRepository repository;

	@CacheEvict(value = "allItems", allEntries = true)
	public boolean insert(Item item) {
		boolean itemIDAlreadyExists = false;
		for (Item existingItem : repository.findAll()) {
			if (existingItem.getId().equals(item.getId())) {
				itemIDAlreadyExists = true;
			}
		}
		if (!itemIDAlreadyExists) {
			repository.save(item);
			return true;
		}
		return false;
	}

	@Cacheable("allItems")
	public List<Item> allItems() {
		List<Item> items = new ArrayList<Item>();
		repository.findAll().forEach(items::add);
		return items;
	}

	@Cacheable(value = "item", key = "#id")
	public Item item(Integer id) {
		for (Item item : repository.findAll()) {
			if (item.getId().equals(id)) {
				return item;
			}
		}
		return null;
	}

	@Cacheable(value = "itemsForBrand", key = "#brand")
	public List<Item> itemsForBrand(String brand) {
		List<Item> itemsFound = new ArrayList<Item>();
		for (Item item : repository.findAll()) {
			if (item.getBrand().equalsIgnoreCase(brand)) {
				itemsFound.add(item);
			}
		}
		return itemsFound;
	}

	@Caching(evict = {
			@CacheEvict(value = "allItems", allEntries = true),
			@CacheEvict(value = "item", key = "#id")
	})
	public boolean modify(Integer id, Item item) {
		Optional<Item> itemToModify = repository.findById(id);
		if (item.getId().equals(id) && itemToModify != null) {
			repository.save(item);
			return true;
		}
		return false;
	}

	@Caching(evict = {
			@CacheEvict(value = "allItems", allEntries = true),
			@CacheEvict(value = "item", key = "#id")
	})
	public boolean delete(Integer id) {
		repository.deleteById(id);
		return true;
	}
}
