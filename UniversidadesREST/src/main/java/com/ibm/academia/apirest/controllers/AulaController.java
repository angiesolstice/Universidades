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
@RequestMapping("/aula")
public class AulaController 
{
	Logger logger = LoggerFactory.getLogger(AulaController.class);
	@Autowired
	private AulaDAO aulaDao;
	
	@Autowired
	private PabellonDAO pabellonDao;
	/**
	 * Endpoint que se utiliza para buscar todas las aulas
	 * @return Regresa una lista con las aulas
	 * @author ASA 10/12/2021
	 */
	@GetMapping("/lista/aulas")
	public ResponseEntity<?> buscarTodas()
	{
		List<Aula> aulas= (List<Aula>) aulaDao.buscarTodos();
		if(aulas.isEmpty())
			throw new NotFoundException("No existen aulas");
		return new ResponseEntity<List<Aula>>(aulas, HttpStatus.OK);
	
	}
	
	/**
	 * Endpoint utilizado para buscar una aula por ID
	 * @param aulaId  es un integer para el ID
	 * @return aula encontrada de tipo Aula 
	 * @author ASA 10/12/2021
	 */
	@GetMapping("/id/{aulaId}")
	public ResponseEntity<?> buscarAulaPorId(@PathVariable Integer aulaId)
	{
	
		Aula aula = aulaDao.buscarPorId(aulaId).orElse(null);
		if(aula == null)
			throw new BadRequestException(String.format("La aula con ID: %d no existe",aulaId));
		
		return  new ResponseEntity<Aula>(aula, HttpStatus.OK) ;
	}
	
	/**
	 * Endpoint para guardar un objeto de tipo Aula
	 * @param aula objeto de tipo Aula
	 * @return Retorna un objeto de tipo Aula que se guarda
	 * @author AngelicaSolisAvila 10/12/2021
	 */
	@PostMapping
	public ResponseEntity<?> guardarAula(@Valid @RequestBody Aula aula, BindingResult result)
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
		
		Aula aulaGuardada= aulaDao.guardar(aula);
		
		return new ResponseEntity<Aula>(aulaGuardada, HttpStatus.CREATED);
	}
	
	/**
	 * Endpoint para eliminar una aula por su id
	 * @param aulaId un integer para el ID
	 * @return mensaje que se ha eliminado exitosamente la aula
	 * @author ASA 10/12/2021
	 */
	@DeleteMapping("/aulaId/{aulaId}")
	public ResponseEntity<?> eliminarAula(@PathVariable Integer aulaId)
	{
		Map<String, Object> respuesta = new HashMap<String, Object>();
		
		Optional<Aula> aula = aulaDao.buscarPorId(aulaId);
		
		if(!aula.isPresent())
			throw new NotFoundException(String.format("La aula con ID: %d no existe", aulaId));
		
		aulaDao.eliminarPorId(aulaId);
		respuesta.put("OK", "Aula ID: " + aulaId + " eliminada exitosamente");
		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.ACCEPTED);
	}
	
	/**
	 * Endpoint para actualizar una aula
	 * @param aulaId como un integer para el ID
	 * @param aula un objeto de tipo Aula
	 * @return aula actualizada 
	 * @author AngelicaSolisAvila 10/12/2021
	 */
	@PutMapping("/upd/aula/{aulaId}")
	public ResponseEntity<?> actualizarAula(@PathVariable Integer aulaId, @RequestBody Aula aula)
	{
		Optional<Aula> oAula = aulaDao.buscarPorId(aulaId);
		
		if(!oAula.isPresent())
			throw new NotFoundException(String.format("La aula con ID: %d no existe", aulaId));
		
		Aula aulaActualizada = aulaDao.actualizar(oAula.get(), aula); 
		
		return new ResponseEntity<Aula>(aulaActualizada, HttpStatus.OK); 
	}
	
	/**
	 * Endpoint para asignar un pabellon a una aula
	 * @param pabellonId como un integer para el id del pabellon
	 * @param aulaId como un integer para el id de la aula
	 * @return aula como un bojeto Aula 
	 * @author ASA 10/12/2021
	 */
	@PutMapping("/aulaId/{aulaId}/pabellon/{pabellonId}")
	public ResponseEntity<?> asignarPabellonAula(@PathVariable Integer pabellonId, @PathVariable Integer aulaId)
	{
		Optional<Aula> oAula = aulaDao.buscarPorId(aulaId);
        if(!oAula.isPresent()) 
            throw new NotFoundException(String.format("Aula con id %d no existe", aulaId));
        
        Optional<Pabellon> oPabellon =pabellonDao.buscarPorId(pabellonId);
        if(!oPabellon.isPresent())
            throw new NotFoundException(String.format("Pabellon con id %d no existe", pabellonId));
        
        Aula aula = aulaDao.asociarPabellonAula(oAula.get(), oPabellon.get());
        
        return new ResponseEntity<Aula>(aula, HttpStatus.OK);
	}
}
