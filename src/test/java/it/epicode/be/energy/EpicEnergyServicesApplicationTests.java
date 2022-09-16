package it.epicode.be.energy;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import it.epicode.be.energy.model.Cliente;
import it.epicode.be.energy.model.Comune;
import it.epicode.be.energy.model.Indirizzo;
import it.epicode.be.energy.model.Provincia;

@AutoConfigureMockMvc
@SpringBootTest
class EpicEnergyServicesApplicationTests {
	
	@Autowired
	private MockMvc mockMvc;
	
	Cliente cliente;
	Indirizzo sedeLegale;
	Indirizzo sedeOperativa;
	Comune comune;
	Provincia provincia;
	
	@Test
	@WithAnonymousUser
	public void loginSenzaBody() throws Exception {
		this.mockMvc.perform(post("/auth/login")).andExpect(status().isBadRequest());
	}

	@Test
	@WithAnonymousUser
	public void testAllClienti() throws Exception {
		this.mockMvc.perform(get("/api/cliente")).andExpect(status().isUnauthorized());
	}
	
	@Test
	@WithAnonymousUser
	public void testAllFatture() throws Exception {
		this.mockMvc.perform(get("/api/fattura")).andExpect(status().isUnauthorized());
	}
	
	
	@BeforeEach
	public void initContext() {
	cliente = new Cliente();
	cliente.setRagioneSociale("AuleroSrl");
	cliente.setPartitaIva("AA11223344");
	cliente.setEmail("admind@admin.it");
	LocalDate dateIns = LocalDate.of(2022, 9, 16);
	cliente.setDataInserimento(dateIns);
	LocalDate dateLast = LocalDate.of(2022, 9, 16);
	cliente.setDataUltimoContatto(dateLast);
	BigDecimal fatturato =new BigDecimal(200000);
	cliente.setFatturatoAnnuale(fatturato);
	cliente.setPec("admin@pec.it");
	cliente.setTelefono("3332224454");
	cliente.setEmailContatto("auleroemail@mail.it");
	cliente.setNomeContatto("Aulero");
	cliente.setCognomeContatto("luigiani");
	cliente.setTelefonoContatto("3332224454");
	
	sedeLegale = new Indirizzo();
	sedeLegale.setVia("Via Politio");
	sedeLegale.setCivico(9);
	sedeLegale.setLocalita("Siracusa");
	sedeLegale.setCap(96100L);
	
	sedeOperativa = new Indirizzo();
	sedeOperativa.setVia("Via Umberto");
	sedeOperativa.setCivico(10);
	sedeOperativa.setLocalita("Bologna");
	sedeOperativa.setCap(96100L);
	
	provincia = new Provincia();
	provincia.setNome("Siracusa");
	provincia.setRegione("Sicilia");
	provincia.setSigla("SR");
	
	comune = new Comune();
	comune.setNome("Siracusa");
	comune.setProvincia(provincia);
	
	}
	
	
}