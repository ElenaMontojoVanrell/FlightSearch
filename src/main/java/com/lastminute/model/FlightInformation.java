package com.lastminute.model;

/**
 * Created on 16/05/2017.
 */
public class FlightInformation {
    private FlightKey flightKey;
    private String airline;
    private Double basePrice;

    public FlightInformation(String origin, String destination, String airline, Double basePrice) {
        this.flightKey = new FlightKey(origin, destination);
        this.airline = airline;
        this.basePrice = basePrice;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public Double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(Double basePrice) {
        this.basePrice = basePrice;
    }

    public FlightKey getFlightKey() {
        return flightKey;
    }

    public void setFlightKey(FlightKey flightKey) {
        this.flightKey = flightKey;
    }
}
