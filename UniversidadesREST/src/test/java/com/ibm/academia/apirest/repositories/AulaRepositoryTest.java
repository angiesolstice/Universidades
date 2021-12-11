package com.ibm.academia.apirest.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import com.ibm.academia.apirest.data.DatosDummy;
import com.ibm.academia.apirest.entities.Aula;
import com.ibm.academia.apirest.entities.Pabellon;
import com.ibm.academia.apirest.enums.Pizarron;

@DataJpaTest
public class AulaRepositoryTest 
{

	   @Autowired
	   private AulaRepository aulaRepository;
	   
	   @Autowired
	   private PabellonRepository pabellonRepository;
	   
	   @BeforeEach
		void setUp()
		{
			//Given
			aulaRepository.save(DatosDummy.aula01());
			aulaRepository.save(DatosDummy.aula02());
			aulaRepository.save(DatosDummy.aula03());
			
			Pabellon pabellon= pabellonRepository.save(DatosDummy.pabellon01());
			List<Aula> aulas= (List<Aula>)aulaRepository.findAll();
			aulas.forEach(aula->aula.setPabellon(pabellon));
			aulaRepository.saveAll(aulas);
			
			
		}
		
		@AfterEach
		void tearDown()
		{
		   aulaRepository.deleteAll();
		}
		
		@Test
		@DisplayName("Test: Buscar aulas por tipo de pizarron")
		void buscarAulaPorTipoPizarron()
		{
			//When
		    Pizarron tipoPizarron = Pizarron.PIZARRA_TIZA;
			List<Aula> expected= (List<Aula>)aulaRepository.buscarAulaPorTipoPizarron(tipoPizarron);
			
			//Then
			assertThat((expected).size() == 2).isTrue();
		}
		
		@Test
		@DisplayName("Test: Buscar aulas por el nombre del pabellon")
		void buscarAulaPorNombrePabellon()
		{
			//When
			String aulaNombre = "Sistemas I";
			List<Aula> expected = (List<Aula>)aulaRepository.buscarAulaPorNombrePabellon(aulaNombre);
			
			//Then
			assertThat((expected).size() == 3).isTrue();
		}

		@Test
		@DisplayName("Test: Buscar aula por numero")	
		void buscarAulaPorNumero()
		{
			//When
			Optional<Aula> expected =aulaRepository.buscarAulaPorNumero(1);
			
			//Then
			assertThat(expected.isPresent()).isTrue();
			
		}
}
