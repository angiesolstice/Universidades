package com.ibm.academia.apirest.repositories;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import com.ibm.academia.apirest.data.DatosDummy;
import com.ibm.academia.apirest.entities.Pabellon;


@DataJpaTest
public class PabellonRepositoryTest 
{
   @Autowired
   private PabellonRepository pabellonRepository;
   
   @BeforeEach
	void setUp()
	{
		//Given
		pabellonRepository.save(DatosDummy.pabellon01());
		pabellonRepository.save(DatosDummy.pabellon02());
		pabellonRepository.save(DatosDummy.pabellon03());
	}
	
	@AfterEach
	void tearDown()
	{
		pabellonRepository.deleteAll();
	}
	
	@Test
	@DisplayName("Test: Buscar pabellon por localidad")
	void buscarPabellonPorLocalidad()
	{
	    //When
		String localidad="Zacatenco";
		List<Pabellon> expected= (List<Pabellon>)pabellonRepository.buscarPabellonPorLocalidad(localidad);
		
		//Then
		assertThat(expected.size() == 2).isTrue();
	}
	
	@Test
	@DisplayName("Test: Buscar pabellon por nombre")
	void  buscarPabellonPorNombre()
	{
		//When
		String nombre="Sistemas I";
		List<Pabellon> expected= (List<Pabellon>)pabellonRepository.buscarPabellonPorNombre(nombre);
		
		//Then
		assertThat(expected.size() == 1).isTrue();
		
		
	}
}
