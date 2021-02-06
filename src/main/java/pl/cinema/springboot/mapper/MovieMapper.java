package pl.cinema.springboot.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import pl.cinema.springboot.model.Movie;

import java.util.List;

@Mapper
public interface MovieMapper {

    @Select("SELECT * FROM ANONYMOUS.MOVIE")
    List<Movie> findAll();
}
