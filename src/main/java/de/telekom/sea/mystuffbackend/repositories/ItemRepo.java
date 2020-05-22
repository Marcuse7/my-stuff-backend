package de.telekom.sea.mystuffbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.telekom.sea.mystuffbackend.entity.Item;

@Repository
public interface ItemRepo extends JpaRepository<Item, Long> {

}
