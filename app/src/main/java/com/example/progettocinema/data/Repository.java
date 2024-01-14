package com.example.progettocinema.data;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.progettocinema.controller.AppController;
import com.example.progettocinema.model.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Repository {
    String urlPost = "https://api.themoviedb.org/3/movie/popular?api_key=059b4ea3747f81799d697699f25eb33c";
    public void getMovies(final MovieAsyncResponse callback) {
        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(new Movie("Ciao", 10.0));
        Log.e("BEFORE REQUEST", "before request");


        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET, urlPost, null, response -> {
            try {
                Log.e("TRY", "INSIDE TRY");
                JSONArray results = response.getJSONArray("results");
                System.out.println(results);
                Log.d("Result", results.toString());
                for(int i = 0; i < results.length(); i++) {
                    JSONObject jsonMovie = results.getJSONObject(i);
                    double voteAvg = jsonMovie.getDouble("vote_average");
                    String title = jsonMovie.getString("title");
                    Movie movie = new Movie(title, voteAvg);
                    movies.add(movie);
                }
                callback.processoTerminato(movies);
            } catch (JSONException e) {
                e.printStackTrace();
                callback.processoFallito(e);
            }
        }, error -> {
            Log.d("Main", "Error");
        });
        AppController.getInstance().addToRequestQueue(request);
    }


}