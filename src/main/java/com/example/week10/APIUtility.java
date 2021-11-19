package com.example.week10;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class APIUtility {

    /**
     * This method will read the file called JSONFile in the root
     * of the project and create an APIResponse object
     * @return
     */
    public static ApiResponse getMoviesJsonFile() {
        ApiResponse response = null;

        //create a GSON object
        Gson gson = new Gson();

        try(
                FileReader fileReader = new FileReader("JSONFile");
                JsonReader jsonReader = new JsonReader(fileReader);
                ) {
            response = gson.fromJson(jsonReader, ApiResponse.class);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }

    /**
     * This will call the OMDB api with the specified search term
     */
    public static ApiResponse getMoviesFromOMDB(String searchTerm) throws IOException, InterruptedException {
        searchTerm = searchTerm.trim().replace(" ", "%20");

        String uri = "http://www.omdbapi.com/?apikey=4a1010ab&s="+searchTerm;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(uri)).build();

        //this sends the result from the API to a file
//        HttpResponse<Path> response = client.send(httpRequest, HttpResponse
//                                                                .BodyHandlers
//                                                                .ofFile(Paths.get("jsonData.json")));
//        return getMoviesJsonFile();

        //this approach stores the API response to a String and then creates objects
        HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        String jsonString = response.body();

        Gson gson = new Gson();
        ApiResponse apiResponse = null;

        try{
            apiResponse  = gson.fromJson(jsonString, ApiResponse.class);
        }catch(Exception e)
        {
            e.printStackTrace();
        }

        return apiResponse;
    }
}
