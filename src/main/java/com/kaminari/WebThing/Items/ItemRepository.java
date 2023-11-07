package com.kaminari.WebThing.Items;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.kaminari.WebThing.Items.Item;

@Repository
public interface ItemRepository extends CrudRepository<Item, Integer> {

}
