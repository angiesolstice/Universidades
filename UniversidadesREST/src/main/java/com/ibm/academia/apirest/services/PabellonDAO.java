package com.ibm.academia.apirest.services;
import com.ibm.academia.apirest.entities.Aula;
import com.ibm.academia.apirest.entities.Pabellon;

public interface PabellonDAO extends GenericoDAO<Pabellon>
{
	public Iterable<Pabellon> buscarPabellonPorLocalidad(String localidad);
	public Iterable<Pabellon> buscarPabellonPorNombre(String nombre);
	public Pabellon actualizar(Pabellon pabellonEncontrado, Pabellon pabellon);
	public Pabellon asociarAulaPabellon(Pabellon pabellon,Aula aula) ;
}
