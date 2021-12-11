package com.ibm.academia.apirest.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.ibm.academia.apirest.enums.Tipo_Pasion;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name="pasion")
public class Pasion implements Serializable
{
	@Column(name = "pasion")
	private Tipo_Pasion pasion;
	private static final long serialVersionUID = 999696065192207223L;

}
