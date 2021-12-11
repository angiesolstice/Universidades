package com.ibm.academia.apirest.servicies;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Qualifier;
import com.ibm.academia.apirest.data.DatosDummy;
import com.ibm.academia.apirest.entities.Alumno;
import com.ibm.academia.apirest.entities.Carrera;
import com.ibm.academia.apirest.entities.Persona;
import com.ibm.academia.apirest.repositories.AlumnoRepository;
import com.ibm.academia.apirest.repositories.PersonaRepository;
import com.ibm.academia.apirest.services.AlumnoDAO;
import com.ibm.academia.apirest.services.PersonaDAO;
import com.ibm.academia.apirest.services.PersonaDAOImpl;

public class AlumnoDAOImplTest 
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
	@DisplayName("Test: Buscar alumnos por nombre de la carrera")
	void buscarAlumnoPorNombreCarrera()
	{
		//Given
		String nombreCarrera="Ingenieria en Sistemas";
    	Alumno alumno1= (Alumno)DatosDummy.alumno01();
    	Alumno alumno2= (Alumno)DatosDummy.alumno02();
    	Carrera carrera= DatosDummy.carrera01();
    	alumno1.setCarrera(carrera);
    	alumno2.setCarrera(carrera);
    	when(((AlumnoRepository)alumnoRepository).buscarAlumnoPorNombreCarrera(nombreCarrera))
    	.thenReturn(Arrays.asList(alumno1,alumno2));
    	
    	//When 
    	List<Persona> expected=(List<Persona>) ((AlumnoDAO)alumnoDAO).buscarAlumnoPorNombreCarrera(nombreCarrera);
    	
    	//Then 
    	 assertThat((Alumno)expected.get(0)).isEqualTo(alumno1);
         assertThat((Alumno)expected.get(1)).isEqualTo(alumno2);
         verify((AlumnoRepository)alumnoRepository).buscarAlumnoPorNombreCarrera(nombreCarrera);
		
	}
}
