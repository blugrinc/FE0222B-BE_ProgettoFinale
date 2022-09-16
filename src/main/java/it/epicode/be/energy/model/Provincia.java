package it.epicode.be.energy.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Provincia {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String sigla;
	private String nome;
	private String regione;

	public Provincia(String sigla, String nome, String regione) {
		this.sigla = sigla;
		this.nome = nome;
		this.regione = regione;
	}
}