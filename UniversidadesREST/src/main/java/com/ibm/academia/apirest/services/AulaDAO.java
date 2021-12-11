package com.ibm.academia.apirest.services;

import java.util.Optional;
import com.ibm.academia.apirest.entities.Aula;
import com.ibm.academia.apirest.entities.Pabellon;
import com.ibm.academia.apirest.enums.Pizarron;


public interface AulaDAO extends GenericoDAO<Aula>
{
	public Iterable<Aula> buscarAulaPorTipoPizarron(Pizarron tipoPizarron);
	public Iterable<Aula> buscarAulaPorNombrePabellon(String nombrePabellon);
	public Optional<Aula> buscarAulaPorNumero(Integer numeroAula); 
	public Aula actualizar(Aula aulaEncontrada, Aula aula);
	public Aula asociarPabellonAula(Aula aula, Pabellon pabellon) ;
}
