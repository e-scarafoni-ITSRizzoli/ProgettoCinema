package com.example.progettocinema.data;

import com.example.progettocinema.model.Movie;

import java.util.ArrayList;

public interface DetailAsyncResponse {
    void processoTerminato(Movie movie);

    void processoFallito(Exception e);
}
