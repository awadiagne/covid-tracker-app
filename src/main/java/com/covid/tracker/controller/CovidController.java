package com.covid.tracker.controller;

import com.covid.tracker.beans.CovidPatient;
import com.covid.tracker.utilities.CSVService;
import com.covid.tracker.utilities.CSVUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@RestController
class CovidController {

        private static final Logger log = LoggerFactory.getLogger(CovidController.class);
        @Autowired
        CSVService fileService;
    
        @GetMapping("/")
        public ResponseEntity<String>  home(){
            log.info("---------- Home Begin----------");
            String message = "Welcome to the upload covid cases and vaccinated people service ! \nThe CSV Files to upload are expected to be in this format :  {Name,Date of Birth,Address,Test Date,Test Result} for Covid Patients and {Name,Date of Birth,Address,First Vaccine Date,Second Vaccine Date} for Vaccinated People";
            log.info("Displaying : "+message);
            log.info("---------- Home End----------");
            return ResponseEntity.status(HttpStatus.OK).body(message);
        }
        
        //Bulk add Covid Patients
        @PostMapping("/addCovidPatients")
        public ResponseEntity<String> addCovidPatients(@RequestParam("file") MultipartFile file) throws IOException, ParseException {
            log.info("---------- Add Covid Patients Begin----------");
            String message = "";

                if (CSVUtil.isCSVFile(file)) {
                    message = "Uploaded the file successfully: " + file.getOriginalFilename();
                    message += "! " + fileService.addCovidPatients(file);
                    return ResponseEntity.status(HttpStatus.OK).body( "\" message \": \" "+ message +" \"");
                }
                message = "Please upload a csv file!";
                return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body("\" message \": \" "+ message +" \"");
        }

        //Bulk add  Vaccinated People
        @PostMapping("/addVaccinatedPeople")
        public ResponseEntity<String>  addVaccinatedPeople(@RequestParam("file") MultipartFile file) throws IOException, ParseException {
            log.info("---------- Add Vaccinated People Begin----------");
            String message = "";

            if (CSVUtil.isCSVFile(file)) {
                    message = "Uploaded the file successfully: " + file.getOriginalFilename();
                    message += "! " + fileService.addVaccinatedPeople(file);
                    return ResponseEntity.status(HttpStatus.OK).body("\" message \": \" " + message + " \"");
            }
            message = "Please upload a csv file!";
            return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body("\" message \": \" " + message + " \"");
        }

        // Bulk modify Covid Patients
        @PutMapping("/modifyCovidPatients")
        public ResponseEntity<String> modifyCovidPatients(@RequestParam("file") MultipartFile file) throws IOException, ParseException {
            log.info("---------- Modify Covid Patients Begin----------");
            String message = "";

            if (CSVUtil.isCSVFile(file)) {
                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                message += "! " + fileService.modifyCovidPatients(file);
                return ResponseEntity.status(HttpStatus.OK).body("\" message \": \" " + message + " \"");
            }
            message = "Please upload a csv file!";
            return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body("\" message \": \" " + message + " \"");
        }

        // Bulk modify Vaccinated People
        @PutMapping("/modifyVaccinatedPeople")
        public ResponseEntity<String> modifyVaccinatedPeople(@RequestParam("file") MultipartFile file) throws IOException, ParseException {
            log.info("---------- Modify Vaccinated People Begin----------");
            String message = "";

            if (CSVUtil.isCSVFile(file)) {
                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                message += "! " + fileService.modifyVaccinatedPeople(file);
                return ResponseEntity.status(HttpStatus.OK).body("\" message \": \" " + message + " \"");
            }
            message = "Please upload a csv file!";
            return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body("\" message \": \" " + message + " \"");
        }

        // Get Covid Patients per day per country
        @GetMapping("/getCovidPatientsPerDayPerCountry")
        public ResponseEntity<List<CovidPatient>> getCovidPatientsPerDayPerCountry(@RequestParam(defaultValue = "0") Integer pageNum,
                                                                                   @RequestParam(defaultValue = "10") Integer pageSize,
                                                                                   @RequestParam(defaultValue = "name") String sortBy,
                                                                                   @RequestParam(defaultValue = "") String dayMonthYear,
                                                                                   @RequestParam(defaultValue = "") String country)  {
            log.info("---------- -getCovidPatientsPerDayPerCountry Begin ---------");
            List<CovidPatient> covidPatientList = fileService.getCovidPatientsPerDayPerCountry(pageNum, pageSize, dayMonthYear, country);
            log.info("Covid Patient List found : " + covidPatientList.get(0) + "\n" + covidPatientList.get(0));
            log.info("---------- -getCovidPatientsPerDayPerCountry End ---------");
            return new ResponseEntity<List<CovidPatient>>(covidPatientList, new HttpHeaders(), HttpStatus.OK);
        }

        // Get Covid Patients per month per country
        @GetMapping("/getCovidPatientsPerMonthPerCountry")
        public ResponseEntity<List<CovidPatient>> getCovidPatientsPerMonthPerCountry(@RequestParam(defaultValue = "0") Integer pageNum,
                                                                                     @RequestParam(defaultValue = "10") Integer pageSize,
                                                                                     @RequestParam(defaultValue = "name") String sortBy,
                                                                                     @RequestParam String monthYear,
                                                                                     @RequestParam String country)  {
            List<CovidPatient> covidPatientList = fileService.getCovidPatientsPerMonthPerCountry(pageNum, pageSize, monthYear, country);
            return new ResponseEntity<List<CovidPatient>>(covidPatientList, new HttpHeaders(), HttpStatus.OK);
        }

        // Get Covid Patients per year per country
        @GetMapping("/getCovidPatientsPerYearPerCountry")
        public ResponseEntity<List<CovidPatient>> getCovidPatientsPerYearPerCountry(@RequestParam(defaultValue = "0") Integer pageNum,
                                                                                    @RequestParam(defaultValue = "10") Integer pageSize,
                                                                                    @RequestParam(defaultValue = "name") String sortBy,
                                                                                    @RequestParam String year,
                                                                                    @RequestParam String country)  {
            List<CovidPatient> covidPatientList = fileService.getCovidPatientsPerYearPerCountry(pageNum, pageSize, year, country);
            return new ResponseEntity<List<CovidPatient>>(covidPatientList, new HttpHeaders(), HttpStatus.OK);
        }

}