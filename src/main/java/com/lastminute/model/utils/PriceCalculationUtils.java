package com.lastminute.model.utils;

import com.lastminute.model.Airline;
import com.lastminute.model.FlightInformation;
import com.lastminute.model.FlightParametersSearch;

import java.time.Duration;
import java.time.LocalDate;

/**
 * Created on 16/05/2017.
 */
public class PriceCalculationUtils {
    public static Double calculatePriceNoInfants (Double price, Integer numberAdults, Integer numberChildren ){
        return price * numberAdults + price * numberChildren * 0.67;
    }

    public static Double applyDepartureDateDiscount(LocalDate flightSearchDate, Double basePrice){
        LocalDate today = LocalDate.now();
        long distance = Duration.between(flightSearchDate.atStartOfDay(),today.atStartOfDay()).toDays();
        if(distance > 30) return basePrice * 0.8;
        if(distance > 15) return basePrice;
        if(distance > 2) return basePrice * 1.2;
        return basePrice * 1.5;
    }

    public static Double calculateInfantPrice(Double infantPrice, Integer numberInfants){
        return infantPrice * numberInfants;
    }

    public static Double roundTo2Decimals(Double number){
        if(number == 0) return 0D;
        return (double) (Math.round(number*100))/100;
    }

    public static Double calculateTotalPrice(FlightParametersSearch flightParametersSearch, Airline airline, FlightInformation flightInformation){
        Double price = 0D;
        if(flightParametersSearch.getInfants()!=0){
            price = calculateInfantPrice(airline.getInfantPrice(), flightParametersSearch.getInfants());
        }
        Double bookInAdvancePrice = applyDepartureDateDiscount(flightParametersSearch.getDeparture(), flightInformation.getBasePrice());
        price+= calculatePriceNoInfants(bookInAdvancePrice, flightParametersSearch.getAdults(), flightParametersSearch.getChildren());
        return roundTo2Decimals(price);
    }
}
