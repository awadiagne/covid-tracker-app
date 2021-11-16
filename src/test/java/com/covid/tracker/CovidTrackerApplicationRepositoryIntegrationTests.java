package com.covid.tracker;

import com.covid.tracker.beans.CovidPatient;
import com.covid.tracker.beans.CovidPatientRepository;
import com.covid.tracker.beans.VaccinatedPeople;
import com.covid.tracker.beans.VaccinatedPeopleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CovidTrackerApplicationRepositoryIntegrationTests {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CovidPatientRepository covidPatientRepository;

    @Autowired
    private VaccinatedPeopleRepository vaccinatedPeopleRepository;


    @Test
    public void whenFindByName_thenReturnCovidPatient() {
        // given
        CovidPatient patient = new CovidPatient("Ali", "14/11/1996", "Sacre coeur","07/10/2021",true);
        entityManager.persist(patient);
        entityManager.flush();

        // when
        CovidPatient found = covidPatientRepository.findByName(patient.getName());

        // then
        assertThat(found.getName()).isEqualTo(patient.getName());
    }

    @Test
    public void whenFindByName_thenReturnVaccinatedPeople() {
        // given
        VaccinatedPeople vaccinatedPeople = new VaccinatedPeople("Ali", "14/11/1996", "Sacre coeur","07/10/2021","07/12/2021");
        entityManager.persist(vaccinatedPeople);
        entityManager.flush();

        // when
        VaccinatedPeople found = vaccinatedPeopleRepository.findByName(vaccinatedPeople.getName());

        // then
        assertThat(found.getName()).isEqualTo(vaccinatedPeople.getName());
    }
}
