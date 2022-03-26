package tn.esprit.spring;

import static org.assertj.core.api.Assertions.assertThat;


import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.repository.EmployeRepository;
import tn.esprit.spring.services.IEmployeService;
@RunWith(SpringRunner.class )
@SpringBootTest
public class EmployeServiceImpTest {
	@Autowired
	IEmployeService employeService;

	@Autowired
	EmployeRepository employeRepository;

	@Test
	public void TestaddOrUpdateEmploye() {
		Employe emp = new Employe(1, "X", "X", "X", "X", true, null);
		int id = employeService.addOrUpdateEmploye(emp);
		Assert.assertEquals(1, id);
	}

	@Test
	public void TestgetEmployePrenomById() {
		Employe emp = new Employe(1, "X", "X", "X", "X", true, null);
		String prenom = employeService.getEmployePrenomById(emp.getId());
		Assert.assertEquals("X", prenom);
	}

	@Test
	public void TestgetAllEmployes() {
		List<Employe> list = employeService.getAllEmployes();
		assertThat(list).size().isGreaterThan(0);
	}

	@Test
	public void TestdeleteEmployeById() {
		employeService.deleteEmployeById(1);
		assertThat(employeRepository.existsById(1)).isFalse();
	}

}
