package tn.esprit.spring;




import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import tn.esprit.spring.services.IEmployeService;
@RunWith(SpringRunner.class )
@SpringBootTest
public class TimesheetApplicationTests {

	@Autowired
	IEmployeService employeService;
	

	@Test
	public void contextLoads() {
		assertThat(employeService).isNotNull();
	}
	

}
