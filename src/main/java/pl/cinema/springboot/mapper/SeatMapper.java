package pl.cinema.springboot.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import pl.cinema.springboot.model.Seat;
import pl.cinema.springboot.model.views.OccupancyOfSeats;

import java.util.List;

@Mapper
public interface SeatMapper {

    @Select("SELECT * FROM ANONYMOUS.SEAT WHERE IDHALL = #{idHall}")
    List<Seat> findAll(int idHall);

    @Select("SELECT S.HALLROW, S.IDSEAT, CASE WHEN T.IDTICKET IS NULL THEN 0 ELSE 1 END AS IFOCCUPIED\n" +
            "FROM ANONYMOUS.SEAT S\n" +
            "LEFT JOIN ANONYMOUS.TICKET T\n" +
            "ON\n" +
            "    S.IDSEAT = T.IDSEAT\n" +
            "    AND T.IDSHOW = #{idShow}\n" +
            "    AND T.STATUS != 'CANCELLED'\n" +
            "WHERE\n" +
            "    S.IDHALL = ( SELECT IDHALL FROM ANONYMOUS.SHOW WHERE IDSHOW = #{idShow} )\n" +
            "GROUP BY S.HALLROW, S.IDSEAT, CASE WHEN T.IDTICKET IS NULL THEN 0 ELSE 1 END")
    List<OccupancyOfSeats> getOccupancyPerShow(int idShow);
}
