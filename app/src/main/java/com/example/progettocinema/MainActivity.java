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
import com.example.progettocinema.fragments.MapsFragment;
import com.example.progettocinema.fragments.SearchFragment;
import com.example.progettocinema.fragments.TrendingFragment;
import com.example.progettocinema.model.Movie;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Class returnFragmentClass(String title) {
        if(title.equals("Trending")) {
            return TrendingFragment.class;
        }
        if(title.equals("Favorites")) {
            return FavoriteFragment.class;
        }
        if(title.equals("Search")) {
            return SearchFragment.class;
        }
        if(title.equals("Maps")) {
            return MapsFragment.class;
        }
        return TrendingFragment.class;
    }
    private void applicaFragment(String title) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, returnFragmentClass(title), null)
                .setReorderingAllowed(true)
                .commit();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        applicaFragment("Trending");
        BottomNavigationView bottomNav = findViewById(R.id.bottomNav);
        bottomNav.setOnItemSelectedListener(item -> {
            applicaFragment(item.getTitle().toString());
            return true;
        });
    }

}