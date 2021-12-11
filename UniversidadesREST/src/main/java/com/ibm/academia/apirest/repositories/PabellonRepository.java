package com.ibm.academia.apirest.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.ibm.academia.apirest.entities.Pabellon;


@Repository
public interface PabellonRepository extends CrudRepository<Pabellon, Integer >
{

	@Query("select p from Pabellon p where upper(p.direccion.localidad) = upper(?1)")
	public Iterable<Pabellon> buscarPabellonPorLocalidad(String localidad);
	
	@Query("select p from Pabellon p where upper(p.nombre) = upper(?1)")
	public Iterable<Pabellon> buscarPabellonPorNombre(String nombre);
}
