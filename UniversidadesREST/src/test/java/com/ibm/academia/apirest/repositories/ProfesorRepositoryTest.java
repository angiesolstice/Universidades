package com.ibm.academia.apirest.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.ibm.academia.apirest.data.DatosDummy;
import com.ibm.academia.apirest.entities.Carrera;
import com.ibm.academia.apirest.entities.Persona;
import com.ibm.academia.apirest.entities.Profesor;

@DataJpaTest
public class ProfesorRepositoryTest
{

	@Autowired
	@Qualifier("repositorioProfesores")
	private PersonaRepository profesorRepository;
	
	@Autowired
	private CarreraRepository carreraRepository;
	
    @BeforeEach
	void setUp()
	{
		//Given
		profesorRepository.save(DatosDummy.profesor01());
		profesorRepository.save(DatosDummy.profesor02());
		profesorRepository.save(DatosDummy.profesor03());
		
		Set<Carrera> listaCarreras= new HashSet<>();
		Carrera carrera01=carreraRepository.save(DatosDummy.carrera01());
		Carrera carrera02=carreraRepository.save(DatosDummy.carrera02());
		listaCarreras.add(carrera01);
		listaCarreras.add(carrera02);
		
		List<Persona> profesores= ((ProfesorRepository)profesorRepository).findAll();
		profesores.forEach(profesor->((Profesor)profesor).setCarreras(listaCarreras));
	}
	
	@AfterEach
	void tearDown()
	{
		profesorRepository.deleteAll();
	}
	
	@Test
	@DisplayName("Test: Buscar profesores por nombre de la carrera")
	void findProfesoresByCarrera()
	{
		//When
		String carreraNombre="Ingenieria en Sistemas";
		List<Persona> expected=(List<Persona>)((ProfesorRepository)profesorRepository).findProfesoresByCarrera(carreraNombre);
		
		//Then
		assertThat(expected.size() == 3).isTrue();
	}
}
