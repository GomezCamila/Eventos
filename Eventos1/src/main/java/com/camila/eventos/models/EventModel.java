package com.camila.eventos.models;


import java.util.Date;

import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
@Entity
@Table(name = "eventos")
public class EventModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = " Por favor ingresa un nombre")
	@Size(min = 3, max = 30, message = "Nombre debe ser mayor a 3 caracteres y menor a 30")
	private String nombre;

	@Future(message = "Por favor ingresa una fecha posterior")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull(message = "Por favor ingresa una fecha")
	private Date fecha;

	@NotBlank(message = " Por favor ingresa una ubicacion")
	private String ubicacion;

	@NotBlank(message = " Por favor elige una provincia")
	private String provincia;

	@Column(updatable = false)
	private Date createdAt;
	@SuppressWarnings("unused")
	private Date updatedAt;

	// rELACION 1:N hacia Usuario
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User organizador;

	// Relacion n:n Eventos a Usuarios
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "asistentes", joinColumns = @JoinColumn(name = "event_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
	private List<User> asistentes;
	
	
	//Relacion n:n hacia mensajes
	@OneToMany(mappedBy="evento", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<MensajeModel> mensajes;

	
	
	public EventModel() {
		
	}
	
	
	
	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Date();
	}

	public List<User> getAsistentes() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
