package com.example.progettovolley.model;

public class Movie {
    private String title;
    private double voteAvg;

    public Movie(String title, double voteAvg) {
        this.title = title;
        this.voteAvg = voteAvg;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVoteAvg() {
        return ""+voteAvg;
    }

    public void setVoteAvg(double voteAvg) {
        this.voteAvg = voteAvg;
    }
}
