package com.lastminute.flightsearch.model.utils;

import com.lastminute.flightsearch.model.FlightSearchDataSetUpForTest;
import com.lastminute.model.Airline;
import com.lastminute.model.FlightInformation;
import com.lastminute.model.FlightParametersSearch;
import com.lastminute.model.utils.PriceCalculationUtils;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created on 17/05/2017.
 */
public class PriceCalculationUtilsTest {

    private static final String CODE = "CODE";
    private static final String NAME = "NAME";
    private static final Double PRICE = 1D;

    private Airline testAirline(){
        return new Airline(CODE, NAME, PRICE);
    }

    private void assertRoundTo2DecimalsResult(Double value, Double expected){
        Double result = PriceCalculationUtils.roundTo2Decimals(value);
        Assert.assertEquals(expected, result);
    }

    @Test
    public void roundTo2Decimals_Should_Work(){
        Double value = 2.005D;
        Double expected = 2.01D;
        assertRoundTo2DecimalsResult(value, expected);

        value = 2.006D;
        expected = 2.01D;
        assertRoundTo2DecimalsResult(value, expected);

        value = 2.004D;
        expected = 2.00D;
        assertRoundTo2DecimalsResult(value, expected);
    }

    @Test
    public void calculatePriceNoInfants_Should_ReturnTestValues(){
        Double expectedValue = 48.12D;
        Double value = PriceCalculationUtils.calculatePriceNoInfants(12D, 2, 3);
        Assert.assertEquals(expectedValue, PriceCalculationUtils.roundTo2Decimals(value));
    }

    private Double getApplyDepartureDateDiscount(long numberOfDays){
        Double value = PriceCalculationUtils.applyDepartureDateDiscount(FlightSearchDataSetUpForTest.getDateRemovingDaysFromToday(numberOfDays), 100D);
        return PriceCalculationUtils.roundTo2Decimals(value);
    }

    @Test
    public void applyDepartureDateDiscount_When_31DaysAdvance_Should_Return80percent(){
        Double expectedValue = 80D;
        Double result = getApplyDepartureDateDiscount(31);
        Assert.assertEquals(expectedValue, result);
    }

    @Test
    public void applyDepartureDateDiscount_When_30DaysAdvance_Should_Return100percent(){
        Double expectedValue = 100D;
        Double result = getApplyDepartureDateDiscount(30);
        Assert.assertEquals(expectedValue, result);
    }

    @Test
    public void applyDepartureDateDiscount_When_15DaysAdvance_Should_Return120percent(){
        Double expectedValue = 120D;
        Double result = getApplyDepartureDateDiscount(15);
        Assert.assertEquals(expectedValue, result);
    }

    @Test
    public void applyDepartureDateDiscount_When_16DaysAdvance_Should_Return100percent(){
        Double expectedValue = 100D;
        Double result = getApplyDepartureDateDiscount(16);
        Assert.assertEquals(expectedValue, result);
    }

    @Test
    public void applyDepartureDateDiscount_When_2DaysAdvance_Should_Return150percent(){
        Double expectedValue = 150D;
        Double result = getApplyDepartureDateDiscount(2);
        Assert.assertEquals(expectedValue, result);
    }

    @Test
    public void applyDepartureDateDiscount_When_3DaysAdvance_Should_Return120percent(){
        Double expectedValue = 120D;
        Double result = getApplyDepartureDateDiscount(3);
        Assert.assertEquals(expectedValue, result);
    }

    @Test
    public void calculateTotalPrice_Should_ReturnExpectedValues(){
        Double expectedValue = 401.5D;

        FlightSearchDataSetUpForTest flightSearchDataSetUpForTest = new FlightSearchDataSetUpForTest();
        FlightParametersSearch flightParametersSearch = flightSearchDataSetUpForTest.defaultSearchValues();

        Airline airline = testAirline();
        FlightInformation flightInformation = new FlightInformation("A", "B", "AB", 100D);

        Double result = PriceCalculationUtils.calculateTotalPrice(flightParametersSearch, airline, flightInformation);

        Assert.assertEquals(expectedValue, result);
    }
}
