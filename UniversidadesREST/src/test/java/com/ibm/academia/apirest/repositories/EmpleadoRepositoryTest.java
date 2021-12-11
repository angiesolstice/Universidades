package com.ibm.academia.apirest.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import com.ibm.academia.apirest.data.DatosDummy;
import com.ibm.academia.apirest.entities.Persona;
import com.ibm.academia.apirest.enums.TipoEmpleado;
@DataJpaTest
public class EmpleadoRepositoryTest
{
	@Autowired
	@Qualifier("repositorioEmpleados")
	private PersonaRepository empleadoRepository;
	
	@BeforeEach
	void setUp()
	{
		//Given
		empleadoRepository.save(DatosDummy.empleado01());
		empleadoRepository.save(DatosDummy.empleado02());
		empleadoRepository.save(DatosDummy.empleado03());
		
	}
	
	@AfterEach
	void tearDown()
	{
		empleadoRepository.deleteAll();
	}
	
	@Test
	@DisplayName("Test: Buscar empleados por tipo")
	void findEmpleadosByTipoEmpleado()
	{
		//When
		TipoEmpleado tipoEmpleado= TipoEmpleado.ADMINISTRATIVO;
		List<Persona> expected=(List<Persona>)((EmpleadoRepository)empleadoRepository).findEmpleadosByTipoEmpleado(tipoEmpleado);
		
		//Then
		assertThat(expected.size() == 2).isTrue();
		
	}
}
