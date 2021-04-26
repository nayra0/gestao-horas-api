package br.com.ilia.gestaohoras.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ilia.gestaohoras.model.Diaria;
import br.com.ilia.gestaohoras.repository.RegistroDiariaRepository;

@Service
public class RegistroDiariaService {

	@Autowired
	RegistroDiariaRepository registroDiariaRepository;

	public Diaria registrar(LocalDateTime ponto) {
		Optional<Diaria> possivelDiaria = registroDiariaRepository.findByDia(ponto.toLocalDate());

		Diaria diaria;

		if (possivelDiaria.isEmpty()) {
			diaria = new Diaria();
		} else {
			diaria = possivelDiaria.get();
		}

		diaria.adicionarPonto(ponto);
		
		return save(diaria);
	}

	private Diaria save(Diaria diaria) {
		return registroDiariaRepository.save(diaria);		
	}

}
