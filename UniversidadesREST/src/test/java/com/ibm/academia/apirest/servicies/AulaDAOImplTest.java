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
import com.ibm.academia.apirest.data.DatosDummy;
import com.ibm.academia.apirest.entities.Aula;
import com.ibm.academia.apirest.entities.Pabellon;
import com.ibm.academia.apirest.enums.Pizarron;
import com.ibm.academia.apirest.repositories.AulaRepository;
import com.ibm.academia.apirest.services.AulaDAO;
import com.ibm.academia.apirest.services.AulaDAOImpl;

public class AulaDAOImplTest
{

	AulaDAO aulaDAO;
	AulaRepository aulaRepository;

    @BeforeEach
    void setUp() 
    {
        aulaRepository = mock(AulaRepository.class);
        aulaDAO = new AulaDAOImpl(aulaRepository);
    }
    
    @Test
    @DisplayName("Test: Buscar aulas por tipo de pizarron")
	void buscarAulaPorTipoPizarron()
	{
    	//Given
    	Pizarron tipoPizarron = Pizarron.PIZARRA_TIZA;
    	when(aulaRepository.buscarAulaPorTipoPizarron(tipoPizarron))
    	.thenReturn(Arrays.asList(DatosDummy.aula01(),DatosDummy.aula02()));
    	
    	//When 
    	List<Aula> expected=(List<Aula>)aulaDAO.buscarAulaPorTipoPizarron(tipoPizarron);
    	
    	//Then 
    	 assertThat(expected.get(0)).isEqualTo(DatosDummy.aula01());
         assertThat(expected.get(1)).isEqualTo(DatosDummy.aula02());
         verify(aulaRepository).buscarAulaPorTipoPizarron(tipoPizarron);
    	
    	
	}
	
    @Test
    @DisplayName("Test: Buscar aulas por nombre del pabellon")
	void buscarAulaPorNombrePabellon()
	{
    	//Given
        String nombrePabellon = "Sistemas I";
        Pabellon pabellon= DatosDummy.pabellon01();
        Aula aula1=DatosDummy.aula01();
        Aula aula2=DatosDummy.aula02();
        aula1.setPabellon(pabellon);
        aula2.setPabellon(pabellon);
    	when(aulaRepository.buscarAulaPorNombrePabellon(nombrePabellon))
    	.thenReturn(Arrays.asList(aula1,aula2));
    	
    	
    	//When 
    	List<Aula> expected=(List<Aula>)aulaDAO.buscarAulaPorNombrePabellon(nombrePabellon);
    	
    	//Then 
    	 assertThat(expected.get(0)).isEqualTo(aula1);
         assertThat(expected.get(1)).isEqualTo(aula2);
         verify(aulaRepository).buscarAulaPorNombrePabellon(nombrePabellon);
	}
	
    @Test
    @DisplayName("Test: Buscar aula por su número")
	void buscarAulaPorNumero()
	{
		//Given
    	Integer numero= 1;
    	when(aulaRepository.buscarAulaPorNumero(numero))
    	.thenReturn(Optional.of(DatosDummy.aula01()));
    	
    	//When 
    	Optional<Aula> expected=aulaDAO.buscarAulaPorNumero(numero);
    	
    	//Then 
    	 assertThat(expected.get()).isEqualTo(DatosDummy.aula01());
         verify(aulaRepository).buscarAulaPorNumero(numero);
	}

  
}
