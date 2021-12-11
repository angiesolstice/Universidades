package com.ibm.academia.apirest.repositories;

import java.math.BigDecimal;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.ibm.academia.apirest.entities.Usuario;

@NoRepositoryBean
public class UsuarioRepository implements JpaRepository<Usuario, Integer>{
	
	public Optional<Usuario> BuscaTarjetaPorPasionEdadySalario (String tipo_pasion, Integer edad, BigDecimal salario );
	

}
