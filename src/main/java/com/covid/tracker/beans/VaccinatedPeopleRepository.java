package com.covid.tracker.beans;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VaccinatedPeopleRepository extends JpaRepository<VaccinatedPeople, Long> {
    public VaccinatedPeople findByName(String name);
}
