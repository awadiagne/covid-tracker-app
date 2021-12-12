package com.covid.tracker.dto;

import java.util.Date;

public class VaccinatedPeopleDto {

    private Long id;
    private String name;
    private Date dateOfBirth;
    private String address;
    private Date firstVaccineDate;
    private Date secondVaccineDate;

    public VaccinatedPeopleDto(){}
    public VaccinatedPeopleDto(String name, Date dateOfBirth, String address, Date firstVaccineDate, Date secondVaccineDate) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.firstVaccineDate = firstVaccineDate;
        this.secondVaccineDate = secondVaccineDate;
    }

    public VaccinatedPeopleDto(Long id,String name, Date dateOfBirth, String address, Date firstVaccineDate, Date secondVaccineDate) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.firstVaccineDate = firstVaccineDate;
        this.secondVaccineDate = secondVaccineDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getFirstVaccineDate() {
        return firstVaccineDate;
    }

    public void setFirstVaccineDate(Date firstVaccineDate) {
        this.firstVaccineDate = firstVaccineDate;
    }

    public Date getSecondVaccineDate() {
        return secondVaccineDate;
    }

    public void setSecondVaccineDate(Date secondVaccineDate) {
        this.secondVaccineDate = secondVaccineDate;
    }
}
