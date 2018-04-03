package uj.jwzp.w4.logic;

import org.springframework.stereotype.Service;
import uj.jwzp.w4.model.Movie;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieLister {

    private final MovieFinder finder;

    public MovieLister(String fileName) {
        finder = new CSVMovieFinder(fileName);
    }

    public List<Movie> moviesDirectedBy(String name) {
        List<Movie> allMovies = finder.findAll();
        return allMovies.stream()
                .filter(m -> m.getDirector().equals(name))
                .collect(Collectors.toList());
    }
}
