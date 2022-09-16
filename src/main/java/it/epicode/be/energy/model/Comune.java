package it.epicode.be.energy.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Comune {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	@ManyToOne
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
	private Provincia provincia;

	public Comune(String nome,Provincia provincia) {
		this.nome = nome;
		this.provincia = provincia;
	}

	public Comune(Long id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	@Override
	public String toString() {
		return "Comune [id=" + id + ", nome=" + nome + "]";
	}
		
	
}
