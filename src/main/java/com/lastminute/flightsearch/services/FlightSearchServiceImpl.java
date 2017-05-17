package com.lastminute.flightsearch.services;

import com.lastminute.model.*;
import com.lastminute.model.utils.AirlinesUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created on 17/05/2017.
 */
public class FlightSearchServiceImpl implements FlightSearchService{

    private AirlinesUtils airlinesUtils;

    public FlightSearchServiceImpl() {
        airlinesUtils = new AirlinesUtils();
    }

    public List<TripInformation> performSearch(FlightParametersSearch flightParametersSearch, Map<FlightKey, List<FlightInformation>> flightInformationList, List<Airline> airlinesList) {

        List<TripInformation> result = new ArrayList<>();
        if(airlinesList== null) return result;

        List<FlightInformation> availableFlights = flightInformationList.get(new FlightKey(flightParametersSearch.getOriginNotNull().getCode(), flightParametersSearch.getDestinationNotNull().getCode()));
        if (availableFlights == null) {
            return result;
        }

        HashMap<String, Airline> airlineHashMap = new HashMap<>();
        List<Airline> airlinesListAux = new ArrayList<>(airlinesList);
        for (FlightInformation flightInformation : availableFlights) {
            Airline airline = airlinesUtils.getAirline(airlinesListAux, flightInformation.getAirline().substring(0, 2), airlineHashMap);
            if (airline != null) {
                result.add(new TripInformationAdapter(flightParametersSearch, flightInformation, airline).convert());
            }
        }
        return result;
    }
}
