package com.lastminute.flightsearch.model.services;

import com.lastminute.flightsearch.model.FlightSearchDataSetUpForTest;
import com.lastminute.flightsearch.services.FlightSearchService;
import com.lastminute.flightsearch.services.FlightSearchServiceImpl;
import com.lastminute.model.*;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * Created on 17/05/2017.
 */
public class AcceptanceCriteriaTest {
    private static FlightSearchDataSetUpForTest flightSearchDataSetUpForTest = new FlightSearchDataSetUpForTest();

    private static Map<FlightKey, List<FlightInformation>> flightKeyListMap = null;
    private static List<Airport> airportList = null;
    private static List<Airline> airlinesList = null;

    private static FlightSearchService flightSearchServiceImpl;

    Airport origin;
    Airport destination;
    LocalDate departure;
    Integer adults;
    Integer children;
    Integer infants;

    @BeforeClass
    public static void dataSetUp(){
        flightKeyListMap = flightSearchDataSetUpForTest.createFlightSetUp();
        airportList = flightSearchDataSetUpForTest.createAirportSetUp();
        airlinesList = flightSearchDataSetUpForTest.createAirlineSetUp();

        flightSearchServiceImpl = new FlightSearchServiceImpl();
    }

    @Test
    public void fromAmsterdamToFrankfurt_should_return3flights(){
        origin = airportList.get(6);
        destination = airportList.get(4);
        departure = flightSearchDataSetUpForTest.getDateRemovingDaysFromToday(31);
        adults = 1;
        children = 0;
        infants = 0;

        FlightParametersSearch flightParametersSearch = new FlightParametersSearch(origin, destination, departure, adults, children, infants);

        List<TripInformation> result = flightSearchServiceImpl.performSearch(flightParametersSearch, flightKeyListMap, airlinesList);
        Assert.assertEquals(3, result.size());

        Assert.assertEquals("TK2372", result.get(0).getAirline());
        Assert.assertEquals("TK2659", result.get(1).getAirline());
        Assert.assertEquals("LH5909", result.get(2).getAirline());

        Assert.assertTrue(result.get(0).getPrice().equals(157.6D));
        Assert.assertTrue(result.get(1).getPrice().equals(198.4D));
        Assert.assertTrue(result.get(2).getPrice().equals(90.4D));

    }

    @Test
    public void fromLondonToIstambul_should_return2flights(){
        origin = airportList.get(2);
        destination = airportList.get(5);
        departure = flightSearchDataSetUpForTest.getDateRemovingDaysFromToday(15);
        adults = 2;
        children = 1;
        infants = 1;
        FlightParametersSearch flightParametersSearch = new FlightParametersSearch(origin, destination, departure, adults, children, infants);

        List<TripInformation> result = flightSearchServiceImpl.performSearch(flightParametersSearch, flightKeyListMap, airlinesList);
        Assert.assertEquals(2, result.size());

        Assert.assertEquals("TK8891", result.get(0).getAirline());
        Assert.assertEquals("LH1085", result.get(1).getAirline());

        Assert.assertTrue(result.get(0).getPrice().equals(806D));
        Assert.assertTrue(result.get(1).getPrice().equals(481.19D));
    }

    @Test
    public void fromBarcelonaToMadrid_should_return2flights(){
        origin = airportList.get(1);
        destination = airportList.get(0);
        departure = flightSearchDataSetUpForTest.getDateRemovingDaysFromToday(2);
        adults = 1;
        children = 2;
        infants = 0;
        FlightParametersSearch flightParametersSearch = new FlightParametersSearch(origin, destination, departure, adults, children, infants);

        List<TripInformation> result = flightSearchServiceImpl.performSearch(flightParametersSearch, flightKeyListMap, airlinesList);
        Assert.assertEquals(2, result.size());

        Assert.assertEquals("IB2171", result.get(0).getAirline());
        Assert.assertEquals("LH5496", result.get(1).getAirline());

        Assert.assertTrue(result.get(0).getPrice().equals(909.09D));
        Assert.assertTrue(result.get(1).getPrice().equals(1028.43D));
    }
}
