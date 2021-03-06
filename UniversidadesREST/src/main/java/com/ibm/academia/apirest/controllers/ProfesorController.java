package com.ibm.academia.apirest.controllers;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ibm.academia.apirest.entities.Persona;
import com.ibm.academia.apirest.exceptions.NotFoundException;
import com.ibm.academia.apirest.services.PersonaDAO;
import com.ibm.academia.apirest.services.ProfesorDAO;
@RestController
@RequestMapping("/profesor")
public class ProfesorController 
{

	Logger logger = LoggerFactory.getLogger(ProfesorController.class);
	@Autowired
	@Qualifier("profesorDAOImpl")
	private PersonaDAO profesorDao;
	
	
	
	/**
	 * Endpoint para crear un objeto Persona de tipo profesor
	 * @param empleado Objeto Profesor con la inforamción para crearlo
	 * @return Retorna un objeto Persona de tipo profesor con HTTPSTATUS 201
	 * @author ASA 10/12/2021
	 */
	@PostMapping
	public ResponseEntity<?> crearProfesor(@RequestBody Persona profesor)
	{
		Persona profesorGuardado = profesorDao.guardar(profesor);
		return new ResponseEntity<Persona>(profesorGuardado, HttpStatus.CREATED);
	}
	
	/**
	 * Endpoint para listar los profesores
	 * @return lista de tipo Persona para los profesores
	 * @author ASA 10/12/2021
	 */
	@GetMapping("/profesor/lista")
	public ResponseEntity<?> obtenerTodos()
	{
		List<Persona> profesores = (List<Persona>) profesorDao.buscarTodos();
		
		if(profesores.isEmpty())
			throw new NotFoundException("No existen profesores");
		return new ResponseEntity<List<Persona>>(profesores, HttpStatus.OK);
	}
	
	/**
	 * Endpoint para obtener un profesor por su ID
	 * @param profesorId como integer del ID del profesor
	 * @return oProfesor como un objeto de tipo Profesor
	 * @author ASA 10/12/2021
	 */
	@GetMapping("/profesorId/{profesorId}")
    public ResponseEntity<?> obtenerProfesorPorId(@PathVariable Integer profesorId)
    {
        Optional<Persona> oProfesor = profesorDao.buscarPorId(profesorId);
        
        if(!oProfesor.isPresent()) 
            throw new NotFoundException(String.format("Profesor con id %d no existe", profesorId));
        
        return new ResponseEntity<Persona>(oProfesor.get(), HttpStatus.OK);
    }
	
	/**
	 * Endpoint  para actualizar la información del profesor
	 * @param profesorId como integer del id del profesor
	 * @param profesor como objeto de tipo Profesor
	 * @return profesorActualizado como un objeto de tipo Persona con informacion actualizada 
	 * @author @author ASA 10/12/2021
	 */
	@PutMapping("/upd/profesorId/{profesorId}")
	public ResponseEntity<?> actualizarProfesor(@PathVariable Integer profesorId, @RequestBody Persona profesor)
	{
		Optional<Persona> oProfesor = profesorDao.buscarPorId(profesorId);
		
		if(!oProfesor.isPresent())
			throw new NotFoundException(String.format("El profesor con ID %d no existe", profesorId));
		
		Persona profesorActualizado = ((ProfesorDAO)profesorDao).actualizar(oProfesor.get(), profesor);
		return new ResponseEntity<Persona>(profesorActualizado, HttpStatus.OK);
	}
	
	/**
	 * Endpoint para eliminar un profesor por ID
	 * @param profesorId como integer para el ID
	 * @return mensage para confirmar que se elimino
	 * @author ASA 10/12/2021
	 */
	@DeleteMapping("/profesorId/{profesorId}")
	public ResponseEntity<?> eliminarEmpleado(@PathVariable Integer profesorId)
	{
		Optional<Persona> oProfesor= profesorDao.buscarPorId(profesorId);
		
		if(!oProfesor.isPresent())
			throw new NotFoundException(String.format("El profesor con ID %d no existe", profesorId));
		
		profesorDao.eliminarPorId(oProfesor.get().getId()); 
		
		return new ResponseEntity<String>("Profesor ID: " + profesorId + " se elimino satisfactoriamente",  HttpStatus.NO_CONTENT);
	}
	
	
	
}
