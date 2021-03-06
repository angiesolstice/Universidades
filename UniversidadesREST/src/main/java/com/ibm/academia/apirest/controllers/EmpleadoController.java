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
import com.ibm.academia.apirest.entities.Pabellon;
import com.ibm.academia.apirest.entities.Persona;
import com.ibm.academia.apirest.exceptions.NotFoundException;
import com.ibm.academia.apirest.services.EmpleadoDAO;
import com.ibm.academia.apirest.services.PabellonDAO;
import com.ibm.academia.apirest.services.PersonaDAO;

@RestController
@RequestMapping("/empleado")
public class EmpleadoController
{

	Logger logger = LoggerFactory.getLogger(EmpleadoController.class);
	@Autowired
	@Qualifier("empleadoDAOImpl")
	private PersonaDAO empleadoDao;
	
	@Autowired
	private PabellonDAO pabellonDao;
	
	
	/**
	 * Endpoint para crear un objeto Persona de tipo empleado
	 * @param empleado Objeto Empleado, con inforamción para crear
	 * @return Retorna un objeto Persona de tipo empleado con  el código HTTPSTATUS 201
	 * @author ASA 10/12/2021
	 */
	@PostMapping
	public ResponseEntity<?> crearEmpleado(@RequestBody Persona empleado)
	{
		Persona empleadoGuardado = empleadoDao.guardar(empleado);
		return new ResponseEntity<Persona>(empleadoGuardado, HttpStatus.CREATED);
	}
	
	/**
	 * Endpoint para hacer una lista de empleados
	 * @return lista de tipo Persona en la que se encuentran los empleados
	 * @author ASA 10/12/2021
	 */
	@GetMapping("/empleados/lista")
	public ResponseEntity<?> obtenerTodos()
	{
		List<Persona> empelados = (List<Persona>) empleadoDao.buscarTodos();
		
		if(empelados.isEmpty())
			throw new NotFoundException("No existen empleados");
		return new ResponseEntity<List<Persona>>(empelados, HttpStatus.OK);
	}
	
	/**
	 * Endpoint para obtener un empleado por su ID
	 * @param empleadoId es integer del ID
	 * @return oEmpelado es objeto Empleado
	 * @author ASA 10/12/2021
	 */
	@GetMapping("/empleadoId/{empleadoId}")
    public ResponseEntity<?> obtenerAlumnoPorId(@PathVariable Integer empleadoId)
    {
        Optional<Persona> oEmpleado = empleadoDao.buscarPorId(empleadoId);
        
        if(!oEmpleado.isPresent()) 
            throw new NotFoundException(String.format("Empleado con id %d no existe", empleadoId));
        
        return new ResponseEntity<Persona>(oEmpleado.get(), HttpStatus.OK);
    }
	
	/**
	 * Endpoint  para actualizar la información del empleado
	 * @param empleadoId como un integer del id del empleado
	 * @param empleado como un objeto de tipo Empleado
	 * @return empleadoActualizado como un objeto de tipo Persona, con los datos actualizados
	 * @author ASA 10/12/2021
	 */
	@PutMapping("/upd/empleadoId/{empleadoId}")
	public ResponseEntity<?> actualizarAlumno(@PathVariable Integer empleadoId, @RequestBody Persona empleado)
	{
		Optional<Persona> oEmpleado = empleadoDao.buscarPorId(empleadoId);
		
		if(!oEmpleado.isPresent())
			throw new NotFoundException(String.format("El alumno con ID %d no existe", empleadoId));
		
		Persona empleadoActualizado = ((EmpleadoDAO)empleadoDao).actualizar(oEmpleado.get(), empleado);
		return new ResponseEntity<Persona>(empleadoActualizado, HttpStatus.OK);
	}
	
	/**
	 * Endpoint para eliminar un empleado por su ID
	 * @param empleadoId como un integer para el ID
	 * @return Mensaje de confirmación de eliminación
	 * @author ASA 10/12/2021
	 */
	@DeleteMapping("/empleadoId/{empeladoId}")
	public ResponseEntity<?> eliminarEmpleado(@PathVariable Integer empleadoId)
	{
		Optional<Persona> oEmpleado= empleadoDao.buscarPorId(empleadoId);
		
		if(!oEmpleado.isPresent())
			throw new NotFoundException(String.format("El empleado con ID %d no existe", empleadoId));
		
		empleadoDao.eliminarPorId(oEmpleado.get().getId()); 
		
		return new ResponseEntity<String>("Empleado ID: " + empleadoId + " se elimino satisfactoriamente",  HttpStatus.NO_CONTENT);
	}
	
	/**
	 * Endpoint que sirve para asignar un pabellon a un empleado
	 * @param empleadoId como un integer para el id del empleado
	 * @param pabellonId como un integer para el id del pabellon
	 * @return empleado como un objeto tipo Persona 
	 * @author ASA 10/12/2021
	 */
	@PutMapping("/empleadoId/{empleadoId}/pabellon/{pabellonId}")
	public ResponseEntity<?> asociarPabellonEmpleado(@PathVariable Integer empleadoId, @PathVariable Integer pabellonId)
	{
		Optional<Persona> oEmpleado = empleadoDao.buscarPorId(empleadoId);
        if(!oEmpleado.isPresent()) 
            throw new NotFoundException(String.format("Empleado con id %d no existe", empleadoId));
        
        Optional<Pabellon> oPabellon =  pabellonDao.buscarPorId(pabellonId);
        if(!oPabellon.isPresent())
            throw new NotFoundException(String.format("Pabellon con id %d no existe", pabellonId));
        
        Persona empleado = ((EmpleadoDAO)empleadoDao).asociarPabellonEmpleado(oEmpleado.get(),oPabellon.get());
        
        return new ResponseEntity<Persona>(empleado, HttpStatus.OK);
	}
	
}
