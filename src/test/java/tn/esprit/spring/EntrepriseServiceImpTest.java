package tn.esprit.spring;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EntrepriseRepository;
import tn.esprit.spring.services.IDepartementService;
import tn.esprit.spring.services.IEntrepriseService;

public class EntrepriseServiceImpTest {
	
	@Autowired
	IEntrepriseService entrepriseService;

	@Autowired
	EntrepriseRepository entrepriseRepository;
	
	
	
	@Test
	public void TestajouterEntreprise() {
		Entreprise ent = new Entreprise("test", "test1");
		int id = entrepriseService.ajouterEntreprise(ent);
		Assert.assertEquals(1, id);
	}
	
	@Test
	public void TestajouterDepartement() {
		Departement dep = new Departement("test");
		int id = entrepriseService.ajouterDepartement(dep);
		Assert.assertEquals(1, id);
	}
		
		
	
	@Test
	public void TestdeleteEntrepriseById() {
		entrepriseService.deleteEntrepriseById(1);
		assertThat(entrepriseRepository.existsById(1)).isFalse();
	}


}
