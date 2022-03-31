package tn.esprit.spring.models;

import java.util.ArrayList;
import java.util.List;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Entreprise;

public class EntrepriseModel {
	
    private int id;
	private String name;	
	private String raisonSocial;
	
	private List<Departement> departements = new ArrayList<>();
	
	public Entreprise toEntity()
	{
		Entreprise entity = new Entreprise();
		entity.setId(this.id);
		entity.setName(this.name);
		entity.setRaisonSocial(this.raisonSocial);
		entity.setDepartements(this.departements);
		
		return entity;
		
	}

}
