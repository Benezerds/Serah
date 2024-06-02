package com.neztech.serah.API.response;

import java.util.List;

public class GeocodeResponse {
    private List<Result> results;

    public List<Result> getResults() {
        return results;
    }

    public static class Result {
        private String formatted_address;

        public String getFormattedAddress() {
            return formatted_address;
        }
    }
}
