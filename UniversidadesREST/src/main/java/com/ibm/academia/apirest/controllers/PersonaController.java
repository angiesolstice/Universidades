package com.ibm.academia.apirest.controllers;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ibm.academia.apirest.exceptions.NotFoundException;
import com.ibm.academia.apirest.entities.Persona;
import com.ibm.academia.apirest.services.PersonaDAO;

@RestController
@RequestMapping("/persona")
public class PersonaController 
{
	Logger logger = LoggerFactory.getLogger(PersonaController.class);
	@Autowired
	@Qualifier("alumnoDAOImpl")
	private PersonaDAO personaDao;
	
	/**
	 * Endpoint para buscar un persona por nombre y apellido
	 * @param nombre de tipo String  
	 * @param apellido de tipo String
	 * @return oPersona de objeto de tipo Persona obtenida por nombre y apellido
	 * @author ASA 10/12/2021
	 */
	@GetMapping("/nombre-apellido")
    public ResponseEntity<?> buscarPersonaPorNombreYApellido(@RequestParam String nombre, @RequestParam String apellido)
	{
        Optional<Persona> oPersona = personaDao.buscarPorNombreYApellido(nombre, apellido);
        
        if(!oPersona.isPresent()) 
            throw new NotFoundException(String.format("No se encontro Persona con nombre %s y apellido %s", nombre, apellido));
        
        return new ResponseEntity<Persona>(oPersona.get(), HttpStatus.OK);
    } 
	
	/**
	 * Endpoint para buscar una persona por su DNI
	 * @param dni de tipo String 
	 * @return oPersona como tipo Persona
	 * @author ASA 10/12/2021
	 */
	@GetMapping("/dni/{dni}")
    public ResponseEntity<?> buscarPersonaPorDni(@PathVariable  String dni)
    {
        Optional<Persona> oPersona = personaDao.buscarPorDni(dni);
        if(!oPersona.isPresent()) 
            throw new NotFoundException(String.format("No se encontro Persona con el DNI %s",dni));
        
        return new ResponseEntity<Persona>(oPersona.get(), HttpStatus.OK);
    }
	
	/**
	 * Endpoint para buscar personas por sus apellidos
	 * @param apellido como String  
	 * @return Lista de tipo de Persona
	 * @author ASA 10/12/2021
	 */
	@GetMapping("/apellido/{apellido}")
    public ResponseEntity<?> buscarPersonaPorApellido(@PathVariable  String apellido)
    {
        List<Persona> personas = (List<Persona>) personaDao.buscarPersonaPorApellido(apellido);
        if(personas.isEmpty()) 
            throw new NotFoundException(String.format("No se encontraron personas con el apellido %s",apellido));
        
        return new ResponseEntity<List<Persona>>(personas, HttpStatus.OK);
    }

}
