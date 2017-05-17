package com.lastminute.flightsearch.services;

import com.lastminute.model.*;

import java.util.List;
import java.util.Map;

/**
 * Created on 17/05/2017.
 */
public interface FlightSearchService {
    List<TripInformation> performSearch(FlightParametersSearch flightParametersSearch, Map<FlightKey, List<FlightInformation>> flightInformationList, List<Airline> airlinesList);
}
