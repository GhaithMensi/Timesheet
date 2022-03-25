package tn.esprit.spring.models;

import java.util.List;

import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.entities.Timesheet;
//Replace this persistent entity with a simple POJO or DTO object.

public class EmployeModel {
	private int id;

	private String prenom;

	private String nom;

	private String email;

	private String password;

	private boolean actif;

	private Role role;
	private List<Departement> departements;
	private Contrat contrat;

	private List<Timesheet> timesheets;

	public Employe toEntity() {
		Employe entity = new Employe();

		entity.setId(this.id);
		entity.setActif(this.actif);
		entity.setContrat(this.contrat);
		entity.setDepartements(this.departements);
		entity.setEmail(this.email);
		entity.setNom(this.nom);
		entity.setPassword(this.password);
		entity.setPrenom(this.prenom);
		entity.setRole(this.role);
		entity.setTimesheets(this.timesheets);

		return entity;
	}

}
