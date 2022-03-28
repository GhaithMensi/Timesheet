package tn.esprit.spring.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EntrepriseRepository;

@Service
public class EntrepriseServiceImpl implements IEntrepriseService {
	private static final Logger logger = LogManager.getLogger(EntrepriseServiceImpl.class);

	@Autowired
    EntrepriseRepository entrepriseRepoistory;
	@Autowired
	DepartementRepository deptRepoistory;
	
	public int ajouterEntreprise(Entreprise entreprise) {
		entrepriseRepoistory.save(entreprise);
		logger.info(entreprise.getName() +"ajouter avec succès");
		return entreprise.getId();
	}

	public int ajouterDepartement(Departement dep) {
		deptRepoistory.save(dep);
		logger.info(dep.getName() +"ajouter avec succès");
		return dep.getId();
		
	}
	
	public void affecterDepartementAEntreprise(int depId, int entrepriseId) {
		//Le bout Master de cette relation N:1 est departement  
				//donc il faut rajouter l'entreprise a departement 
				// ==> c'est l'objet departement(le master) qui va mettre a jour l'association
				//Rappel : la classe qui contient mappedBy represente le bout Slave
				//Rappel : Dans une relation oneToMany le mappedBy doit etre du cote one.
				
		Optional<Entreprise> entrepriseManagedEntity = entrepriseRepoistory.findById(entrepriseId);
		logger.debug("Entreprise" + entrepriseManagedEntity);
		
				Entreprise entreprise=null;
				if(entrepriseManagedEntity.isPresent())
					entreprise=entrepriseManagedEntity.get();
					
				
				
				Optional<Departement> depManagedEntity = deptRepoistory.findById(depId);
				logger.debug("Departement" + depManagedEntity);
				Departement departement=null;
				if(depManagedEntity.isPresent())
				{
					departement = depManagedEntity.get();
				    departement.setEntreprise(entreprise);
				    logger.info("Departement "+ depManagedEntity.get() + " affected to entreprise " + entrepriseManagedEntity );
				    deptRepoistory.save(departement);
				}
				
				logger.error("Entreprise or Departement doesnt exist");
		
	}
	
	
	public List<String> getAllDepartementsNamesByEntreprise(int entrepriseId) {
		Optional<Entreprise> entrepriseManagedEntity=entrepriseRepoistory.findById(entrepriseId);
		Entreprise ent=new Entreprise();
		if (entrepriseManagedEntity.isPresent())
				ent=entrepriseManagedEntity.get();
		
		List<String> depNames = new ArrayList<>();
		for(Departement dep : ent.getDepartements()){
			depNames.add(dep.getName());
		}
		
		return depNames;
	}
	
	
	@Transactional
	public void deleteEntrepriseById(int entrepriseId) {
		entrepriseRepoistory.delete(entrepriseRepoistory.findById(entrepriseId).orElse(null));	
	}

	@Transactional
	public void deleteDepartementById(int depId) {
		deptRepoistory.delete(deptRepoistory.findById(depId).orElse(null));	
	}


	public Entreprise getEntrepriseById(int entrepriseId) {
		return entrepriseRepoistory.findById(entrepriseId).orElse(null);	
	}

}
