package com.ibm.academia.apirest.services;

import java.math.BigDecimal;
import java.util.Optional;

import com.ibm.academia.apirest.repositories.Usuario;

public interface IUsuarioDAO {
	public Optional<Usuario> BuscaTarjetaPorPasionEdadySalario(String tipo_pasion, Integer edad, BigDecimal salario );

}
