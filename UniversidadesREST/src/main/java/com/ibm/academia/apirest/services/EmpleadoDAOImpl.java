package com.ibm.academia.apirest.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ibm.academia.apirest.entities.Empleado;
import com.ibm.academia.apirest.entities.Pabellon;
import com.ibm.academia.apirest.entities.Persona;
import com.ibm.academia.apirest.enums.TipoEmpleado;
import com.ibm.academia.apirest.repositories.EmpleadoRepository;
import com.ibm.academia.apirest.repositories.PersonaRepository;
@Service
public class EmpleadoDAOImpl extends PersonaDAOImpl implements EmpleadoDAO {

	@Autowired
	public EmpleadoDAOImpl(@Qualifier("repositorioEmpleados")PersonaRepository repository) 
	{
		super(repository);
	}

	


	@Override
	@Transactional(readOnly= true)
	public Iterable<Persona> findEmpleadosByTipoEmpleado(TipoEmpleado tipoEmpleado) {
		return ((EmpleadoRepository)repository).findEmpleadosByTipoEmpleado(tipoEmpleado);
	}
	
	@Override
	@Transactional
	public Persona actualizar(Persona empleadoEncontrado, Persona empleado) 
	{
		Persona empleadoActualizado = null;
		empleadoEncontrado.setNombre(empleado.getNombre());
		empleadoEncontrado.setApellido(empleado.getApellido());
		empleadoEncontrado.setDireccion(empleado.getDireccion());;
		empleadoActualizado = repository.save(empleadoEncontrado);
		return empleadoActualizado;
	}

	@Override
	@Transactional
	public Persona asociarPabellonEmpleado(Persona empleado, Pabellon pabellon) 
	{
		((Empleado)empleado).setPabellon(pabellon);;
		return repository.save(empleado);
	}

	

	

}
