package de.telekom.sea.mystuffbackend.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Item {

	@Id
	@GeneratedValue
	private Long id;

	private String name;

	private int amount;

	private String location;

	private String description;

	private Date lastUsed;

	public Item() {
	}

	public Item(String name, int amount, String location, String description, Date lastUsed) {
		this.name = name;
		this.amount = amount;
		this.location = location;
		this.description = description;
		this.lastUsed = lastUsed;
	}

}
