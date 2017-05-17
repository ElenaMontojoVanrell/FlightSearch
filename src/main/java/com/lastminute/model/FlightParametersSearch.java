package com.lastminute.model;

import java.time.LocalDate;

/**
 * Created on 16/05/2017.
 */
public class FlightParametersSearch {
    private Airport origin;
    private Airport destination;
    private LocalDate departure;
    private Integer adults;
    private Integer children;
    private Integer infants;

    public FlightParametersSearch(Airport origin, Airport destination, LocalDate departure, Integer adults, Integer children, Integer infants) {
        this.origin = origin;
        this.destination = destination;
        this.departure = departure;
        this.adults = adults;
        this.children = children;
        this.infants = infants;
    }

    public Airport getOrigin() {
        return origin;
    }

    public Airport getOriginNotNull() {
        if (origin == null) {
            return new Airport();
        }
        else {
            return origin;
        }
    }

    public void setOrigin(Airport origin) {
        this.origin = origin;
    }

    public Airport getDestination() {
        return destination;
    }

    public Airport getDestinationNotNull(){
        if(destination == null) {
            return new Airport();
        }
        else {
            return destination;
        }
    }

    public void setDestination(Airport destination) {
        this.destination = destination;
    }

    public LocalDate getDeparture() {
        return departure;
    }

    public void setDeparture(LocalDate departure) {
        this.departure = departure;
    }

    public Integer getAdults() {
        return adults;
    }

    public void setAdults(Integer adults) {
        this.adults = adults;
    }

    public Integer getChildren() {
        return children;
    }

    public void setChildren(Integer children) {
        this.children = children;
    }

    public Integer getInfants() {
        return infants;
    }

    public void setInfants(Integer infants) {
        this.infants = infants;
    }
}
