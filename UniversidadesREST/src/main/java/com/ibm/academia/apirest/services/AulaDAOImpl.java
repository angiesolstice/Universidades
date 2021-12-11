package com.ibm.academia.apirest.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ibm.academia.apirest.entities.Aula;
import com.ibm.academia.apirest.entities.Pabellon;
import com.ibm.academia.apirest.enums.Pizarron;
import com.ibm.academia.apirest.repositories.AulaRepository;
@Service
public class AulaDAOImpl extends GenericoDAOImpl<Aula,AulaRepository> implements AulaDAO{
    @Autowired
	public AulaDAOImpl(AulaRepository repository) {
		super(repository);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Aula> buscarAulaPorNumero(Integer numeroAula) 
	{
		return repository.buscarAulaPorNumero(numeroAula);
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Aula> buscarAulaPorTipoPizarron(Pizarron tipoPizarron) 
	{
		return repository.buscarAulaPorTipoPizarron(tipoPizarron);
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Aula> buscarAulaPorNombrePabellon(String nombrePabellon) 
	{
		return repository.buscarAulaPorNombrePabellon(nombrePabellon);
	}

	@Override
	@Transactional
	public Aula actualizar(Aula aulaEncontrada, Aula aula)
	{
		Aula aulaActualizada = null;
		aulaEncontrada.setCantidadPupitres(aula.getCantidadPupitres());
		aulaEncontrada.setPizarron(aula.getPizarron());
		aulaEncontrada.setNumeroAula(aula.getNumeroAula());
		aulaActualizada = repository.save(aulaEncontrada);
		return aulaActualizada;
	}

	@Override
	@Transactional
	public Aula asociarPabellonAula(Aula aula, Pabellon pabellon) 
	{
		aula.setPabellon(pabellon);;
		return repository.save(aula);
	}
}
