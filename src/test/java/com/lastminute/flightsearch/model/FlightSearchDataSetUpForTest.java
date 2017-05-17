package com.lastminute.flightsearch.model;

import com.lastminute.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.function.Function;

/**
 * Created on 17/05/2017.
 */
public class FlightSearchDataSetUpForTest {

    private Airport origin;
    private Airport destination;
    private LocalDate departure;
    private Integer adults;
    private Integer children;
    private Integer infants;

    private final static String FILE_NAME_FLIGHT_INFORMATION = File.separator + "Flights.csv";
    private final static String FILE_NAME_AIRPORTS = File.separator + "Airports.csv";
    private final static String FILE_NAME_AIRLINES = File.separator + "Airlines.csv";

    public static Function<String, FlightInformation> mapToFlightInformation = (line) -> {
        String[] p = line.split(",");
        return new FlightInformation(p[0], p[1], p[2], Double.parseDouble(p[3]));
    };

    public static Function<String, Airport> mapToAirports = (line) -> {
        String[] p = line.split(",");
        return new Airport(p[0], p[1]);
    };

    public static Function<String, Airline> mapToAirlines = (line) -> {
        String[] p = line.split(",");
        return new Airline(p[0], p[1], Double.parseDouble(p[2]));
    };

    public Map<FlightKey, List<FlightInformation>> createFlightSetUp(){
        try {
            InputStream is = new FileInputStream(this.getClass().getResource(FILE_NAME_FLIGHT_INFORMATION).getFile());
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            return br.lines().map(mapToFlightInformation).collect(Collectors.groupingBy(FlightInformation::getFlightKey));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return new HashMap<>();
    }

    public List<Airport> createAirportSetUp(){
        return getSetupList(FILE_NAME_AIRPORTS, mapToAirports);
    }

    public List<Airline> createAirlineSetUp(){
        return getSetupList(FILE_NAME_AIRLINES, mapToAirlines);
    }

    private <T> List<T> getSetupList(String filename, Function<String, T> function) {
        try {
            InputStream is = new FileInputStream(this.getClass().getResource(filename).getFile());
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            return br.lines().map(function).collect(Collectors.toList());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public static LocalDate getDateRemovingDaysFromToday(long numberOfDays){
        LocalDate daysBeforeFlight = LocalDate.now();
        return daysBeforeFlight.minusDays(numberOfDays);
    }

    public FlightParametersSearch defaultSearchValues(){
        origin = new Airport("CODE", "NAME");
        destination = new Airport("CODE2", "NAME2");
        departure = getDateRemovingDaysFromToday(1);
        adults = 2;
        children = 1;
        infants = 1;
        return new FlightParametersSearch(origin, destination, departure, adults, children, infants);
    }
}
