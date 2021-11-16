package com.covid.tracker.utilities;

import com.covid.tracker.beans.CovidPatient;
import com.covid.tracker.beans.CovidPatientRepository;
import com.covid.tracker.beans.VaccinatedPeople;
import com.covid.tracker.beans.VaccinatedPeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.NoResultException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CSVService {

    @Autowired
    CovidPatientRepository covidPatientRepository;
    @Autowired
    VaccinatedPeopleRepository vaccinatedPeopleRepository;

    public String addCovidPatients(MultipartFile file) throws IOException {
        String result = "";
        List<CovidPatient> covidPatients = CSVUtil.loadCSVForAddingCovidPatientsInDB(file.getInputStream());
        covidPatientRepository.saveAll(covidPatients);
        result = "Records saved in DB";
        return result;
    }

    public String modifyCovidPatients(MultipartFile file) throws IOException {
        String result = "";
        List<CovidPatient> covidPatients = CSVUtil.loadCSVForModifyingCovidPatientsInDB(file.getInputStream());
        covidPatientRepository.saveAll(covidPatients);
        result = "Records saved in DB";
        return result;
    }

    public String addVaccinatedPeople(MultipartFile file) throws IOException {
        String result = "";
        List<VaccinatedPeople> vaccinatedPeople = CSVUtil.loadCSVForAddingVaccinatedPeopleInDB(file.getInputStream());
        vaccinatedPeopleRepository.saveAll(vaccinatedPeople);
        result = "Records saved in DB";
        return result;
    }

    public String modifyVaccinatedPeople(MultipartFile file) throws IOException {
        String result = "";
        List<VaccinatedPeople> vaccinatedPeople = CSVUtil.loadCSVForModifyingVaccinatedPeopleInDB(file.getInputStream());
        vaccinatedPeopleRepository.saveAll(vaccinatedPeople);
        result = "Records saved in DB";
        return result;
    }

    public Page<CovidPatient> getCovidPatients(Pageable pageable) {
        if(!covidPatientRepository.findAll(pageable).isEmpty())
            return covidPatientRepository.findAll(pageable);
        throw new NoResultException("No result found");
    }

    public Page<VaccinatedPeople> getVaccinatedPeople(Pageable pageable) {
        if(!vaccinatedPeopleRepository.findAll(pageable).isEmpty())
            return vaccinatedPeopleRepository.findAll(pageable);
        throw new NoResultException("No result found");
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
