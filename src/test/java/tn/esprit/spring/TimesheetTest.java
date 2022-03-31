package tn.esprit.spring;

import static org.assertj.core.api.Assertions.assertThat;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.entities.Timesheet;
import tn.esprit.spring.repository.EmployeRepository;
import tn.esprit.spring.repository.MissionRepository;
import tn.esprit.spring.repository.TimesheetRepository;
import tn.esprit.spring.services.TimesheetServiceImpl;
@RunWith(SpringRunner.class )
@SpringBootTest
public class TimesheetTest {
    @Autowired
    TimesheetServiceImpl timesheetService;

    @Autowired
    TimesheetRepository timesheetRepository;
    @Autowired
    EmployeRepository employeRepository;
    @Autowired
    MissionRepository missionRepository;





    @Test
    public void TestfindAllMissionByEmployeJPQL() {

        List<timesheetService> list = timesheetService.findAllMissionByEmployeJPQL(employeId);
        assertThat(list).size().isGreaterThan(0);
    }
    
    @Test
    public void TestajouterTimesheet() {
    	 DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
         Calendar cal = Calendar.getInstance();
         Date date = cal.getTime();
 		Employe emp = new Employe(1, "X", "X", "X", "X", true, null);
 		employeRepository.save(emp);
 		Mission mission= new Mission();
 		mission.setId(1);
 		mission.setDepartement(null);
 		mission.setDescription("test");
 		mission.setName("test");
 		mission.setTimesheets(null);
 		missionRepository.save(mission);

    	timesheetService.ajouterTimesheet(1, 1, date, date);  
     	List<Timesheet> list=timesheetRepository.getTimesheetsByMissionAndDate(emp, mission, date, date);

    	assertThat(list).size().isGreaterThan(0);
    }
    



}
