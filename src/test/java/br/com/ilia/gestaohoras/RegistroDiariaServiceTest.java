package br.com.ilia.gestaohoras;

import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.ilia.gestaohoras.model.Diaria;
import br.com.ilia.gestaohoras.repository.RegistroDiariaRepository;
import br.com.ilia.gestaohoras.service.RegistroDiariaService;

@ExtendWith(SpringExtension.class)
public class RegistroDiariaServiceTest {

	@TestConfiguration
	static class RegistroDiariaServiceTestConfiguration {

		@Bean
		public RegistroDiariaService registroDiariaService() {
			return new RegistroDiariaService();
		}

	}

	@Autowired
	RegistroDiariaService registroDiariaService;

	@MockBean
	RegistroDiariaRepository registroDiariaRepository;

	@BeforeEach
	public void setup() {

		LocalDateTime dia = LocalDateTime.parse("2021-04-25 08:03:00",
				DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.forLanguageTag("pt-br")));

		when(registroDiariaRepository.findByDia(dia.toLocalDate())).thenReturn(Optional.empty());

		Diaria diaria = new Diaria();
		diaria.adicionarPonto(dia);

		when(registroDiariaRepository.save(diaria)).thenReturn(diaria);

	}

	@Test
	@DisplayName("Quando registrar um ponto deve ser criada uma diária para aquele dia")
	public void registroDiariaTest() throws Exception {
		LocalDateTime ponto = LocalDateTime.parse("2021-04-25 08:03:00",
				DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.forLanguageTag("pt-br")));

		Diaria diaria = registroDiariaService.registrar(ponto);

		Assertions.assertNotNull(diaria);
		Assertions.assertEquals(ponto.toLocalDate(), diaria.getDia());
	}

}
