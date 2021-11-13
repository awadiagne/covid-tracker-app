package com.covid.tracker.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class VaccinatedPeople{

    private @Id @GeneratedValue Long id;
    private String name;
    private Date dateOfBirth;
    private String address;
    private Date firstVaccineDate;
    private Date secondVaccineDate;

    public VaccinatedPeople(String name, Date dateOfBirth, String address, Date firstVaccineDate, Date secondVaccineDate) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.firstVaccineDate = firstVaccineDate;
        this.secondVaccineDate = secondVaccineDate;
    }

    public VaccinatedPeople(Long id,String name, Date dateOfBirth, String address, Date firstVaccineDate, Date secondVaccineDate) {
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

    /*@Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof Patient))
            return false;
        Patient patient = (Patient) o;
        return Objects.equals(this.id, patient.id) && Objects.equals(this.name, patient.name)
                && Objects.equals(this.role, patient.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name, this.role);
    }*/

    @Override
    public String toString() {
        return "Patient {" + "id=" + this.id + ", name='" + this.name + '\'' + ", Date Of Birth ='" + this.dateOfBirth + '\'' + ", Address ='" + this.address  + '\'' + '}' + ", First Vaccine Date ='" + this.firstVaccineDate + '\'' + ", Second Vaccine Date ='" + this.secondVaccineDate;
    }
}
