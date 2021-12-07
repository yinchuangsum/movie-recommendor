package com.yinchuangsum.movierecommendor.model.entity;

import com.yinchuangsum.movierecommendor.model.Movie;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class MovieImpl implements Movie {
    private String name;
}
