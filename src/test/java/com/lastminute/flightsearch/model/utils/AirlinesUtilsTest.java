package com.lastminute.flightsearch.model.utils;

import com.lastminute.model.Airline;
import com.lastminute.model.utils.AirlinesUtils;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created on 17/05/2017.
 */
public class AirlinesUtilsTest {

    private AirlinesUtils airlinesUtils = new AirlinesUtils();

    private static final String CODE = "CODE";
    private static final String NAME = "NAME";
    private static final Double PRICE = 1D;

    private Airline testAirline(){
        return new Airline(CODE, NAME, PRICE);
    }

    private List<Airline> setUpAirlines(){
        List<Airline> airlineList = new ArrayList<>();
        airlineList.add(testAirline());
        return airlineList;
    }

    private HashMap<String, Airline> setUpAirlineHashMap(){
        HashMap<String, Airline> airlineHashMap = new HashMap<>();
        airlineHashMap.put(CODE, testAirline());
        return airlineHashMap;
    }

    @Test
    public void getAirline_Should_ReturnAirline_When_ExistingOnHashMap(){
        Airline result = airlinesUtils.getAirline(null, CODE, setUpAirlineHashMap());
        Assert.assertEquals(result.getCode(), CODE);
        Assert.assertEquals(result.getInfantPrice(), PRICE);
        Assert.assertEquals(result.getName(), NAME);
    }

    @Test
    public void getAirline_Should_ReturnNull_When_EmptyListOfAirlines(){
        List<Airline> airlineList = new ArrayList<>();
        HashMap<String, Airline> airlineHashMap = new HashMap<>();
        Airline result = airlinesUtils.getAirline(airlineList, CODE, airlineHashMap);
        Assert.assertNull(result);
    }

    @Test
    public void getAirline_Should_ReturnNull_When_AirlineNotFound(){
        List<Airline> airlineList = new ArrayList<>();
        Double newPrice = PRICE + 2;
        airlineList.add(new Airline(CODE+"2", NAME+"2", newPrice));
        HashMap<String, Airline> airlineHashMap = new HashMap<>();
        Airline result = airlinesUtils.getAirline(airlineList, CODE, airlineHashMap);
        Assert.assertNull(result);
    }

    @Test
    public void getAirline_Should_ReturnAirlineAndAddToMap_When_AirlineFoundedNotExistingOnHashMap(){
        HashMap<String, Airline> airlineHashMap = new HashMap<>();
        List<Airline> airlineList = setUpAirlines();
        Airline result = airlinesUtils.getAirline(airlineList, CODE, airlineHashMap);

        Assert.assertEquals(result.getCode(), CODE);
        Assert.assertEquals(result.getInfantPrice(), PRICE);
        Assert.assertEquals(result.getName(), NAME);

        Assert.assertEquals(1, airlineHashMap.size());
        Assert.assertEquals(0, airlineList.size());
        Airline assertAirline = airlineHashMap.get(CODE);
        Assert.assertEquals(assertAirline, result);
    }

    @Test
    public void getAirline_Should_RemoveAirlineFromList_When_AirlineFoundedNotExistingOnHashMap(){
        HashMap<String, Airline> airlineHashMap = new HashMap<>();
        List<Airline> airlineList = setUpAirlines();
        Double newPrice = PRICE + 2;
        airlineList.add(new Airline(CODE+"2", NAME+"2", newPrice));

        Airline result = airlinesUtils.getAirline(airlineList, CODE, airlineHashMap);

        Assert.assertEquals(1, airlineList.size());
        Assert.assertEquals("CODE2" ,airlineList.get(0).getCode());
        Assert.assertEquals("NAME2" ,airlineList.get(0).getName());
        Assert.assertEquals(newPrice ,airlineList.get(0).getInfantPrice());
    }
}
