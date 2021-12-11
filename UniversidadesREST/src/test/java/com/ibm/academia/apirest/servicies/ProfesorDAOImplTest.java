package com.ibm.academia.apirest.servicies;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Qualifier;
import com.ibm.academia.apirest.data.DatosDummy;
import com.ibm.academia.apirest.entities.Carrera;
import com.ibm.academia.apirest.entities.Persona;
import com.ibm.academia.apirest.entities.Profesor;
import com.ibm.academia.apirest.repositories.PersonaRepository;
import com.ibm.academia.apirest.repositories.ProfesorRepository;
import com.ibm.academia.apirest.services.PersonaDAO;
import com.ibm.academia.apirest.services.PersonaDAOImpl;
import com.ibm.academia.apirest.services.ProfesorDAO;

public class ProfesorDAOImplTest 
{

    PersonaDAO profesorDAO;
	
    @Qualifier("repositorioProfesores")
	PersonaRepository profesorRepository;
	
	@BeforeEach
    void setUp() 
    {
        profesorRepository = mock(PersonaRepository.class);
        profesorDAO= new PersonaDAOImpl(profesorRepository);
    }
	
	
	@Test
	@DisplayName("Test: Buscar profesores por nombre de carrera")
	void findProfesoresByCarrera()
	{
		//Given
		Set<Carrera> listaCarreras= new HashSet<>();
		String nombreCarrera="Ingenieria en Sistemas";
    	Profesor profesor1= (Profesor)DatosDummy.profesor01();
    	Profesor profesor2= (Profesor)DatosDummy.profesor02();
    	Carrera carrera= DatosDummy.carrera01();
    	listaCarreras.add(carrera);
    	profesor1.setCarreras(listaCarreras);
    	profesor2.setCarreras(listaCarreras);
    	when(((ProfesorRepository)profesorRepository).findProfesoresByCarrera(nombreCarrera))
    	.thenReturn(Arrays.asList(profesor1,profesor2));
    	
    	//When 
    	List<Persona> expected=(List<Persona>) ((ProfesorDAO)profesorDAO).findProfesoresByCarrera(nombreCarrera);
    	
    	//Then 
    	 assertThat(expected.get(0)).isEqualTo(profesor1);
         assertThat(expected.get(1)).isEqualTo(profesor2);
         verify((ProfesorRepository)profesorRepository).findProfesoresByCarrera(nombreCarrera);
	}
}
