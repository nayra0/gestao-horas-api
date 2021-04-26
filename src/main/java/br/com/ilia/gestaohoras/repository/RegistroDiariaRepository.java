package br.com.ilia.gestaohoras.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ilia.gestaohoras.model.Diaria;

public interface RegistroDiariaRepository extends JpaRepository<Diaria, Long>{
	
	Optional<Diaria> findByDia(LocalDate dia); 

}
