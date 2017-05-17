package com.lastminute.model.utils;

import com.lastminute.model.Airline;

import java.util.HashMap;
import java.util.List;

/**
 * Created on 16/05/2017.
 */
public class AirlinesUtils {
    public AirlinesUtils() {
    }

    public Airline getAirline(List<Airline> airlines, String airlineCode, HashMap<String, Airline> airlineHashMap){
        if(airlineHashMap.containsKey(airlineCode)){
            return airlineHashMap.get(airlineCode);
        } else {
            for(Airline airline: airlines){
                if(airlineCode.equals(airline.getCode())) {
                    airlineHashMap.put(airlineCode,airline);
                    airlines.remove(airline);
                    return airline;
                }
            }
        }
        return null;
    }
}
