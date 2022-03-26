package tn.esprit.spring.services;

import java.util.ArrayList;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.controller.RestControlEmploye;
import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.entities.Timesheet;
import tn.esprit.spring.execeptions.ResourceNotFoundException;
import tn.esprit.spring.repository.ContratRepository;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EmployeRepository;
import tn.esprit.spring.repository.TimesheetRepository;

@Service
public class EmployeServiceImpl implements IEmployeService {
	private static final Logger logger = LogManager.getLogger(EmployeServiceImpl.class);

	@Autowired
	EmployeRepository employeRepository;
	@Autowired
	DepartementRepository deptRepoistory;
	@Autowired
	ContratRepository contratRepoistory;
	@Autowired
	TimesheetRepository timesheetRepository;

	@Override
	public Employe authenticate(String login, String password) {
		logger.info("logging in .....");
		return employeRepository.findByEmailAndPassword(login, password);
	}

	@Override
	public int addOrUpdateEmploye(Employe employe) {
		employeRepository.save(employe);
		return employe.getId();
	}


	public void mettreAjourEmailByEmployeId(String email, int employeId) {
		//Call "Optional#isPresent()" before accessing the value. (example)

		Optional<Employe> optEmp=employeRepository.findById(employeId);
		if (optEmp.isPresent()) {
			Employe employe = optEmp.get();
			employe.setEmail(email);
			employeRepository.save(employe);
		}

	}

	@Transactional	
	public void affecterEmployeADepartement(int employeId, int depId) {
		Optional<Departement> optionalDepartement = deptRepoistory.findById(depId);
		Optional<Employe> optionalEmpt = employeRepository.findById(employeId);
			
		logger.debug("Departement" + optionalDepartement);
		logger.debug("Employe" + optionalEmpt);


		if (optionalDepartement.isPresent() && optionalEmpt.isPresent()) {
			Departement depManagedEntity = optionalDepartement.get();
			Employe employeManagedEntity = optionalEmpt.get();

			if (depManagedEntity.getEmployes() == null) {
				
				logger.info("departement  "+ depManagedEntity.getName() + " doesnt have any employees");
				List<Employe> employes = new ArrayList<>();
				employes.add(employeManagedEntity);
				depManagedEntity.setEmployes(employes);
			} else {

				depManagedEntity.getEmployes().add(employeManagedEntity);
			}

			logger.info("employe "+ employeManagedEntity.getEmail() + " affected to departement " + depManagedEntity );
			deptRepoistory.save(depManagedEntity);
		}

	}
	@Transactional
	public void desaffecterEmployeDuDepartement(int employeId, int depId)
	{
		Departement dep = deptRepoistory.findById(depId).orElse(new Departement());

		int employeNb = dep.getEmployes().size();
		for(int index = 0; index < employeNb; index++){
			if(dep.getEmployes().get(index).getId() == employeId){
				dep.getEmployes().remove(index);
				break;//a revoir
			}
		}
	} 
	
	// Tablesapce (espace disque) 

	public int ajouterContrat(Contrat contrat) {
		contratRepoistory.save(contrat);
		return contrat.getReference();
	}

	public void affecterContratAEmploye(int contratId, int employeId) {
		Optional<Contrat> optionalContrat = contratRepoistory.findById(contratId);
		Optional<Employe> optionalEmploye = employeRepository.findById(employeId);

		if (optionalContrat.isPresent() && optionalEmploye.isPresent()) {
			Contrat contratManagedEntity = optionalContrat.get();
			Employe employeManagedEntity = optionalEmploye.get();

			contratManagedEntity.setEmploye(employeManagedEntity);
			contratRepoistory.save(contratManagedEntity);
		}
		logger.error("Contract or Employedoesnt exist");

	}

	public String getEmployePrenomById(int employeId) {
		Optional<Employe> optionalEmploye = employeRepository.findById(employeId);

		if(optionalEmploye.isPresent()) {
		Employe employeManagedEntity = optionalEmploye.get();
		return employeManagedEntity.getPrenom(); }
		else {
				throw  new ResourceNotFoundException("Employe not found");
			
		}
	}
	 
	public void deleteEmployeById(int employeId)
	{
		Optional<Employe> optionalEmploye = employeRepository.findById(employeId);

		if (optionalEmploye.isPresent()) {
			Employe employe = optionalEmploye.get();

			logger.info("Desaffecter l'employe de tous les departements c'est le bout master qui permet de mettre a jour la table dassociation");
			for (Departement dep : employe.getDepartements()) {
				dep.getEmployes().remove(employe);
			}

			employeRepository.delete(employe);
		}
		logger.error("Employe doesnt exist");

	}

	public void deleteContratById(int contratId) {
		Optional<Contrat> optionalContrat = contratRepoistory.findById(contratId);

		if(optionalContrat.isPresent()) {
		Contrat contratManagedEntity = optionalContrat.get();
		contratRepoistory.delete(contratManagedEntity);
		}
		logger.error("Contract doesnt exist");

	}

	public int getNombreEmployeJPQL() {
		return employeRepository.countemp();
	}

	public List<String> getAllEmployeNamesJPQL() {
		return employeRepository.employeNames();

	}

	public List<Employe> getAllEmployeByEntreprise(Entreprise entreprise) {
		return employeRepository.getAllEmployeByEntreprisec(entreprise);
	}

	public void mettreAjourEmailByEmployeIdJPQL(String email, int employeId) {
		employeRepository.mettreAjourEmailByEmployeIdJPQL(email, employeId);

	}
	public void deleteAllContratJPQL() {
		employeRepository.deleteAllContratJPQL();
	}

	public float getSalaireByEmployeIdJPQL(int employeId) {
		return employeRepository.getSalaireByEmployeIdJPQL(employeId);
	}

	public Double getSalaireMoyenByDepartementId(int departementId) {
		return employeRepository.getSalaireMoyenByDepartementId(departementId);
	}

	public List<Timesheet> getTimesheetsByMissionAndDate(Employe employe, Mission mission, Date dateDebut,
			Date dateFin) {
		return timesheetRepository.getTimesheetsByMissionAndDate(employe, mission, dateDebut, dateFin);
	}

	public List<Employe> getAllEmployes() {
		return (List<Employe>) employeRepository.findAll();
	}

}
