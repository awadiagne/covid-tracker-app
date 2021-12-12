package com.covid.tracker.utilities;

import com.covid.tracker.beans.CovidPatient;
import com.covid.tracker.beans.CovidPatientRepository;
import com.covid.tracker.beans.VaccinatedPeople;
import com.covid.tracker.beans.VaccinatedPeopleRepository;
import com.covid.tracker.dto.CovidPatientDto;
import com.covid.tracker.dto.VaccinatedPeopleDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CSVService implements ICSVService {

    @Autowired
    CovidPatientRepository covidPatientRepository;
    @Autowired
    VaccinatedPeopleRepository vaccinatedPeopleRepository;
    @Autowired
    EntityManager em;

    public String addCovidPatients(MultipartFile file) throws IOException, ParseException {
        String result = "";
        List<CovidPatient> covidPatients = CSVUtil.loadCSVForAddingCovidPatientsInDB(file.getInputStream());
        covidPatientRepository.saveAll(covidPatients);
        result = "Records saved in DB";
        return result;
    }

    public String modifyCovidPatients(MultipartFile file) throws IOException, ParseException {
        String result = "";
        List<CovidPatient> covidPatients = CSVUtil.loadCSVForModifyingCovidPatientsInDB(file.getInputStream());
        covidPatientRepository.saveAll(covidPatients);
        result = "Records modified in DB";
        return result;
    }

    public String addVaccinatedPeople(MultipartFile file) throws IOException, ParseException {
        String result = "";
        List<VaccinatedPeople> vaccinatedPeople = CSVUtil.loadCSVForAddingVaccinatedPeopleInDB(file.getInputStream());
        vaccinatedPeopleRepository.saveAll(vaccinatedPeople);
        result = "Records saved in DB";
        return result;
    }

    public String modifyVaccinatedPeople(MultipartFile file) throws IOException, ParseException {
        String result = "";
        List<VaccinatedPeople> vaccinatedPeople = CSVUtil.loadCSVForModifyingVaccinatedPeopleInDB(file.getInputStream());
        vaccinatedPeopleRepository.saveAll(vaccinatedPeople);
        result = "Records modified in DB";
        return result;
    }

    public List<CovidPatient> getCovidPatientsPerDayPerCountry(int pageNum, int pageSize, String dayMonthYear, String country) {
        //Pageable paging = PageRequest.of(pageNum, pageSize, Sort.by(sort));
        String dayMonthYearTab[] = dayMonthYear.split("-");

        return em.createNamedQuery("findAllCovidPatientsPerDayPerCountry", CovidPatient.class)
                .setParameter("day", dayMonthYearTab[0])
                .setParameter("month", dayMonthYearTab[1])
                .setParameter("year", dayMonthYearTab[2])
                .setParameter("country", country)
                .setFirstResult(pageNum)
                .setMaxResults(pageSize)
                .getResultList();
    }

    public List<CovidPatient> getCovidPatientsPerMonthPerCountry(int pageNum, int pageSize, String monthYear, String country) {
        String dayMonthYearTab[] = monthYear.split("-");

        return em.createNamedQuery("findAllCovidPatientsPerMonthPerCountry", CovidPatient.class)
                .setParameter("month", dayMonthYearTab[0])
                .setParameter("year", dayMonthYearTab[1])
                .setParameter("country", country)
                .setFirstResult(pageNum)
                .setMaxResults(pageSize)
                .getResultList();
    }

    public List<CovidPatient> getCovidPatientsPerYearPerCountry(int pageNum, int pageSize,  String year, String country) {

        return em.createNamedQuery("findAllCovidPatientsPerYearPerCountry", CovidPatient.class)
                .setParameter("year", year)
                .setParameter("country", country)
                .setFirstResult(pageNum)
                .setMaxResults(pageSize)
                .getResultList();
    }

    public CovidPatient getCovidPatientByName(String name) {
        if(covidPatientRepository.findByName(name) != null)
            return covidPatientRepository.findByName(name);
        throw new NoResultException("No result found");
    }

    public VaccinatedPeople getVaccinatedPeopleByName(String name) {
        if(vaccinatedPeopleRepository.findByName(name) != null)
            return vaccinatedPeopleRepository.findByName(name);
        throw new NoResultException("No result found");
    }
}
