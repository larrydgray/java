package com.packt.cardatabase.domain;


import java.util.List;

import com.packt.cardatabase.entity.Car;
import org.springframework.data.repository.CrudRepository;

public interface CarRepository extends CrudRepository <Car, Long> {
	// Fetch cars by brand and sort by year
	List<Car> findByBrandOrderByYearAsc(String brand);
	
	List<Car> findByColor(String color);
	
	List<Car> findByYear(int year);
}
