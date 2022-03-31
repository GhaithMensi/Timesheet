package tn.esprit.spring.models;

import java.util.List;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.entities.Mission;

public class DepartementModel {
	
    private int id;	
	private String name;
	private List<Employe> employes;
	private List<Mission> missions;
	private Entreprise entreprise;
	
	public Departement toEntity()
	{
		Departement entity=new Departement();
		entity.setId(this.id);
		entity.setEmployes(this.employes);
		entity.setMissions(this.missions);
		entity.setEntreprise(this.entreprise);
		
		return entity;
	}

}
