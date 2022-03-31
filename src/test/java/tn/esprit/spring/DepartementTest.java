package tn.esprit.spring;

import static org.assertj.core.api.Assertions.assertThat;


import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.services.IDepartementService;
@RunWith(SpringRunner.class )
@SpringBootTest
public class DepartementTest {
    @Autowired
    IDepartementService departementService;

    @Autowired
    DepartementRepository deptRepoistory;



    @Test
    public void TestgetAllDepartements(){
        List<Departement> list = DepartementService.getAllDepartements();
        assertThat(list).size().isGreaterThan(0);
    }



}
