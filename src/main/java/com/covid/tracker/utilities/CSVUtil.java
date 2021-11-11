package com.covid.tracker.utilities;

import com.covid.tracker.beans.CovidPatient;
import com.covid.tracker.beans.VaccinatedPeople;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Component
public class CSVUtil {
    public static String TYPE = "text/csv";
    static String[] DETAILS = { "Name","Date of Birth","Address","Test Date", "Test Result"};
    static final String POS = "positive";
    private static final Logger log = LoggerFactory.getLogger(CSVUtil.class);

    public static boolean isCSVFile(MultipartFile file) {
        if (!TYPE.equals(file.getContentType())) {
            return false;
        }
        return true;
    }

    public static List<CovidPatient> loadCSVForCovidPatientsInDB(InputStream csvStream) {
        log.info("---------- LoadCSVForCovidPatientsInDB Begin ----------");
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(csvStream, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());){

            List<CovidPatient> covidPatients = new ArrayList<CovidPatient>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                CovidPatient covidPatient = new CovidPatient(
                        csvRecord.get(0),
                        new SimpleDateFormat("dd/MM/yyyy").parse(csvRecord.get(1)),
                        csvRecord.get(2),
                        new SimpleDateFormat("dd/MM/yyyy").parse(csvRecord.get(3)),
                        csvRecord.get(4).equalsIgnoreCase(POS)
                );
                covidPatients.add(covidPatient);
            }
            return covidPatients;
        } catch (IOException e) {
            log.info(e.getMessage());
            throw new RuntimeException("Error while parsing CSV file: " + e.getMessage());
        }catch (ParseException e){
            log.info(e.getMessage());
            throw new RuntimeException("Error while parsing CSV file dates: " + e.getMessage());
        }
    }

    public static List<VaccinatedPeople> loadCSVForVaccinatedPeopleInDB(InputStream csvStream) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(csvStream, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

            List<VaccinatedPeople> vaccinatedPeoples = new ArrayList<VaccinatedPeople>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                VaccinatedPeople vaccinatedPeople = new VaccinatedPeople(
                        csvRecord.get(0),
                        new SimpleDateFormat("dd/MM/yyyy").parse(csvRecord.get(1)),
                        csvRecord.get(2),
                        new SimpleDateFormat("dd/MM/yyyy").parse(csvRecord.get(3)),
                        new SimpleDateFormat("dd/MM/yyyy").parse(csvRecord.get(4))
                );
                vaccinatedPeoples.add(vaccinatedPeople);
            }
            return vaccinatedPeoples;
        } catch (IOException e) {
            throw new RuntimeException("Error while parsing CSV file: " + e.getMessage());
        } catch (ParseException e) {
            throw new RuntimeException("Error while parsing CSV file dates: " + e.getMessage());
        }
    }
}
