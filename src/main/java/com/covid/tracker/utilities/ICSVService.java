package com.covid.tracker.utilities;

import com.covid.tracker.beans.CovidPatient;
import com.covid.tracker.beans.VaccinatedPeople;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public interface ICSVService {

    String addCovidPatients(MultipartFile file) throws IOException, ParseException;

    String modifyCovidPatients(MultipartFile file) throws IOException, ParseException ;

    String addVaccinatedPeople(MultipartFile file) throws IOException, ParseException;

    String modifyVaccinatedPeople(MultipartFile file) throws IOException, ParseException;

    List<CovidPatient> getCovidPatientsPerDayPerCountry(int pageNum, int pageSize, String dayMonthYear, String country);

    List<CovidPatient> getCovidPatientsPerMonthPerCountry(int pageNum, int pageSize, String monthYear, String country);

    List<CovidPatient> getCovidPatientsPerYearPerCountry(int pageNum, int pageSize, String year, String country);

    CovidPatient getCovidPatientByName(String name);

    VaccinatedPeople getVaccinatedPeopleByName(String name);

}
