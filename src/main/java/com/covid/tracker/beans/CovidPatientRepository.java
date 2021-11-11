package com.covid.tracker.beans;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CovidPatientRepository extends JpaRepository<CovidPatient, Long> {

}
