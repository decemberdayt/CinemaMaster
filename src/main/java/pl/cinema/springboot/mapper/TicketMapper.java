package pl.cinema.springboot.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.lang.Nullable;
import pl.cinema.springboot.model.Seat;
import pl.cinema.springboot.model.Show;
import pl.cinema.springboot.model.Ticket;
import pl.cinema.springboot.model.views.PurchaseSummary;

import java.util.List;

@Mapper
public interface TicketMapper {

    @Select("SELECT * FROM ANONYMOUS.TICKET")
    List<Ticket> findAll();

    @Insert("INSERT INTO ANONYMOUS.TICKET (IDTICKET\n" +
            ",IDSHOW\n" +
            ",IDSEAT\n" +
            ",IDHALL\n" +
            ",BUYERNAME\n" +
            ",BUYERSURNAME\n" +
            ",STATUS\n" +
            ",PRICE\n" +
            ",REDUCED) VALUES (#{idTicket},#{idShow},#{idSeat},#{idHall},#{buyerName},#{buyerSurname},#{status},#{price},#{reduced})")
    void insert(Ticket ticket);

    @Select("SELECT *\n" +
            "FROM \n" +
            "(\n" +
            "    SELECT * \n" +
            "    FROM ANONYMOUS.TICKET \n" +
            "    ORDER BY IDTICKET DESC\n" +
            ")\n" +
            "WHERE ROWNUM = 1")
    @Nullable Ticket findMaxId();

    @Select("SELECT \n" +
            "    T.IDTICKET\n" +
            "    , T.BUYERNAME\n" +
            "    , T.BUYERSURNAME\n" +
            "    , M.TITLE\n" +
            "    , H.HALLNAME\n" +
            "    , SH.SHOWDATE\n" +
            "    , SH.SHOWTIME\n" +
            "    , S.IDSEAT\n" +
            "    , S.HALLROW\n" +
            "    , T.PRICE\n" +
            "FROM ANONYMOUS.TICKET T\n" +
            "INNER JOIN ANONYMOUS.SHOW SH\n" +
            "ON\n" +
            "    SH.IDSHOW = T.IDSHOW\n" +
            "INNER JOIN ANONYMOUS.MOVIE M\n" +
            "ON\n" +
            "    M.IDMOVIE = SH.IDMOVIE\n" +
            "INNER JOIN ANONYMOUS.HALL H\n" +
            "ON\n" +
            "    H.IDHALL = SH.IDHALL\n" +
            "INNER JOIN ANONYMOUS.SEAT S\n" +
            "ON\n" +
            "    S.IDSEAT = T.IDSEAT\n" +
            "WHERE T.IDTICKET IN (#{idTicket})")
    public PurchaseSummary getPurchaseSummary(int idTicket);


}
