package tn.esprit.spring.models;


import java.io.Serializable;
import java.util.List;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.entities.Timesheet;

public class MissionModel implements Serializable {

	private static final long serialVersionUID = -5369734855993305723L;

	private int id;
	
	private String name;
	
	private String description;
	
	
	private Departement departement;
	
	private  List<Timesheet> timesheets;
	

	public Mission toEntity() {
		Mission entity = new Mission();
		entity.setId(this.id);
		entity.setDepartement(this.departement);
		entity.setDescription(this.description);
		entity.setName(this.name);
		entity.setTimesheets(this.timesheets);
		return entity;
	}
	

}
