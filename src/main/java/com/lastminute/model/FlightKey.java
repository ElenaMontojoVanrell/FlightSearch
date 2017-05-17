package com.lastminute.model;

/**
 * Created on 16/05/2017.
 */
public class FlightKey {
    private String origin;
    private String destination;

    public FlightKey(String origin, String destination) {
        this.origin = origin;
        this.destination = destination;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    @Override
    public boolean equals (Object o){
        if (o instanceof FlightKey){
            FlightKey fk = (FlightKey) o;
            return this.getOrigin().equals(fk.getOrigin()) && this.getDestination().equals(fk.getDestination());
        }
        return false;
    }

    @Override
    public int hashCode(){
        return origin.hashCode() + destination.hashCode();
    }
}
