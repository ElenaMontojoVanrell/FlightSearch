package com.lastminute.model;

import com.lastminute.model.utils.PriceCalculationUtils;

/**
 * Created on 16/05/2017.
 */
public class TripInformationAdapter {
    private FlightParametersSearch flightParametersSearch;
    private FlightInformation flightInformation;
    private Airline airline;

    public TripInformationAdapter(FlightParametersSearch flightParametersSearch, FlightInformation flightInformation, Airline airline) {
        this.flightParametersSearch = flightParametersSearch;
        this.flightInformation = flightInformation;
        this.airline = airline;
    }

    public TripInformation convert(){
        TripInformation tripInformation = new TripInformation();
        tripInformation.setAirline(flightInformation.getAirline());
        tripInformation.setPrice(PriceCalculationUtils.calculateTotalPrice(flightParametersSearch,airline, flightInformation));
        return tripInformation;
    }
}
