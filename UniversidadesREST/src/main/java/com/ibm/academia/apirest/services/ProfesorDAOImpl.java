package com.ibm.academia.apirest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.academia.apirest.entities.Persona;
import com.ibm.academia.apirest.repositories.ProfesorRepository;

@Service
public class ProfesorDAOImpl extends PersonaDAOImpl implements ProfesorDAO{

	@Autowired
	public ProfesorDAOImpl(@Qualifier("repositorioProfesores")ProfesorRepository repository) {
		super(repository);
		
	}

	@Override
	@Transactional(readOnly= true)
	public Iterable<Persona> findProfesoresByCarrera(String carrera) 
	{
		return ((ProfesorRepository)repository).findProfesoresByCarrera(carrera);
	}
	
	@Override
	@Transactional
	public Persona actualizar(Persona profesorEncontrado, Persona profesor) 
	{
		Persona profesorActualizado = null;
		profesorEncontrado.setNombre(profesor.getNombre());
		profesorEncontrado.setApellido(profesor.getApellido());
		profesorEncontrado.setDireccion(profesor.getDireccion());
		profesorActualizado = repository.save(profesorEncontrado);
		return profesorActualizado;
	}

}
