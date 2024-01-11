package com.example.progettocinema.data;

import com.example.progettocinema.model.Movie;

import java.util.ArrayList;

public interface MovieAsyncResponse {
    void processoTerminato(ArrayList<Movie> movies);
    void processoFallito(Exception e);
}
