package de.telekom.sea.mystuffbackend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import de.telekom.sea.mystuffbackend.entity.Item;
import de.telekom.sea.mystuffbackend.repositories.ItemRepo;

@RequestMapping("/api/v1/items")
@RestController
public class ItemRestController {

	private final ItemRepo repo;

	@Autowired
	private ItemRestController(ItemRepo repo) {
		super();
		this.repo = repo;
	}

	@GetMapping
	public List<Item> getAll() {
		return this.repo.findAll();
	}

	@GetMapping("{id}")
	public ResponseEntity<?> getById(@PathVariable("id") Long id) {
		Optional<Item> item = this.repo.findById(id);
		if (item.isPresent())
			return new ResponseEntity<Item>(item.get(), HttpStatus.OK);
		else
			return new ResponseEntity<String>("item with id " + id + " could not be found",
					HttpStatus.NOT_FOUND);
	}

	@PostMapping
	public ResponseEntity<?> createItem(@RequestBody Item newItem) {
		try {
			this.repo.save(newItem);
			return new ResponseEntity<Item>(newItem, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<String>("could not create item", HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("{id}")
	public ResponseEntity<?> updateItem(@PathVariable("id") Long id, @RequestBody Item newItem) {
		Optional<Item> oldItem = this.repo.findById(id);
		if (oldItem.isPresent()) {
			Item item = oldItem.get();
			item.setAmount(newItem.getAmount());
			item.setDescription(newItem.getDescription());
			item.setLastUsed(newItem.getLastUsed());
			item.setLocation(newItem.getLocation());
			item.setName(newItem.getName());
			return new ResponseEntity<Item>(this.repo.save(item), HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("item with id " + id + " could not be updated",
					HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "{id}")
	ResponseEntity<?> deleteItem(@PathVariable Long id) {
		try {
			this.repo.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<String>("could not delete item with id " + id,
					HttpStatus.NOT_FOUND);
		}
	}

}
