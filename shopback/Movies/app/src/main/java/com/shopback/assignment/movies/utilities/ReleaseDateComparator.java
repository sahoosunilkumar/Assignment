package com.shopback.assignment.movies.utilities;

import com.sunilsahoo.inventorycontroller.entity.Movie;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;

/**
 * Created by sunilkumarsahoo on 2/27/17.
 */

public class ReleaseDateComparator implements Comparator<Movie> {
    private static final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    public int compare(Movie movie1, Movie movie2) {
        try {
            return formatter.parse(movie1.getReleaseDate()).compareTo(formatter.parse(movie2.getReleaseDate()));
        } catch (ParseException e) {
            e.printStackTrace();
            return -1;
        }
    }
}