package com.covid.tracker.controller;

import com.covid.tracker.beans.VaccinatedPeople;
import com.covid.tracker.beans.VaccinatedPeopleRepository;
import com.covid.tracker.utilities.CSVService;
import com.covid.tracker.utilities.CSVUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
class PatientController {

        /*@Autowired
        CovidPatientRepository covidPatientRepository;
        @Autowired
        VaccinatedPeopleRepository vaccinatedPeopleRepository;*/
        private static final Logger log = LoggerFactory.getLogger(PatientController.class);
        @Autowired
        CSVService fileService;

        /*PatientController(CovidPatientRepository covidPatientRepository, VaccinatedPeopleRepository vaccinatedPeopleRepository) {
            this.covidPatientRepository = covidPatientRepository;
            this.vaccinatedPeopleRepository = vaccinatedPeopleRepository;
        }*/
    
        @GetMapping("/")
        public ResponseEntity<String>  home(){
            String message = "Welcome to the upload covid cases service !";
            return ResponseEntity.status(HttpStatus.OK).body( "\" message \": \" "+ message +" \"");
        }
        
        //Bulk add Covid Patients
        @PostMapping("/addCovidPatients")
        public ResponseEntity<String> addCovidPatients(@RequestParam("file") MultipartFile file) {
             String message = "";

                if (CSVUtil.isCSVFile(file)) {
                    fileService.saveCovidPatients(file);
                    message = "Uploaded the file successfully: " + file.getOriginalFilename();
                    return ResponseEntity.status(HttpStatus.OK).body( "\" message \": \" "+ message +" \"");
                }
                message = "Please upload a csv file!";
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("\" message \": \" "+ message +" \"");
            //return covidPatientRepository.save(newCovidPatients);
        }

        //Bulk add  Vaccinated People
        @PostMapping("/addVaccinatedPeople")
        public ResponseEntity<String>  addVaccinatedPeople(@RequestParam("file") MultipartFile file) {
            String message = "";

            if (CSVUtil.isCSVFile(file)) {
                try {
                    fileService.saveVaccinatedPeople(file);
                    message = "Uploaded the file successfully: " + file.getOriginalFilename();
                    return ResponseEntity.status(HttpStatus.OK).body("\" message \": \" " + message + " \"");
                } catch (Exception e) {
                    message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                    return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("\" message \": \" " + message + " \"");
                }
            }
            message = "Please upload a csv file!";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("\" message \": \" " + message + " \"");
            //return covidPatientRepository.save(newCovidPatients);        }
        }
        // Single item

        // tag::get-single-item[]
        /*@GetMapping("/patients/{id}")
        EntityModel<Patient> one(@PathVariable Long id) {

            Patient patient = repository.findById(id)
                    .orElseThrow(() -> new PatientNotFoundException(id));

            return EntityModel.of(patient,
                    linkTo(methodOn(PatientController.class).one(id)).withSelfRel(),
                    linkTo(methodOn(PatientController.class).all()).withRel("patients"));
        }
        // end::get-single-item[]

        @PutMapping("/patients/{id}")
        Patient replacePatient(@RequestBody Patient newPatient, @PathVariable Long id) {

            return repository.findById(id)
                    .map(patient -> {
                        patient.setName(newPatient.getName());
                        patient.setRole(newPatient.getRole());
                        return repository.save(patient);
                    }) //
                    .orElseGet(() -> {
                        newPatient.setId(id);
                        return repository.save(newPatient);
                    });
        }*/
}
