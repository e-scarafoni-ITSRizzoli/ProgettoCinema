package com.example.progettovolley.data;

import com.example.progettovolley.model.Post;

import java.util.ArrayList;

public interface PostAsyncResponse {
    void processoTerminato(ArrayList<Post> posts);
    void processoFallito(Exception e);
}
