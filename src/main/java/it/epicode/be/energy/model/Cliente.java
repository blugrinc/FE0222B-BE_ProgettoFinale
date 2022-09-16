package it.epicode.be.energy.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String ragioneSociale;
	private String partitaIva;
	@Email
	private String email;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataInserimento;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataUltimoContatto;
	private BigDecimal fatturatoAnnuale;
	private String pec;
	private String telefono;
	@Email
	private String emailContatto;
	private String nomeContatto;
	private String cognomeContatto;
	private String telefonoContatto;

	@OneToOne(cascade = CascadeType.ALL)
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
	private Indirizzo sedeLegale;

	@OneToOne(cascade = CascadeType.ALL)
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
	private Indirizzo sedeOperativa;

	@Enumerated(EnumType.STRING)
	private TipoCliente tipoCliente;

}