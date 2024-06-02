package com.neztech.serah.API.response;

import java.util.List;

public class PlacesResponse {
    private List<Result> results;

    public List<Result> getResults() {
        return results;
    }

    public static class Result {
        private String name;
        private Geometry geometry;

        public String getName() {
            return name;
        }

        public Geometry getGeometry() {
            return geometry;
        }
    }

    public static class Geometry {
        private Location location;

        public Location getLocation() {
            return location;
        }
    }

    public static class Location {
        private double lat;
        private double lng;

        public double getLat() {
            return lat;
        }

        public double getLng() {
            return lng;
        }
    }
}

