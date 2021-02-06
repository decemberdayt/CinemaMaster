package pl.cinema.springboot.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import pl.cinema.springboot.model.Show;
import pl.cinema.springboot.model.views.AllShowPerMovie;

import java.util.List;

@Mapper
public interface ShowMapper {

    @Select("SELECT * FROM ANONYMOUS.SHOW")
    List<Show> getAll();

    @Select("SELECT S.*, H.NUMBEROFROWS, H.NUMBEROFSEATS, M.TITLE\n" +
            "FROM ANONYMOUS.SHOW S\n" +
            "INNER JOIN ANONYMOUS.HALL H\n" +
            "ON\n" +
            "    H.IDHALL = S.IDHALL\n" +
            "INNER JOIN ANONYMOUS.MOVIE M\n" +
            "ON\n" +
            "    M.IDMOVIE = S.IDMOVIE\n" +
            "WHERE S.IDMOVIE = #{idMovie}")
    List<AllShowPerMovie> getAllShowPerMovie(int idMovie);

    @Select("SELECT S.*, H.NUMBEROFROWS, H.NUMBEROFSEATS, M.TITLE\n" +
            "FROM ANONYMOUS.SHOW S\n" +
            "INNER JOIN ANONYMOUS.HALL H\n" +
            "ON\n" +
            "    H.IDHALL = S.IDHALL\n" +
            "INNER JOIN ANONYMOUS.MOVIE M\n" +
            "ON\n" +
            "    M.IDMOVIE = S.IDMOVIE")
    List<AllShowPerMovie> getAllShow();
}
