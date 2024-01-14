package com.example.progettocinema.fragments;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.progettocinema.R;
import com.example.progettocinema.adapter.MovieAdapter;
import com.example.progettocinema.data.MovieAsyncResponse;
import com.example.progettocinema.data.Repository;
import com.example.progettocinema.databinding.FragmentSearchBinding;
import com.example.progettocinema.model.Movie;

import java.util.ArrayList;

public class SearchFragment extends Fragment {
    //FragmentSearchBinding binding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_search,
                container,
                false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        EditText editText = view.findViewById(R.id.inputSearch);
        TextView placeholder = view.findViewById(R.id.searchTextPlaceholder);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.d("Edit text", "Testo cambiato");
               String newQuery = editText.getText().toString();
               placeholder.setVisibility(View.GONE);
                new Repository().getMoviesFromQuery(newQuery, new MovieAsyncResponse() {
                    @Override
                    public void processoTerminato(ArrayList<Movie> movies) {
                        //ListView listView = view.findViewById(R.id.lista_trending);
                        RecyclerView recyclerView = view.findViewById(R.id.lista_search);
                        MovieAdapter adapter = new MovieAdapter(movies);
                        recyclerView.setAdapter(adapter);
                    }

                    @Override
                    public void processoFallito(Exception e) {
                        Log.d("Errore", e.getMessage());
                    }
                });
            }
        });
    }
}
