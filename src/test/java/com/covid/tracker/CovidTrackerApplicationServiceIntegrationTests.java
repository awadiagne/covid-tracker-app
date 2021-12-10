package com.covid.tracker;

import com.covid.tracker.beans.CovidPatient;
import com.covid.tracker.beans.CovidPatientRepository;
import com.covid.tracker.beans.VaccinatedPeople;
import com.covid.tracker.beans.VaccinatedPeopleRepository;
import com.covid.tracker.utilities.CSVService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class CovidTrackerApplicationServiceIntegrationTests {

    @TestConfiguration
    static class CovidPatientServiceImplTestContextConfiguration {

        @Bean
        public CSVService fileService() {
            return new CSVService();
        }
    }

    @Autowired
    private CSVService fileService;

    @MockBean
    private CovidPatientRepository covidPatientRepository;

    @MockBean
    private VaccinatedPeopleRepository vaccinatedPeopleRepository;

    @Before("")
    public void setup() throws ParseException {
        CovidPatient patient = new CovidPatient("Boubou", new SimpleDateFormat().parse("17-03-2006"), "Mermoz","07", "10", "2021",true, "France");

        Mockito.when(covidPatientRepository.findByName(patient.getName())).thenReturn(patient);

        VaccinatedPeople vaccinatedPeople = new VaccinatedPeople("Boubou", new SimpleDateFormat().parse("17-03-2006"), "Mermoz",new SimpleDateFormat().parse("07-10-2021"),new SimpleDateFormat().parse("07/12/2021"));

        Mockito.when(vaccinatedPeopleRepository.findByName(vaccinatedPeople.getName())).thenReturn(vaccinatedPeople);
    }

    @Test
    public void whenValidName_thenCovidPatientShouldBeFound() {
        String name = "Boubou";
        CovidPatient found = fileService.getCovidPatientByName(name);

        assertThat(found.getName()).isEqualTo(name);
    }

    @Test
    public void whenValidName_thenVaccinatedPeopleShouldBeFound() {
        String name = "Boubou";
        VaccinatedPeople found = fileService.getVaccinatedPeopleByName(name);

        assertThat(found.getName()).isEqualTo(name);
    }
}
