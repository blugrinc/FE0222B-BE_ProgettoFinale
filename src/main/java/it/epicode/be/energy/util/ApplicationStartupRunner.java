package it.epicode.be.energy.util;

import java.io.FileNotFoundException;

import java.io.FileReader;
import java.io.IOException;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import it.epicode.be.energy.model.Comune;
import it.epicode.be.energy.model.Provincia;
import it.epicode.be.energy.repository.ComuneRepository;
import it.epicode.be.energy.repository.ProvinciaRepository;
import it.epicode.be.energy.repository.RoleRepository;
import it.epicode.be.energy.repository.UserRepository;


/**
 * Classe utility per l'inizializzazione del database all'avvio
 * dell'applicazione
 *
 */
@Component
public class ApplicationStartupRunner implements CommandLineRunner {

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	ComuneRepository comuneRepository;

	@Autowired
	ProvinciaRepository provinciaRepository;

	@Override
	public void run(String... args) throws Exception {
		caricaProvincia();
		caricaComune();

	}

	public void caricaProvincia() {
		CSVParser csvParser = new CSVParserBuilder().withSeparator(';').build();
		try (CSVReader reader = new CSVReaderBuilder(new FileReader("src/main/resources/static/provinceitaliane.csv")).withCSVParser(csvParser)
				.withSkipLines(1).build()) {
			String[] values = null;
			reader.readNext();

			while ((values = reader.readNext()) != null) {
				Provincia prov = new Provincia();
				prov.setSigla(values[0]);
				prov.setNome(values[1]);
				prov.setRegione(values[2]);

				provinciaRepository.save(new Provincia(values[0], values[1], values[2]));

			}
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public void caricaComune() {
		CSVParser csvParser = new CSVParserBuilder().withSeparator(';').build();
		try (CSVReader reader = new CSVReaderBuilder(new FileReader("src/main/resources/static/comuniitaliani.csv")).withCSVParser(csvParser)
				.withSkipLines(1).build()) {
			String[] values = null;

			while ((values = reader.readNext()) != null) {
				Comune comune = new Comune();
				comune.setNome(values[2]);
				Provincia prov = provinciaRepository.findByNome(values[3]);
				if (prov != null) {
					comune.setProvincia(prov);
					comuneRepository.save(comune);
				}

			}
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

}
