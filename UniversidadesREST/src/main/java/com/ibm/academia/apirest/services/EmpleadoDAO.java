package com.ibm.academia.apirest.services;
import com.ibm.academia.apirest.entities.Pabellon;
import com.ibm.academia.apirest.entities.Persona;
import com.ibm.academia.apirest.enums.TipoEmpleado;

public interface EmpleadoDAO extends PersonaDAO
{
  
     public Iterable<Persona>findEmpleadosByTipoEmpleado(TipoEmpleado tipoEmpleado);
     public Persona actualizar(Persona empleadoEncontrado, Persona empleado);
 	 public Persona asociarPabellonEmpleado(Persona empleado, Pabellon pabellon);
}
