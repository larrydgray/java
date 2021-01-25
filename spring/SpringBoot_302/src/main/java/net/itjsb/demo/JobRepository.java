package net.itjsb.demo;

import org.springframework.data.repository.CrudRepository;

//The Job Repository has in-built methods that you can use to save,
// locate, and delete data.
public interface JobRepository extends CrudRepository<Job, Long> {
}
