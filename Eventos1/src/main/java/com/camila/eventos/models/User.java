package com.camila.eventos.models;

import java.util.Date;
import java.util.List;

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
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message=" Por favor ingresa un nombre")
	@Size(min=3, max=30, message="Nombre debe ser mayor a 3 caracteres y menor a 30")
	private String nombre;
	
	@NotBlank(message=" Por favor ingresa un apellido")
	@Size(min=3, max=30, message="Apellido debe ser mayor a 3 caracteres y menor a 30")
	private String apellido;
	
	@NotBlank(message=" Por favor ingresa un correo electronico")
	@Email(message="El correo ingresado no es correcto")
	private String email;
	
	@NotBlank(message=" Por favor ingresa una ubicacion")
	@Size(min=3, max=30, message="Ubicacion debe ser mayor a 3 caracteres y menor a 30")
	private String ubicacion;
	
	@NotBlank(message=" Por favor selecciona una provincia")
	private String provincia;
	
	@NotBlank(message="Por favor, ingresa el password")
	@Size(min=8, max=64, message= "Password debe contener minimo 8 caracteres")
	private String password;
	
	@Transient
	@NotBlank(message="Por favor confirma la contrasenia")
	@Size(min=8, message= "Password debe contener minimo 8 caracteres")
	private String passwordConfirmation;
	
	@Column(updatable = false)
	private Date createdAt;
	private Date updatedAt;
	
	//Relacion 1:n hacia Eventos
	@OneToMany(mappedBy="organizador", fetch=FetchType.LAZY)
	private List<EventModel> eventosOrganizados;
	
	//Relacion muchos a muchos de Usuario a Eventos
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="asistentes",
	joinColumns= @JoinColumn(name="user_id"),
	inverseJoinColumns = @JoinColumn(name="event_id"))
	private List<EventModel> eventoAsistir;
	
	
	//Relacion n:n hacia mensajes
		@OneToMany(mappedBy="autor", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
		private List<MensajeModel> mensajes;
	
		// otros getters y setters removidos para resumir.
		@PrePersist
		protected void onCreate() {
			this.createdAt = new Date();
		}

		@PreUpdate
		protected void onUpdate() {
			this.updatedAt = new Date();
		}

		public User() {
			// TODO Auto-generated constructor stub
		}

}
