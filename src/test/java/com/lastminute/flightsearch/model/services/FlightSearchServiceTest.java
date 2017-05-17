package com.lastminute.flightsearch.model.services;

import com.lastminute.flightsearch.model.FlightSearchDataSetUpForTest;
import com.lastminute.flightsearch.services.FlightSearchService;
import com.lastminute.flightsearch.services.FlightSearchServiceImpl;
import com.lastminute.model.*;
import com.lastminute.model.FlightParametersSearch;
import com.lastminute.model.utils.AirlinesUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

/**
 * Created on 17/05/2017.
 */
public class FlightSearchServiceTest {

    private FlightSearchDataSetUpForTest flightSearchDataSetUpForTest = new FlightSearchDataSetUpForTest();
    private List<Airline> airlinesList;
    private Map<FlightKey, List<FlightInformation>> flightKeyListMap;
    FlightParametersSearch flightParametersSearch;

    private static FlightSearchService flightSearchServiceImpl;



    @Before
    public void dataSetUp(){
        flightSearchServiceImpl = new FlightSearchServiceImpl();

        airlinesList = new ArrayList<>();

        flightParametersSearch = flightSearchDataSetUpForTest.defaultSearchValues();

        flightKeyListMap = mock(Map.class);
    }

    @Test
    public void performSearch_When_NullFlightParametersSearch_Should_ReturnEmptyList(){

        List<TripInformation> result = flightSearchServiceImpl.performSearch(null, flightKeyListMap, airlinesList);
        verify(flightKeyListMap, times(0)).get(any(FlightKey.class));

        Assert.assertEquals(0, result.size());
    }

    @Test
    public void performSearch_When_NullFlightKeyListMap_Should_ReturnEmptyList(){

        List<TripInformation> result = flightSearchServiceImpl.performSearch(flightParametersSearch, null, airlinesList);
        verify(flightKeyListMap, times(0)).get(any(FlightKey.class));

        Assert.assertEquals(0, result.size());
    }

    @Test
    public void performSearch_When_NullAirlinesList_Should_ReturnEmptyList(){

        List<TripInformation> result = flightSearchServiceImpl.performSearch(flightParametersSearch, flightKeyListMap, null);
        verify(flightKeyListMap, times(0)).get(any(FlightKey.class));

        Assert.assertEquals(0, result.size());
    }

    @Test
    public void performSearch_When_NoAvailableFlights_Should_ReturnEmptyList(){

        when(flightKeyListMap.get(any(FlightKey.class))).thenReturn(null);
        List<TripInformation> result = flightSearchServiceImpl.performSearch(flightParametersSearch, flightKeyListMap, airlinesList);
        verify(flightKeyListMap, times(1)).get(any(FlightKey.class));

        Assert.assertEquals(0, result.size());
    }

    @Test
    public void performSearch_When_EmptyFlightList_Should_ReturnEmptyList(){

        List<FlightInformation> listOfFlights = new ArrayList<>();

        when(flightKeyListMap.get(any(FlightKey.class))).thenReturn(listOfFlights);
        List<TripInformation> result = flightSearchServiceImpl.performSearch(flightParametersSearch, flightKeyListMap, airlinesList);
        verify(flightKeyListMap, times(1)).get(any(FlightKey.class));

        Assert.assertEquals(0, result.size());
    }

    @Test
    public void performSearch_When_2AvailableFlightOneUnknownAirline_Should_ReturnOnlyTripFromAcceptedAirline(){
        //flights 1234 and 9876, but only one of those airlines is recognised: 1234
        List<FlightInformation> listOfFlights = createDataForTest();
        Airline testAirline = new Airline("12", "b", 1d);
        airlinesList.add(testAirline);

        Map<FlightKey, List<FlightInformation>> flightInformationLMap = new HashMap<>();
        flightInformationLMap.put(new FlightKey(flightParametersSearch.getOrigin().getCode(), flightParametersSearch.getDestination().getCode()), listOfFlights);

        List<TripInformation> result = flightSearchServiceImpl.performSearch(flightParametersSearch, flightInformationLMap, airlinesList);

        Assert.assertEquals(1, result.size());
        Assert.assertEquals("1234", result.get(0).getAirline());
    }

    private List<FlightInformation> createDataForTest(){
        FlightInformation fi1 = new FlightInformation("AB", "CD", "1234", 1D);
        FlightInformation fi2 = new FlightInformation("ZY", "XW", "9876", 2D);
        List<FlightInformation> list = new ArrayList<>();
        list.add(fi1);
        list.add(fi2);
        return list;
    }
}
