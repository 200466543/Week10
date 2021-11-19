package com.example.week10;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ApiResponse {
    private String totalResults;

    @SerializedName("Response")
    private String response;

    @SerializedName("Search")
    private Movie[] search;

    public String getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(String totalResults) {
        this.totalResults = totalResults;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public Movie[] getSearch() {
        return search;
    }

    public void setSearch(Movie[] search) {
        this.search = search;
    }
}
