package com.kaminari.WebThing;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Item")
public class Item {
	@Id private Integer id;
	private String name;
	private String brand;
	private String description;
	private Double cost;

	public Item() {
		this.id = -1;
		this.name = "";
		this.brand = "";
		this.description = "";
		this.cost = -0.1;
	}

	public Item(Integer id, String name, String brand, String description, Double cost) {
		this.id = id;
		this.name = name;
		this.brand = brand;
		this.description = description;
		this.cost = cost;
	}

	public Integer getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public String getBrand() {
		return this.brand;
	}

	public String getDescription() {
		return this.description;
	}

	public Double getCost() {
		return this.cost;
	}

	public void set(Item item) {
		if (item.id.equals(this.id)) { 
			this.name = item.name;
			this.brand = item.brand;
			this.description = item.description;
			this.cost = item.cost;
		}
	}
}
