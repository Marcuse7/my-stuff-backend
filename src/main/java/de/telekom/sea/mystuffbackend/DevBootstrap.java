package de.telekom.sea.mystuffbackend;

import java.util.Date;

import javax.xml.bind.DatatypeConverter;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import de.telekom.sea.mystuffbackend.entity.Item;
import de.telekom.sea.mystuffbackend.repositories.ItemRepo;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

	private ItemRepo itemRepo;

	public DevBootstrap(ItemRepo itemRepo) {
		this.itemRepo = itemRepo;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		this.initData();
	}

	private void initData() {

		itemRepo.save(new Item("Broken vase", 1, "attic", "Ming Dynasty",
				toDate("2020-02-01T10:00:00Z")));

		itemRepo.save(new Item("Bobby Car", 1, "basement", "Unlimited mileage",
				toDate("2019-10-05T10:00:00Z")));

		itemRepo.save(new Item("Photo Box", 2, "attic", "Weird college stuff",
				toDate("2019-10-05T10:00:00Z")));

		itemRepo.save(new Item("Winter tyres", 4, "garage", "used on Nürburgring",
				toDate("2020-02-01T08:00:00Z")));

		itemRepo.save(new Item("Apple ][", 1, "attic", "gift from Steve",
				toDate("2010-08-16T08:00:00Z")));

	}

	private Date toDate(String expression) {
		return DatatypeConverter.parseDateTime(expression).getTime();
	}

}
