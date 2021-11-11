package com.covid.tracker.utilities;

import java.util.stream.Collectors;

import com.covid.tracker.beans.CovidPatient;
import com.covid.tracker.beans.CovidPatientRepository;
import com.covid.tracker.beans.VaccinatedPeople;
import com.covid.tracker.beans.VaccinatedPeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public void saveCovidPatients(MultipartFile file) {
        try {
            List<CovidPatient> covidPatients = CSVUtil.loadCSVForCovidPatientsInDB(file.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
    }

    public void saveVaccinatedPeople(MultipartFile file) {
        try {
            List<VaccinatedPeople> vaccinatedPeople = CSVUtil.loadCSVForVaccinatedPeopleInDB(file.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
    }

    public List<CovidPatient> getAllCovidPatients() {
        return covidPatientRepository.findAll();
    }

    public List<VaccinatedPeople> getAllVaccinatedPeople() {
        return vaccinatedPeopleRepository.findAll();
    }
}
