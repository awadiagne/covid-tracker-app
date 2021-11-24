package com.covid.tracker.beans;

import javax.persistence.*;
import java.util.Date;

@Entity
@NamedQueries({
        @NamedQuery(name="findAllCovidPatientsPerDayPerCountry",
                query = "select c from CovidPatient c where c.day = :day and c.month = :month and c.year = :year and c.country = :country"),
        @NamedQuery(name="findAllCovidPatientsPerMonthPerCountry",
                query = "select c from CovidPatient c where c.month = :month and c.year = :year and c.country = :country"),
        @NamedQuery(name="findAllCovidPatientsPerYearPerCountry",
                query = "select c from CovidPatient c where c.year = :year and c.country = :country")
})
public class CovidPatient {

    private @Id @GeneratedValue Long id;
    private String name;
    private Date dateOfBirth;
    private String address;
    private String day;
    private String month;
    private String year;
    private boolean testResult;
    private String country;

    public CovidPatient(){}
    public CovidPatient(String name, Date dateOfBirth, String address, String day, String month, String year, boolean testResult, String country) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.day = day;
        this.month = month;
        this.year = year;
        this.testResult = testResult;
        this.country = country;
    }

    public CovidPatient(Long id, String name, Date dateOfBirth, String address, String day, String month, String year, boolean testResult, String country) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.day = day;
        this.month = month;
        this.year = year;
        this.testResult = testResult;
        this.country = country;
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

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public boolean isTestResult() {
        return testResult;
    }

    public void setTestResult(boolean testResult) {
        this.testResult = testResult;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Covid Patient {" + "id=" + this.id + ", name='" + this.name + '\'' + ", Date Of Birth ='" + this.dateOfBirth + '\'' + ", Address ='" + this.address  + '\'' + '}' + ", Test Date ='" + this.day + "-" + this.month + "-" + this.year + '\'' + ", Test Result ='" + this.testResult + ", Country ='" + this.country;
    }
}
