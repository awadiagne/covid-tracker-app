package com.covid.tracker.utilities;

import com.covid.tracker.beans.CovidPatient;
import com.covid.tracker.beans.VaccinatedPeople;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Component
public class CSVUtil {
    public static String TYPE = "text/csv";
    static String[] DETAILS_FOR_COVID_PATIENTS = { "Name","Date of Birth","Address","Test Date", "Test Result"};
    static String[] DETAILS_FOR_VACCINATED_PEOPLE = { "Name","Date of Birth","Address","Test Date", "Test Result"};
    static final String POS = "positive";
    private static final Logger log = LoggerFactory.getLogger(CSVUtil.class);

    public static boolean isCSVFile(MultipartFile file) {
        return  TYPE.equals(file.getContentType());
    }

    public static List<CovidPatient> loadCSVForAddingCovidPatientsInDB(InputStream csvStream) throws IOException, ParseException{
        log.info("---------- loadCSVForAddingCovidPatientsInDB Begin ----------");
        BufferedReader fileReader = new BufferedReader(new InputStreamReader(csvStream, StandardCharsets.UTF_8));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());

            List<CovidPatient> covidPatients = new ArrayList<>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                String [] dayMonthYear = csvRecord.get(3).split("-");
                CovidPatient covidPatient = new CovidPatient(
                        csvRecord.get(0),
                        new SimpleDateFormat("dd-MM-yyyy").parse(csvRecord.get(1)),
                        csvRecord.get(2),
                        dayMonthYear[0],
                        dayMonthYear[1],
                        dayMonthYear[2],
                        csvRecord.get(4).equalsIgnoreCase(POS),
                        csvRecord.get(5)
                );
                covidPatients.add(covidPatient);
            }
            log.info("---------- loadCSVForAddingCovidPatientsInDB End ----------");
            return covidPatients;
    }

    public static List<VaccinatedPeople> loadCSVForAddingVaccinatedPeopleInDB(InputStream csvStream) throws IOException, ParseException{
        log.info("---------- loadCSVForAddingVaccinatedPeopleInDB Begin ----------");
        BufferedReader fileReader = new BufferedReader(new InputStreamReader(csvStream, StandardCharsets.UTF_8));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());

            List<VaccinatedPeople> vaccinatedPeoples = new ArrayList<>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                VaccinatedPeople vaccinatedPeople = new VaccinatedPeople(
                        csvRecord.get(0),
                        new SimpleDateFormat("dd-MM-yyyy").parse(csvRecord.get(1)),
                        csvRecord.get(2),
                        new SimpleDateFormat("dd-MM-yyyy").parse(csvRecord.get(3)),
                        new SimpleDateFormat("dd-MM-yyyy").parse(csvRecord.get(4))
                );
                vaccinatedPeoples.add(vaccinatedPeople);
            }
            log.info("---------- loadCSVForAddingVaccinatedPeopleInDB End ----------");
            return vaccinatedPeoples;
    }

    public static List<CovidPatient> loadCSVForModifyingCovidPatientsInDB (InputStream csvStream) throws IOException, ParseException {
        log.info("---------- loadCSVForModifyingCovidPatientsInDB Begin ----------");
        BufferedReader fileReader = new BufferedReader(new InputStreamReader(csvStream, StandardCharsets.UTF_8));
        CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());

            List<CovidPatient> covidPatients = new ArrayList<>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                String [] dayMonthYear = csvRecord.get(4).split("-");
                CovidPatient covidPatient = new CovidPatient(
                        Long.parseLong(csvRecord.get(0)),
                        csvRecord.get(1),
                        new SimpleDateFormat("dd-MM-yyyy").parse(csvRecord.get(2)),
                        csvRecord.get(3),
                        dayMonthYear[0],
                        dayMonthYear[1],
                        dayMonthYear[2],
                        csvRecord.get(5).equalsIgnoreCase(POS),
                        csvRecord.get(6)
                );
                covidPatients.add(covidPatient);
            }
            log.info("---------- loadCSVForModifyingCovidPatientsInDB End ----------");
            return covidPatients;

    }

    public static List<VaccinatedPeople> loadCSVForModifyingVaccinatedPeopleInDB (InputStream csvStream) throws IOException, ParseException{
        log.info("---------- loadCSVForModifyingVaccinatedPeopleInDB Begin ----------");
        BufferedReader fileReader = new BufferedReader(new InputStreamReader(csvStream, StandardCharsets.UTF_8));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());

            List<VaccinatedPeople> vaccinatedPeoples = new ArrayList<>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                VaccinatedPeople vaccinatedPeople = new VaccinatedPeople(
                        Long.parseLong(csvRecord.get(0)),
                        csvRecord.get(1),
                        new SimpleDateFormat("dd-MM-yyyy").parse(csvRecord.get(2)),
                        csvRecord.get(3),
                        new SimpleDateFormat("dd-MM-yyyy").parse(csvRecord.get(4)),
                        new SimpleDateFormat("dd-MM-yyyy").parse(csvRecord.get(5))
                );
                vaccinatedPeoples.add(vaccinatedPeople);
            }
            log.info("---------- loadCSVForModifyingVaccinatedPeopleInDB End ----------");
            return vaccinatedPeoples;
    }

    public static void getCovidPatients(List<CovidPatient> covidPatients) {
        log.info("---------- getCovidPatients Begin ----------");
        String dateTimeLabel = OffsetDateTime.now( ZoneOffset.UTC ).truncatedTo( ChronoUnit.MINUTES ).format( DateTimeFormatter.ofPattern( "uuuuMMdd'T'HHmmX" , Locale.US ) );
        String fileNamePath = "Report_from_" + dateTimeLabel + ".csv";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileNamePath));
             CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader("Name", "Date Of Birth", "Address", "Test Date", "Test Result"))) {

            for (CovidPatient covidPatient : covidPatients) {
                csvPrinter.printRecord(covidPatient.getName(), covidPatient.getDateOfBirth(), covidPatient.getAddress(), covidPatient.getDay() + "-" + covidPatient.getMonth() + "-" + covidPatient.getYear(), covidPatient.isTestResult());
            }
            csvPrinter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info("---------- getCovidPatients End ----------");
    }
}
