package com.camila.eventos.models;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;





@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "mensajes")
public class MensajeModel  {



	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Por favor ingresa un mensaje")
	@Column(columnDefinition = "text")
	private String comentario;

	@Column(updatable = false)
	private Date createdAt;
	private Date updatedAt;

	// Relaciones tabla intermedia
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User autor;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "event_id")
	private EventModel evento;

	
	
	public MensajeModel (User usuario, EventModel evento, String comentario) {
   this.autor=usuario;
   this.evento= evento;
   this.comentario=comentario;
	}

	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Date();
	}

	

}
