package pl.cinema.springboot.controllers;

import org.springframework.web.bind.annotation.*;
import pl.cinema.springboot.mapper.MovieMapper;
import pl.cinema.springboot.mapper.ShowMapper;
import pl.cinema.springboot.model.Movie;
import pl.cinema.springboot.model.views.AllShowPerMovie;

import java.util.List;
import java.util.logging.Logger;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/movies")
public class MovieController {
    private MovieMapper movieMapper;
    private ShowMapper showMapper;
    protected final Logger log = Logger.getLogger(getClass().getName());

    public MovieController(MovieMapper movieMapper, ShowMapper showMapper) {
        this.movieMapper = movieMapper;
        this.showMapper = showMapper;
    }

    @GetMapping
    public List<Movie> getAll()
    {
        return movieMapper.findAll();
    }

    @GetMapping("/{idMovie}")
    public List<AllShowPerMovie> getAllShowPerMovie(@PathVariable int idMovie) {
        return showMapper.getAllShowPerMovie(idMovie);
    }

    @GetMapping("/allShows")
    public List<AllShowPerMovie> getAllShow() {
        return showMapper.getAllShow();
    }
}
