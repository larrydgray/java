package com.packt.cardatabase.domain;
import com.packt.cardatabase.entity.Owner;
import org.springframework.data.repository.CrudRepository;

public interface OwnerRepository extends CrudRepository<Owner, Long> {
	
}
