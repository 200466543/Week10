package com.example.week10;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Collections;

public class ApiResponse {
    private String totalResults;

    @SerializedName("Response")
    private String response;

    @SerializedName("Search")
    private ArrayList<Movie> search;


    public ArrayList<Movie> getSearchSorted() {
        Collections.sort(search);
        return search;
    }


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

    public ArrayList<Movie> getSearch() {
        return search;
    }

    public void setSearch(ArrayList<Movie> search) {
        this.search = search;
    }
}
