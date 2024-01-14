package com.example.progettocinema.adapter;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.progettocinema.R;
import com.example.progettocinema.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private final ArrayList<Movie> movies;
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    public MovieAdapter(ArrayList<Movie> movies) {
        this.movies = movies;
    }

    @NonNull
    @Override
    public MovieAdapter.MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_layout, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.MovieViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.bind(movies.get(position));

        holder.itemView.setOnClickListener(v -> {
            Animation animation = AnimationUtils.loadAnimation(v.getContext(), R.anim.button_press_animation);
            holder.itemView.startAnimation(animation);

            if(onItemClickListener != null) {
                onItemClickListener.onItemClick(v, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }


    static class MovieViewHolder extends RecyclerView.ViewHolder {
        private final TextView name;
        private final TextView avg;
        private final ImageView icon;


        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            this.name = itemView.findViewById(R.id.text_view_project_title);
            this.avg = itemView.findViewById(R.id.text_view_project_description);
            this.icon = itemView.findViewById(R.id.image_view_project_icon);
        }

        public void bind(Movie movie) {
            name.setText(movie.getTitle());
            avg.setText(movie.getVoteAvg());
            String url = movie.getImageUrl();
            Picasso.get()
                            .load(url)
                                    .into(icon, new com.squareup.picasso.Callback() {
                                        @Override
                                        public void onSuccess() {
                                            Log.d("Picasso", "Image loaded successfully");
                                        }
                                        @Override
                                        public void onError(Exception e) {
                                            Log.e("Picasso", "Error in loading image");
                                            icon.setImageResource(R.drawable.ic_launcher_background);
                                        }
                                    });
        }
    }
}
