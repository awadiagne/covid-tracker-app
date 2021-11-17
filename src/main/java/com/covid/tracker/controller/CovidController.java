package com.covid.tracker.controller;

import com.covid.tracker.beans.CovidPatient;
import com.covid.tracker.beans.VaccinatedPeople;
import com.covid.tracker.utilities.CSVService;
import com.covid.tracker.utilities.CSVUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;

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
                    fileService.addCovidPatients(file);
                    message = "Uploaded the file successfully: " + file.getOriginalFilename();
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
                    fileService.addVaccinatedPeople(file);
                    message = "Uploaded the file successfully: " + file.getOriginalFilename();
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
                fileService.modifyCovidPatients(file);
                message = "Uploaded the file successfully: " + file.getOriginalFilename();
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
                fileService.modifyVaccinatedPeople(file);
                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body("\" message \": \" " + message + " \"");
            }
            message = "Please upload a csv file!";
            return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body("\" message \": \" " + message + " \"");
        }

        // Get Covid Patients
        @GetMapping("/getCovidPatients")
        public Page<CovidPatient> getCovidPatients(Pageable pageable)  {
            return fileService.getCovidPatients(pageable);
        }

        // Get Vaccinated People
        @GetMapping("/getVaccinatedPeople")
        public Page<VaccinatedPeople> getVaccinatedPeople(Pageable pageable)  {
            return fileService.getVaccinatedPeople(pageable);
        }
}