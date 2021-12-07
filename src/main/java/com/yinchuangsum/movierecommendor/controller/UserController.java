package com.yinchuangsum.movierecommendor.controller;

import com.yinchuangsum.movierecommendor.model.Movie;
import com.yinchuangsum.movierecommendor.model.User;
import com.yinchuangsum.movierecommendor.model.entity.UserImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @GetMapping("/user/{id}/recommendedMovie")
    @ResponseBody
    public List<Movie> getRecommendedMovieList(@PathVariable Integer id) {
        User user = new UserImpl(id);

        return user.recommendMovie();
    }
}
