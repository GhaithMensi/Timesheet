package tn.esprit.spring.models;

import java.util.Date;

import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.entities.Employe;
//Replace this persistent entity with a simple POJO or DTO object.
public class ContratModel {

	private int reference;

	private Date dateDebut;

	private String typeContrat;

	private Employe employe;

	private float salaire;

	public Contrat toEntity() {
		Contrat entity = new Contrat();

		entity.setDateDebut(this.dateDebut);
		entity.setEmploye(this.employe);
		entity.setReference(this.reference);
		entity.setSalaire(this.salaire);
		entity.setTypeContrat(this.typeContrat);

		return entity;
	}
}
