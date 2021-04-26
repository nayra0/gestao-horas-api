package br.com.ilia.gestaohoras.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Diaria {
	
	private LocalDate dia;
		
	public void adicionarPonto(LocalDateTime ponto) {
		if(this.dia == null) {
			this.dia = ponto.toLocalDate();
		}
	}
	
	public LocalDate getDia() {
		return this.dia;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dia == null) ? 0 : dia.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Diaria other = (Diaria) obj;
		if (dia == null) {
			if (other.dia != null)
				return false;
		} else if (!dia.equals(other.dia))
			return false;
		return true;
	}

}
