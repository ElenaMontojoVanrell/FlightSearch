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
    private List<Airport> airportList;
    private List<Airline> airlinesList;
    private Map<FlightKey, List<FlightInformation>> flightKeyListMap;
    private AirlinesUtils airlinesUtils;
    FlightParametersSearch flightParametersSearch;

    private static FlightSearchService flightSearchServiceImpl;



    @Before
    public void dataSetUp(){
        flightSearchServiceImpl = new FlightSearchServiceImpl();

        airportList = new ArrayList<>();
        airlinesList = new ArrayList<>();

        flightParametersSearch = flightSearchDataSetUpForTest.defaultSearchValues();

        flightKeyListMap = mock(Map.class);
        airlinesUtils = mock(AirlinesUtils.class);
    }

    @Test
    public void performSearch_When_NoAvailableFlights_Should_ReturnEmptyList(){

        when(flightKeyListMap.get(any(FlightKey.class))).thenReturn(null);
        List<TripInformation> result = flightSearchServiceImpl.performSearch(flightParametersSearch, flightKeyListMap, airlinesList);

        verify(flightKeyListMap, times(1)).get(any(FlightKey.class));
        verify(airlinesUtils,never()).getAirline(any(List.class), any(String.class), any(HashMap.class));

        Assert.assertEquals(0, result.size());

    }

    @Test
    public void performSearch_When_EmptyFlightList_Should_ReturnEmptyList(){

        List<FlightInformation> listOfFlights = new ArrayList<>();

        when(flightKeyListMap.get(any(FlightKey.class))).thenReturn(listOfFlights);

        List<TripInformation> result = flightSearchServiceImpl.performSearch(flightParametersSearch, flightKeyListMap, airlinesList);

        verify(flightKeyListMap, times(1)).get(any(FlightKey.class));
        verify(airlinesUtils,times(0)).getAirline(any(List.class), any(String.class), any(HashMap.class));

        Assert.assertEquals(0, result.size());

    }

    @Test
    public void performSearch_When_2AvailableFlightOneUnknownAirline_Should_ReturnOnlyOneOption(){

        List<FlightInformation> listOfFlights = createDataForTest();
        Airline testAirline = new Airline("a", "b", 1d);

        when(flightKeyListMap.get(any(FlightKey.class))).thenReturn(listOfFlights);
        when(airlinesUtils.getAirline(any(List.class), eq(listOfFlights.get(0).getAirline().substring(0,2)), any(HashMap.class))).thenReturn(null);
        when(airlinesUtils.getAirline(any(List.class), eq(listOfFlights.get(1).getAirline().substring(0,2)), any(HashMap.class))).thenReturn(testAirline);

        List<TripInformation> result = flightSearchServiceImpl.performSearch(flightParametersSearch, flightKeyListMap, airlinesList);

        verify(flightKeyListMap, times(1)).get(any(FlightKey.class));

        Assert.assertEquals(0, result.size());
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
