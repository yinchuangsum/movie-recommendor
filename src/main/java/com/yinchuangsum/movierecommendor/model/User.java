package com.yinchuangsum.movierecommendor.model;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface User {
    List<User> getFriends();

    List<Movie> getMoviesWatched();

    List<Movie> recommendMovie();
}
