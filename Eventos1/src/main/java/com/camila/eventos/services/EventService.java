package com.camila.eventos.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.camila.eventos.models.EventModel;
import com.camila.eventos.models.MensajeModel;
import com.camila.eventos.models.User;
import com.camila.eventos.repositories.EventRepo;
import com.camila.eventos.repositories.MensajeRepo;

@Service
public class EventService {

	
	@Autowired
	private final EventRepo eventRepo;
	private final MensajeRepo mensajeRepo;
	public EventService(EventRepo eRe, MensajeRepo mRe) {
		this.eventRepo = eRe;
		this.mensajeRepo = mRe;
	}

	//CREAR EVENTO
		public EventModel crearEvento(EventModel evento) {
			return eventRepo.save(evento);
		}
	
	//EDITAR EVENTO
		public EventModel actualizarEvento(EventModel evento) {
			return eventRepo.save(evento);
		}
	//ELIMINAR EVENTO
		public void borrarEvento(Long id) {
			eventRepo.deleteById(id);
		}
		
		//UNIRSE O CANCELAR EVENTO
		
		public void unirseCancelarEvento(EventModel evento, User Usuario,  boolean asistencia ) {
			if(asistencia) {
				evento.getAsistentes().add(Usuario);
			}else {
				evento.getAsistentes().remove(Usuario);
			}
            eventRepo.save(evento);		
		}
		
		public List<EventModel> eventoProvinciaUsuario(String provincia){
			return eventRepo.findByProvincia(provincia);
		}
		
        public List<EventModel> eventoNoProvinciaUsuario(String provincia){
        	return eventRepo.findByProvinciaIsNot(provincia);
        	
        }
 
        public EventModel unEvento(Long id) {
        	return eventRepo.findById(id).orElse(null);
        }


       //CREAR UN MENSAJE
        
        public void agregarComentario(User usuario, EventModel evento, String comentario) {
        	MensajeModel mensaje = new MensajeModel(usuario, evento, comentario);
        	mensajeRepo.save(mensaje);
        }



}
