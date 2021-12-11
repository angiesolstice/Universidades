package com.ibm.academia.apirest.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.academia.apirest.entities.Aula;
import com.ibm.academia.apirest.entities.Pabellon;
import com.ibm.academia.apirest.exceptions.BadRequestException;
import com.ibm.academia.apirest.exceptions.NotFoundException;
import com.ibm.academia.apirest.services.AulaDAO;
import com.ibm.academia.apirest.services.PabellonDAO;
@RestController
@RequestMapping("/pabellon")
public class PabellonController 
{

	
	Logger logger = LoggerFactory.getLogger(PabellonController.class);
	@Autowired
	private PabellonDAO pabellonDao;
	
	@Autowired
	private AulaDAO aulaDao;
	/**
	 * Endpoint para buscar todos los pabellones
	 * @return lista de tipo Aula 
	 * @author ASA 10/12/2021
	 */
	@GetMapping("/lista/pabellones")
	public ResponseEntity<?> buscarTodas()
	{
		List<Pabellon> pabellones= (List<Pabellon>) pabellonDao.buscarTodos();
		if(pabellones.isEmpty())
			throw new NotFoundException("No existen pabellones");
		return new ResponseEntity<List<Pabellon>>(pabellones, HttpStatus.OK);
	
	}
	
	/**
	 * Endpoint para buscar un pabellon por su ID
	 * @param pabellonId como integer para el ID del Pabellon
	 * @return pabellon como tipo Pabellon de resultado
	 * @author ASA 10/12/2021
	 */
	@GetMapping("/id/{pabellonId}")
	public ResponseEntity<?> buscarPabellonPorId(@PathVariable Integer pabellonId)
	{
	
		Pabellon pabellon = pabellonDao.buscarPorId(pabellonId).orElse(null);
		if(pabellon == null)
			throw new BadRequestException(String.format("La aula con ID: %d no existe",pabellonId));
		
		return  new ResponseEntity<Pabellon>(pabellon, HttpStatus.OK) ;
	}
	
	/**
	 * Endoint para guardar un objeto de tipo Pabellon
	 * @param pabellon como un tipo Pabellon, a insertar
	 * @return pabellonGuardado como tipo pabellon
	 * @author ASA 10/12/2021
	 */
	@PostMapping
	public ResponseEntity<?> guardarPabellon(@Valid @RequestBody Pabellon pabellon, BindingResult result)
	{
		Map<String, Object> validaciones = new HashMap<String, Object>();
		if(result.hasErrors())
		{
			List<String> listaErrores = result.getFieldErrors()
					.stream()
					.map(errores -> "Campo: '" + errores.getField() + "' " + errores.getDefaultMessage())
					.collect(Collectors.toList());
			validaciones.put("Lista Errores", listaErrores);
			return new ResponseEntity<Map<String, Object>>(validaciones, HttpStatus.BAD_REQUEST);
		}
		
		Pabellon pabellonGuardado= pabellonDao.guardar(pabellon);
		
		return new ResponseEntity<Pabellon>(pabellonGuardado, HttpStatus.CREATED);
	}
	
	/**
	 * Endpoint para eliminar un pabellon por id
	 * @param pabellonId un integer para el ID
	 * @return mensaje que se ha eliminado el pabellon exitosamente
	 * @author ASA 10/12/2021
	 */
	@DeleteMapping("/pabellonId/{pabellonId}")
	public ResponseEntity<?> eliminarPabellon(@PathVariable Integer pabellonId)
	{
		Map<String, Object> respuesta = new HashMap<String, Object>();
		
		Optional<Pabellon> pabellon = pabellonDao.buscarPorId(pabellonId);
		
		if(!pabellon.isPresent())
			throw new NotFoundException(String.format("El pabellon con ID: %d no existe", pabellonId));
		
		pabellonDao.eliminarPorId(pabellonId);
		respuesta.put("OK", "Pabellon ID: " + pabellonId + " eliminado exitosamente");
		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.ACCEPTED);
	}
	
	/**
	 * Endpoint que se utiliza para actualizar un pabellon
	 * @param pabellonId como un integer para el ID
	 * @param pabellon un objeto de tipo Pabellon
	 * @return pabellon con información actualizada 
	 * @author ASA 10/12/2021
	 */
	@PutMapping("/upd/pabellon/{pabellonId}")
	public ResponseEntity<?> actualizarPabellon(@PathVariable Integer pabellonId, @RequestBody Pabellon pabellon)
	{
		Optional<Pabellon> oPabellon = pabellonDao.buscarPorId(pabellonId);
		
		if(!oPabellon.isPresent())
			throw new NotFoundException(String.format("El pabellon con ID: %d no existe", pabellonId));
		
		Pabellon pabellonActualizado = pabellonDao.actualizar(oPabellon.get(), pabellon); 
		
		return new ResponseEntity<Pabellon>(pabellonActualizado, HttpStatus.OK); 
	}
	
	/**
	 * Endpoint para asignar un pabellon a una aula
	 * @param pabellonId como un integer para el id del pabellon
	 * @param aulaId como un integer para el id de la aula
	 * @return aula como un ojeto Aula 
	 * @author ASA 10/12/2021
	 */
	@PutMapping("/pabellonId/{pabellonId}/aula/{aulaId}")
	public ResponseEntity<?> asignarAulaPabellon(@PathVariable Integer pabellonId, @PathVariable Integer aulaId)
	{
		Optional<Pabellon> oPabellon = pabellonDao.buscarPorId(pabellonId);
        if(!oPabellon.isPresent()) 
            throw new NotFoundException(String.format("Pabellon con id %d no existe", pabellonId));
        
        Optional<Aula> oAula =aulaDao.buscarPorId(aulaId);
        if(!oAula.isPresent())
            throw new NotFoundException(String.format("Aula con id %d no existe", aulaId));
        
        Pabellon pabellon = pabellonDao.asociarAulaPabellon(oPabellon.get(),oAula.get());
        
        return new ResponseEntity<Pabellon>(pabellon, HttpStatus.OK);
	}
}
