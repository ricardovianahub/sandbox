package net.ricardoviana.maprouter;

import java.util.HashMap;
import java.util.Map;

public class MapOfPoi {
    private final Map<String, Poi> pois = new HashMap<>();
    private final Map<String, Boolean> allowedRoutes = new HashMap<>(); // Tracks allowed routes

    private MapOfPoi() {}

    public static MapOfPoi fromDesign(String design, String[] additionalParams) {
        MapOfPoi map = new MapOfPoi();
        // Assuming the width of the map is the length of the design for a single row layout
        // For multi-row layouts, this logic might need to be adjusted based on additional input regarding row length.
        int width = design.length(); // This assumes a single-row layout for the given example

        for (int i = 0; i < design.length(); i++) {
            char c = design.charAt(i);
            if (c != '.') {
                int row = i / width; // This will be 0 for single-row designs
                int col = i % width; // This gives the correct column position
                map.pois.put(String.valueOf(c), new Poi(String.valueOf(c), row, col));
            }
        }

        // Register allowed routes based on additionalParams
        for (String route : additionalParams) {
            if (route.length() >= 2) { // Ensure the route string is valid
                map.allowedRoutes.put(route, true);
            }
        }
        return map;
    }

    public Poi getPoi(String identifier) {
        return pois.get(identifier);
    }

    public double distance(Poi poi1, Poi poi2) {
        return Math.sqrt(Math.pow(poi1.getColumn() - poi2.getColumn(), 2) + Math.pow(poi1.getRow() - poi2.getRow(), 2));
    }

    public double shortestTravel(Poi poiStart, Poi poiEnd) {
        String routeKey = poiStart.getIdentifier() + poiEnd.getIdentifier();
        if (poiStart.getIdentifier().equals(poiEnd.getIdentifier())) {
            throw new IllegalArgumentException("Both POIs cannot be the same");
        } else if (!allowedRoutes.containsKey(routeKey)) {
            throw new IllegalArgumentException("Cannot travel from letter to letter or unregistered path");
        }
        return distance(poiStart, poiEnd);
    }
}
