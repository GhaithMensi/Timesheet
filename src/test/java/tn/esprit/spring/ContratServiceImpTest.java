package tn.esprit.spring;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import tn.esprit.spring.services.IContratService;
import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.repository.ContratRepository;

@RunWith(SpringRunner.class )
@SpringBootTest

public class ContratServiceImpTest {
	
	@Autowired
	IContratService ContratService;

	@Autowired
	ContratRepository ContratRepository;


	@Test 
	public void TestgetAllContrats() {
		List<Contrat> list = ContratService.getAllContrats();
		assertThat(list).size().isGreaterThan(0);
	}


}
