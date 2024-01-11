package com.example.progettovolley;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.progettovolley.controller.AppController;
import com.example.progettovolley.data.MovieAsyncResponse;
import com.example.progettovolley.data.PostAsyncResponse;
import com.example.progettovolley.data.Repository;
import com.example.progettovolley.model.Post;
import com.example.progettovolley.model.Movie;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*new Repository().getPosts(new PostAsyncResponse() {
            @Override
            public void processoTerminato(ArrayList<Post> posts) {
                ListView listView = findViewById(R.id.mia_lista_view);
                ArrayAdapter<Post> arrayAdapter = new ArrayAdapter<Post>(
                        MainActivity.this,
                        android.R.layout.simple_list_item_2,
                        android.R.id.text1,
                        posts
                ) {
                  @NonNull
                  @Override
                  public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                      View view = super.getView(position, convertView, parent);
                      TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                      TextView text2 = (TextView) view.findViewById(android.R.id.text2);
                      text1.setText(posts.get(position).getTitle());
                      text2.setText(posts.get(position).getBody().substring(0,30));
                      return view;
                  }
                };
                listView.setAdapter(arrayAdapter);
            }

            @Override
            public void processoFallito(Exception e) {
                Log.d("Errore", e.getMessage());
            }
        });*/

        new Repository().getMovies(new MovieAsyncResponse() {
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
            }

            @Override
            public void processoFallito(Exception e) {
                Log.d("Errore", e.getMessage());
            }
        });
    }

}