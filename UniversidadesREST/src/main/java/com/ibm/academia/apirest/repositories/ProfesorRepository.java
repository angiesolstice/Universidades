package com.ibm.academia.apirest.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ibm.academia.apirest.entities.Persona;


@Repository("repositorioProfesores")
public interface ProfesorRepository extends PersonaRepository
{
	@Query(value="SELECT * FROM universidad.Personas inner join universidad.profesor_carrera on personas.id=profesor_carrera.profesor_id inner join universidad.carreras on carreras.id=profesor_carrera.carrera_id where carreras.nombre= ?1",nativeQuery=true)
	public Iterable<Persona> findProfesoresByCarrera(String carrera);
}
