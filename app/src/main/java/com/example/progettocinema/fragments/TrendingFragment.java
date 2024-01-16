package com.example.progettocinema.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.progettocinema.MainActivity;
import com.example.progettocinema.R;
import com.example.progettocinema.adapter.MovieAdapter;
import com.example.progettocinema.data.MovieAsyncResponse;
import com.example.progettocinema.data.Repository;
import com.example.progettocinema.model.Movie;

import java.util.ArrayList;

public class TrendingFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_trending,
                                container,
                                false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        new Repository().getMovies(new MovieAsyncResponse() {
            @Override
            public void processoTerminato(ArrayList<Movie> movies) {
                RecyclerView recyclerView = view.findViewById(R.id.lista_trending);
                MovieAdapter adapter = new MovieAdapter(movies);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void processoFallito(Exception e) {
                Log.d("Errore", e.getMessage());
            }
        });
    }
}
