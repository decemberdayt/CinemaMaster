package pl.cinema.springboot.model;

import java.util.List;

public class MovieGenre {

    public int idGenre;
    public String genreName;
    public List<Movie> moviesList;

    @Override
    public String toString() {
        return "Genre{" +
                "idGenre=" + idGenre +
                ", genreName='" + genreName + "\'" +
                "}";
    }
}
