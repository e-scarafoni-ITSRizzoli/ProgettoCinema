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

import com.example.progettocinema.MainActivity;
import com.example.progettocinema.R;
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
                ListView listView = view.findViewById(R.id.lista_trending);
                ArrayAdapter<Movie> arrayAdapter = new ArrayAdapter<Movie>(
                        view.getContext(),
                        android.R.layout.simple_list_item_2,
                        android.R.id.text1,
                        movies
                ) {
                    @NonNull
                    @Override
                    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                        View view = super.getView(position, convertView, parent);
                        TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                        TextView text2 = (TextView) view.findViewById(android.R.id.text2);
                        text1.setText(movies.get(position).getTitle());
                        text2.setText(movies.get(position).getVoteAvg());
                        return view;
                    }
                };
                listView.setAdapter(arrayAdapter);
                listView.setOnItemClickListener((parent, view, position, id) -> {
                    Movie movie = arrayAdapter.getItem(position);
                    Log.e("CLICK", movie.getTitle());
                });
            }

            @Override
            public void processoFallito(Exception e) {
                Log.d("Errore", e.getMessage());
            }
        });
    }
}
