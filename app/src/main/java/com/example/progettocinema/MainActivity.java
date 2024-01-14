package com.example.progettocinema;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.progettocinema.data.MovieAsyncResponse;
import com.example.progettocinema.data.Repository;
import com.example.progettocinema.fragments.FavoriteFragment;
import com.example.progettocinema.fragments.TrendingFragment;
import com.example.progettocinema.model.Movie;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private void applicaFragment(String title) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, title.equals("Trending") ? TrendingFragment.class : FavoriteFragment.class, null)
                .setReorderingAllowed(true)
                .commit();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /*new Repository().getMovies(new MovieAsyncResponse() {
            @Override
            public void processoTerminato(ArrayList<Movie> movies) {
                ListView listView = findViewById(R.id.mia_lista_view);
                ArrayAdapter<Movie> arrayAdapter = new ArrayAdapter<Movie>(
                        MainActivity.this,
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
        });*/
        applicaFragment("Trending");
        BottomNavigationView bottomNav = findViewById(R.id.bottomNav);
        bottomNav.setOnItemSelectedListener(item -> {
            applicaFragment(item.getTitle().toString());
            return true;
        });
    }

}