package com.covid.tracker.beans;

import org.springframework.data.repository.PagingAndSortingRepository;


public interface CovidPatientRepository extends PagingAndSortingRepository<CovidPatient, Long> {
    CovidPatient findByName(String name);

}
