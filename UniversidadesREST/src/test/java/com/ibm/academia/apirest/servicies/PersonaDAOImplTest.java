package com.ibm.academia.apirest.servicies;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Qualifier;
import com.ibm.academia.apirest.data.DatosDummy;
import com.ibm.academia.apirest.entities.Persona;
import com.ibm.academia.apirest.repositories.PersonaRepository;
import com.ibm.academia.apirest.services.PersonaDAO;
import com.ibm.academia.apirest.services.PersonaDAOImpl;

public class PersonaDAOImplTest
{

	PersonaDAO alumnoDAO;
	
    @Qualifier("repositorioAlumnos")
	PersonaRepository alumnoRepository;
    
    @BeforeEach
    void setUp() 
    {
        alumnoRepository = mock(PersonaRepository.class);
        alumnoDAO= new PersonaDAOImpl(alumnoRepository);
    }
	
	@Test
	@DisplayName("Test: Buscar persona por nombre y apellido")
    void buscarPorNombreYApellido()
    {
		//Given
		String nombre="Andres";
		String apellido="Benitez";
    	when(alumnoRepository.buscarPorNombreYApellido(nombre, apellido))
    	.thenReturn(Optional.of(DatosDummy.alumno02()));
    	
    	//When 
    	Optional<Persona> expected=alumnoDAO.buscarPorNombreYApellido(nombre, apellido);
    	
    	//Then 
    	assertThat(expected.get()).isEqualTo(DatosDummy.alumno02());
        
        verify(alumnoRepository).buscarPorNombreYApellido(nombre, apellido);
    }

	@Test
	@DisplayName("Test: Buscar persona por DNI")
	void buscarPorDni()
	{
		//Given
		String dni="45233715";
    	when(alumnoRepository.buscarPorDni(dni))
    	.thenReturn(Optional.of(DatosDummy.alumno01()));
    	
    	//When 
    	Optional<Persona> expected=alumnoDAO.buscarPorDni(dni);
    	
    	//Then 
    	 assertThat(expected.get()).isEqualTo(DatosDummy.alumno01());
        
         verify(alumnoRepository).buscarPorDni(dni);
	}

	@Test
	@DisplayName("Test: Buscar persona por apellido")
	void buscarPersonaPorApellido()
	{
		//Given
		String apellido="Benitez";
    	when(alumnoRepository.buscarPersonaPorApellido(apellido))
    	.thenReturn(Arrays.asList(DatosDummy.alumno01(),DatosDummy.alumno02()));
    	
    	//When 
    	List<Persona> expected=(List<Persona>) alumnoDAO.buscarPersonaPorApellido(apellido);
    	
    	//Then 
    	assertThat(expected.get(0)).isEqualTo(DatosDummy.alumno01());
    	assertThat(expected.get(1)).isEqualTo(DatosDummy.alumno02());
        verify(alumnoRepository).buscarPersonaPorApellido(apellido);
	}
}
