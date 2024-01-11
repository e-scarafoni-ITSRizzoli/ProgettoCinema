package com.example.progettovolley.data;

import com.example.progettovolley.model.Movie;
import com.example.progettovolley.model.Post;

import java.util.ArrayList;

public interface MovieAsyncResponse {
    void processoTerminato(ArrayList<Movie> movies);
    void processoFallito(Exception e);
}
