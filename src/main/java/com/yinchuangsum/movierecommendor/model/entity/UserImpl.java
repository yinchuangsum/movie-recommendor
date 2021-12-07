package com.yinchuangsum.movierecommendor.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yinchuangsum.movierecommendor.model.Movie;
import com.yinchuangsum.movierecommendor.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@NoArgsConstructor
public class UserImpl implements User {
    @Getter
    @Setter
    private Integer id;
    @JsonIgnore
    private static final transient RestTemplate REST_TEMPLATE = new RestTemplate();
    @JsonIgnore
    private static final String SOCIAL_MEDIA_USER_URL_PREFIX = "http://localhost:8080/mysocialnetwork/user/";
    @JsonIgnore
    private static final String FRIENDS_ENDPOINT = "/friends";
    @JsonIgnore
    private static final String WATCHED_MOVIE_ENDPOINT = "/movies";

    public UserImpl(Integer id) {
        this.id = id;
    }

    @Override
    public List<User> getFriends() {
        UserImpl[] response = REST_TEMPLATE.getForObject(SOCIAL_MEDIA_USER_URL_PREFIX + id.toString() + FRIENDS_ENDPOINT, UserImpl[].class);
        return Arrays.asList(Objects.requireNonNull(response));
    }

    @Override
    public List<Movie> getMoviesWatched() {
        MovieImpl[] response = REST_TEMPLATE
                .getForObject(SOCIAL_MEDIA_USER_URL_PREFIX + id.toString() + WATCHED_MOVIE_ENDPOINT, MovieImpl[].class);
        return Arrays.asList(Objects.requireNonNull(response));
    }

    @Override
    public List<Movie> recommendMovie() {
        HashSet<User> userHashSet = getFriendsAndFriendsOfFriends();

        HashMap<Movie, Integer> movieCount = getMovieCount(userHashSet);

        Set<Movie> movieSet = movieCount.keySet();

        return movieSet.stream()
                // for inverse sorting
                .sorted(Comparator.comparing(movie -> -movieCount.getOrDefault(movie, 0)))
                .collect(Collectors.toList());
    }

    private HashSet<User> getFriendsAndFriendsOfFriends() {
        List<User> friends = getFriends();
        HashSet<User> userHashSet = new HashSet<>(friends);

        friends.forEach(friend -> userHashSet.addAll(friend.getFriends()));
        return userHashSet;
    }

    private HashMap<Movie, Integer> getMovieCount(Collection<User> users) {
        HashMap<Movie, Integer> movieCount = new HashMap<>();

        users.forEach(user ->
                user.getMoviesWatched().forEach(movie ->
                        movieCount.put(movie, movieCount.getOrDefault(movie, 0) + 1)));

        return movieCount;
    }

    @Override
    public boolean equals(Object obj) {
        // self check
        if (this == obj)
            return true;
        // null check
        if (obj == null)
            return false;
        // type check and cast
        if (getClass() != obj.getClass())
            return false;
        UserImpl user = (UserImpl) obj;

        // field comparison
        return this.id.equals(user.getId());
    }

    @Override
    public int hashCode() {
        return getId();
    }
}
