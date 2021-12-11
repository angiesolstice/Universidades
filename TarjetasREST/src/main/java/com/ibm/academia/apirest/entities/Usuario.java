package com.ibm.academia.apirest.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.ibm.academia.apirest.enums.Tipo_Pasion;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@ToString
@Entity
@Table(name="usuarios")

@Inheritance(strategy = InheritanceType.JOINED)
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "tipo" 
)

public class Usuario implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull(message = "No puede ser nulo")
	@NotEmpty(message = "No puede ser vacio")
	@Column(name = "edad", nullable = false, length = 3)
	private Edad edad;
	
	@NotNull(message = "No puede ser nulo")
	@NotEmpty(message = "No puede ser vacio")
	@Column(name = "tipo_pasion")
	@Enumerated(EnumType.STRING)
	private Tipo_Pasion tipo_pasion;
	
	@NotNull(message = "No puede ser nulo")
	@NotEmpty(message = "No puede ser vacio")
	@Column(name = "salario")
	private BigDecimal salario;
	
	
	@Column(name = "tarjeta")
	@Enumerated(EnumType.STRING)
	private Tarjeta tarjeta;
	
	@Column(name = "fecha_alta")
	private Date fechaAlta;
	
	@Column(name = "fecha_modificacion")
	private Date fechaModificacion;
	
	
	
	public Usuario(Integer id, Edad edad, Tipo_Pasion tipo_pasion, BigDecimal salario, Tarjeta tarjeta) {
		super();
		this.id = id;
		this.edad = edad;
		this.tipo_pasion = tipo_pasion;
		this.salario = salario;
		this.tarjeta = tarjeta;
	}



	@Override
	public int hashCode() {
		return Objects.hash(id);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(id, other.id);
	}


	@PrePersist
	private void antesPersistir()
	{
		this.fechaAlta = new Date();
	}
	
	@PreUpdate
	private void antesActualizar()
	{
		this.fechaModificacion = new Date();
	}
	private static final long serialVersionUID = 1796061307897537073L;

}
