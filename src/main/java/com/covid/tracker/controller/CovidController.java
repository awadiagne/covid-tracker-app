package com.covid.tracker.controller;

import com.covid.tracker.beans.CovidPatient;
import com.covid.tracker.dto.CovidPatientDto;
import com.covid.tracker.utilities.CSVUtil;
import com.covid.tracker.utilities.ICSVService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
class CovidController {

        private static final Logger log = LoggerFactory.getLogger(CovidController.class);
        @Autowired
        ICSVService fileService;
        @Autowired
        private ModelMapper modelMapper;

        @GetMapping("/")
        public ResponseEntity<String>  home(){
            log.info("---------- Home Begin----------");
            String message = "Welcome to the upload covid cases and vaccinated people service ! \nThe CSV Files to upload are expected to be in this format :  {Name,Date of Birth,Address,Test Date,Test Result} for Covid Patients and {Name,Date of Birth,Address,First Vaccine Date,Second Vaccine Date} for Vaccinated People";
            log.info("Displaying : " + message);
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
        @ResponseBody
        public List<CovidPatientDto> getCovidPatientsPerDayPerCountry(@RequestParam(defaultValue = "0") Integer pageNum,
                                                                      @RequestParam(defaultValue = "10") Integer pageSize,
                                                                      @RequestParam String dayMonthYear,
                                                                      @RequestParam String country)  {
            log.info("---------- -getCovidPatientsPerDayPerCountry Begin ---------");

            List<CovidPatient> covidPatientList = fileService.getCovidPatientsPerDayPerCountry(pageNum, pageSize, dayMonthYear, country);
            if(!covidPatientList.isEmpty()){
                log.info("List fetched : " + covidPatientList.toString());
            }else{
                log.info("No records found !");
            }

            log.info("---------- -getCovidPatientsPerDayPerCountry End ---------");

            return covidPatientList.stream()
                    .map(this::convertCovidPatientToDto)
                    .collect(Collectors.toList());
        }

        // Get Covid Patients per month per country
        @GetMapping("/getCovidPatientsPerMonthPerCountry")
        @ResponseBody
        public List<CovidPatientDto> getCovidPatientsPerMonthPerCountry(@RequestParam(defaultValue = "0") Integer pageNum,
                                                                        @RequestParam(defaultValue = "10") Integer pageSize,
                                                                        @RequestParam String monthYear,
                                                                        @RequestParam String country)  {
            log.info("---------- -getCovidPatientsPerMonthPerCountry Begin ---------");

            List<CovidPatient> covidPatientList = fileService.getCovidPatientsPerMonthPerCountry(pageNum, pageSize, monthYear, country);
            if(!covidPatientList.isEmpty()){
                log.info("List fetched : " + covidPatientList.toString());
            }else{
                log.info("No records found !");
            }

            log.info("---------- -getCovidPatientsPerMonthPerCountry End ---------");

            return covidPatientList.stream()
                    .map(this::convertCovidPatientToDto)
                    .collect(Collectors.toList());        }

        // Get Covid Patients per year per country
        @GetMapping("/getCovidPatientsPerYearPerCountry")
        @ResponseBody
        public List<CovidPatientDto> getCovidPatientsPerYearPerCountry(@RequestParam(defaultValue = "0") Integer pageNum,
                                                                       @RequestParam(defaultValue = "10") Integer pageSize,
                                                                       @RequestParam String year,
                                                                       @RequestParam String country)  {
            log.info("---------- -getCovidPatientsPerYearPerCountry Begin ---------");

            List<CovidPatient> covidPatientList = fileService.getCovidPatientsPerYearPerCountry(pageNum, pageSize, year, country);
            if(!covidPatientList.isEmpty()){
                log.info("List fetched : " + covidPatientList.toString());
            }else{
                log.info("No records found !");
            }

            log.info("---------- -getCovidPatientsPerYearPerCountry End ---------");

            return covidPatientList.stream()
                    .map(this::convertCovidPatientToDto)
                    .collect(Collectors.toList());
        }

        private CovidPatientDto convertCovidPatientToDto(CovidPatient covidPatient) {
            CovidPatientDto covidPatientDto = modelMapper.map(covidPatient, CovidPatientDto.class);

            return covidPatientDto;
        }
}