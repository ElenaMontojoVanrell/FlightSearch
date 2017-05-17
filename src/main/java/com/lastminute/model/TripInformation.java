package com.lastminute.model;

/**
 * Created on 16/05/2017.
 */
public class TripInformation {
    private String airline;
    private Double price;

    public TripInformation() {
    }

    public TripInformation(String airline, Double price) {
        this.airline = airline;
        this.price = price;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
