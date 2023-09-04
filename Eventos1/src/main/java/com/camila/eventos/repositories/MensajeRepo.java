package com.camila.eventos.repositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.camila.eventos.models.MensajeModel;

@Repository
public interface MensajeRepo extends CrudRepository<MensajeModel, Long> {

}
