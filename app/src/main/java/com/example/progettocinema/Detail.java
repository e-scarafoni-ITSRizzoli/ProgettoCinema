package com.example.progettocinema;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.example.progettocinema.model.Favorites;
import com.example.progettocinema.model.Movie;

public class Detail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        int id = getIntent().getIntExtra("movieId", 0);
        TextView tv = findViewById(R.id.textView2);
        tv.setText("ID: " + id);
        Button bottone = findViewById(R.id.detailBack);
        bottone.setOnClickListener(v -> {
            finish();
        });
        Button buttonFav = findViewById(R.id.buttonFav);
        if(Favorites.isInFavorites(id)) {
            buttonFav.setText("Remove");
        }
        buttonFav.setOnClickListener(v -> {
            Movie m = new Movie("prova", 10);
            m.setId(id);
            //Log.d("favs", Favorites.toMyString());
            if(Favorites.isInFavorites(m)) {
                Favorites.removeMovie(m);
                buttonFav.setText("Add");
            } else {
                Favorites.addMovie(m);
                buttonFav.setText("Remove");
            }
        });
    }
}