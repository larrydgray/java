package com.packt.cardatabase;

import java.util.Arrays;
import java.util.List;

import com.packt.cardatabase.domain.CarRepository;
import com.packt.cardatabase.domain.OwnerRepository;
import com.packt.cardatabase.domain.UserRepository;
import com.packt.cardatabase.entity.Car;
import com.packt.cardatabase.entity.Owner;
import com.packt.cardatabase.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;





@SpringBootApplication
public class CardatabaseApplication {
	
	
	private static final Logger logger = LoggerFactory.getLogger(CardatabaseApplication.class);

	@Autowired
	private UserRepository urepository;

	@Autowired
	private CarRepository repository;

	@Autowired	
	private OwnerRepository orepository;

	public static void main(String[] args) {
		SpringApplication.run(CardatabaseApplication.class, args);
		logger.info("Hello Spring Boot");
		
	}
	@Bean
	CommandLineRunner runner() {
		return args -> {
			
			Owner owner1 = new Owner("John" , "Johnson");
			Owner owner2 = new Owner("Mary" , "Robinson");
			
			orepository.save(owner1);
			orepository.save(owner2);

			repository.save(new Car("Ford", "Mustang", "Red", "ADF-1121", 2017, 59000, owner1));
			repository.save(new Car("Nissan", "Leaf", "White", "SSJ-3002", 2014, 29000, owner2));
			repository.save(new Car("Toyota", "Prius", "Silver", "KKO-0212", 2018, 39000, owner2));

			/*
			List cars = repository.findByBrandOrderByYearAsc("Ford");
			cars.forEach(car -> System.out.println(car.toString()));
			cars = repository.findByColor("White");
			cars.forEach(car -> System.out.println(car.toString()));
			cars = repository.findByYear(2018);
			cars.forEach(car -> System.out.println(car.toString()));
			*/

			urepository.save(new User("user", "userpassword", "USER"));
			urepository.save(new User("admin", "adminpassword", "ADMIN"));

		};
	}	
}
