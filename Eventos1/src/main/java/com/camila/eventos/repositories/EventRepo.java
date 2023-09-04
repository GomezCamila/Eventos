package com.camila.eventos.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.camila.eventos.models.EventModel;

@Repository
public interface EventRepo extends CrudRepository<EventModel, Long> {

	List<EventModel> findByProvincia(String provincia);
	
	Optional<EventModel> findById(Long id);
	List<EventModel> findByProvinciaIsNot(String provincia);
	
	
}
