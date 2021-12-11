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
import com.ibm.academia.apirest.entities.Persona;
import com.ibm.academia.apirest.enums.TipoEmpleado;
import com.ibm.academia.apirest.repositories.EmpleadoRepository;
import com.ibm.academia.apirest.services.EmpleadoDAO;
import com.ibm.academia.apirest.services.EmpleadoDAOImpl;


public class EmpleadoDAOImplTest 
{

    EmpleadoDAO empleadoDAO;
	
    EmpleadoRepository empleadoRepository;

	
	@BeforeEach
    void setUp() 
    {
        empleadoRepository= mock(EmpleadoRepository.class);
        empleadoDAO= new EmpleadoDAOImpl(empleadoRepository);
    }
	
	@Test
    @DisplayName("Test: Buscar empleados por tipo de empleado")
	void findEmpleadosByTipoEmpleado()
	{
		//Given
        TipoEmpleado tipoEmpleado =TipoEmpleado.ADMINISTRATIVO;
        when((empleadoRepository).findEmpleadosByTipoEmpleado(tipoEmpleado))
                .thenReturn(Arrays.asList(DatosDummy.empleado01(),DatosDummy.empleado03()));

        //When
        List<Persona> expected =(List<Persona>)(empleadoDAO).findEmpleadosByTipoEmpleado(tipoEmpleado);

        //Then
        assertThat(expected.get(0)).isEqualTo(DatosDummy.empleado01());
        assertThat(expected.get(1)).isEqualTo(DatosDummy.empleado03());

        verify(empleadoRepository).findEmpleadosByTipoEmpleado(tipoEmpleado);
		
	}
	
	
}
