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

    public String addCovidPatients(MultipartFile file) {
        String result = "";
        try {
            List<CovidPatient> covidPatients = CSVUtil.loadCSVForAddingCovidPatientsInDB(file.getInputStream());
            covidPatientRepository.saveAll(covidPatients);
            result = "Records saved in DB";
        } catch (IOException e) {
            result = "Records not saved in DB ";
            throw new RuntimeException("Error while persisting data in DB: " + e.getMessage());
        }
        return result;
    }

    public String modifyCovidPatients(MultipartFile file) {
        String result = "";
        try {
            List<CovidPatient> covidPatients = CSVUtil.loadCSVForModifyingCovidPatientsInDB(file.getInputStream());
            covidPatientRepository.saveAll(covidPatients);
            result = "Records saved in DB";
        } catch (IOException e) {
            throw new RuntimeException("Error while persisting data in DB: " + e.getMessage());
        }
        return result;
    }

    public String addVaccinatedPeople(MultipartFile file) {
        String result = "";
        try {
            List<VaccinatedPeople> vaccinatedPeople = CSVUtil.loadCSVForAddingVaccinatedPeopleInDB(file.getInputStream());
            vaccinatedPeopleRepository.saveAll(vaccinatedPeople);
            result = "Records saved in DB";
        } catch (IOException e) {
            throw new RuntimeException("Error while persisting data in DB: " + e.getMessage());
        }
        return result;
    }

    public String modifyVaccinatedPeople(MultipartFile file) {
        String result = "";
        try {
            List<VaccinatedPeople> vaccinatedPeople = CSVUtil.loadCSVForModifyingVaccinatedPeopleInDB(file.getInputStream());
            vaccinatedPeopleRepository.saveAll(vaccinatedPeople);
            result = "Records saved in DB";
        } catch (IOException e) {
            throw new RuntimeException("Error while persisting data in DB: " + e.getMessage());
        }
        return result;
    }

    public Page<CovidPatient> getCovidPatients(Pageable pageable) {
        //CSVUtil.getCovidPatients(covidPatientRepository.findAll());
        return covidPatientRepository.findAll(pageable);
    }

    public Page<VaccinatedPeople> getVaccinatedPeople(Pageable pageable) {
        return vaccinatedPeopleRepository.findAll(pageable);
    }
}
