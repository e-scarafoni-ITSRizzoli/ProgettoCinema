package com.example.progettocinema;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.example.progettocinema.data.DetailAsyncResponse;
import com.example.progettocinema.data.Repository;
import com.example.progettocinema.databinding.ActivityDetailBinding;
import com.example.progettocinema.model.Favorites;
import com.example.progettocinema.model.Movie;
import com.squareup.picasso.Picasso;

public class Detail extends AppCompatActivity {
    ActivityDetailBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_detail);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail);
        int id = getIntent().getIntExtra("movieId", 0);

        binding.titleDetail.setText("ID: " + id);

        new Repository().getMovieDetails(id, new DetailAsyncResponse() {
            @Override
            public void processoTerminato(Movie movie) {
                //tv.setText("Title: " + movie.getTitle());
                binding.titleDetail.setText(movie.getTitle());
                binding.description.setText("Description: \n" +movie.getDescription());
                binding.releaseDate.setText("Release date: " + movie.getReleaseDate());
                binding.tagline.setText('"'+ movie.getTagLine() + '"');
                binding.genres.setText(movie.getGenres().toString());
                Picasso.get()
                        .load(movie.getImageUrl())
                        .into(binding.moviePoster, new com.squareup.picasso.Callback() {
                            @Override
                            public void onSuccess() {
                                Log.d("Picasso", "Image loaded successfully");
                            }
                            @Override
                            public void onError(Exception e) {
                                Log.e("Picasso", "Error in loading image");
                            }});
            }

            @Override
            public void processoFallito(Exception e) {
                Log.d("Errore", e.getMessage());
            }
        });



        //Button bottone = findViewById(R.id.detailBack);
        binding.detailBack.setOnClickListener(v -> {
            finish();
        });
        // Button buttonFav = findViewById(R.id.buttonFav);
        if(Favorites.isInFavorites(id)) {
            binding.buttonFav.setText("Remove");
        }
        binding.buttonFav.setOnClickListener(v -> {
            Movie m = new Movie("prova", 10);
            m.setId(id);
            //Log.d("favs", Favorites.toMyString());
            if(Favorites.isInFavorites(m)) {
                Favorites.removeMovie(m);
                binding.buttonFav.setText("Add");
            } else {
                Favorites.addMovie(m);
                binding.buttonFav.setText("Remove");
            }
        });
    }
}