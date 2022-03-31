package tn.esprit.spring;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import tn.esprit.spring.entities.*;
import tn.esprit.spring.services.IEmployeService;

@RunWith(SpringRunner.class )
@SpringBootTest

public class ContratTest {
	
	@Autowired
	IEmployeService employeService;


	private Contrat contrat ; 
	String str ="29/11/2020" ; 
	
	
	@Test
	public void testGetTypeContrat() throws ParseException{
		Date date = new SimpleDateFormat("dd/mm/yyyy").parse(str) ; 
		
		contrat = new Contrat(date, "type 1", 20); 
		assertEquals("type 1", contrat.getTypeContrat());
	
	}

	
	@Test
	public void testSetTypeContrat() throws ParseException{
		Date date = new SimpleDateFormat("dd/mm/yyyy").parse(str) ; 
		
		contrat = new Contrat(date, "type 1", 20); 

		contrat.setTypeContrat("type 2");
		assertEquals("type 2", contrat.getTypeContrat());
		
	}

}
