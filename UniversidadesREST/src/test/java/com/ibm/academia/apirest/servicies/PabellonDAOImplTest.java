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
import com.ibm.academia.apirest.data.DatosDummy;
import com.ibm.academia.apirest.entities.Pabellon;
import com.ibm.academia.apirest.repositories.PabellonRepository;
import com.ibm.academia.apirest.services.PabellonDAO;
import com.ibm.academia.apirest.services.PabellonDAOImpl;

public class PabellonDAOImplTest
{
	PabellonDAO pabellonDAO;
	
	PabellonRepository pabellonRepository;
	
	@BeforeEach
    void setUp() 
    {
        pabellonRepository = mock(PabellonRepository.class);
        pabellonDAO= new PabellonDAOImpl(pabellonRepository);
    }
	
	@Test
	@DisplayName("Test: Buscar pabellon por localidad")
	void buscarPabellonPorLocalidad()
	{
		//Given
    	String localidad="Zacatenco";
    	when(pabellonRepository.buscarPabellonPorLocalidad(localidad))
    	.thenReturn(Arrays.asList(DatosDummy.pabellon02(),DatosDummy.pabellon03()));
    	
    	//When 
    	List<Pabellon> expected=(List<Pabellon>)pabellonDAO.buscarPabellonPorLocalidad(localidad);
    	
    	//Then 
    	 assertThat(expected.get(0)).isEqualTo(DatosDummy.pabellon02());
         assertThat(expected.get(1)).isEqualTo(DatosDummy.pabellon03());
         verify(pabellonRepository).buscarPabellonPorLocalidad(localidad);
	}
	
	@Test
	@DisplayName("Test: Buscar pabellon por nombre")
	void buscarPabellonPorNombre()
	{
		//Given
    	String nombre="Sistemas I";
    	when(pabellonRepository.buscarPabellonPorNombre(nombre))
    	.thenReturn(Arrays.asList(DatosDummy.pabellon01()));
    	
    	//When 
    	List<Pabellon> expected=(List<Pabellon>)pabellonDAO.buscarPabellonPorNombre(nombre);
    	
    	//Then 
    	 assertThat(expected.get(0)).isEqualTo(DatosDummy.pabellon01());
         verify(pabellonRepository).buscarPabellonPorNombre(nombre);
		
	}
	
	
}
