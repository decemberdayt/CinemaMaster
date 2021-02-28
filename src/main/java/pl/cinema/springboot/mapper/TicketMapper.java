package pl.cinema.springboot.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.lang.Nullable;
import pl.cinema.springboot.model.Ticket;
import pl.cinema.springboot.model.views.PurchaseSummary;

import java.util.List;

@Mapper
public interface TicketMapper {

    @Select("SELECT \n" +
            "    T.IDTICKET\n" +
            "    , T.IDSHOW\n" +
            "    ,T.IDSEAT\n" +
            "    , T.IDHALL\n" +
            "    , T.BUYERNAME\n" +
            "    , T.BUYERSURNAME\n" +
            "    , CASE\n" +
            "        WHEN S.SHOWTIME > SYSTIMESTAMP THEN T.STATUS\n" +
            "        ELSE 'OVERDUE'\n" +
            "      END AS STATUS\n" +
            "    , T.PRICE\n" +
            "    , T.REDUCED\n" +
            "FROM ANONYMOUS.TICKET T\n" +
            "INNER JOIN ANONYMOUS.USER_TICKETS UT\n" +
            "ON\n" +
            "    T.IDTICKET = UT.IDTICKET\n" +
            "INNER JOIN ANONYMOUS.SHOW S\n" +
            "    ON S.IDSHOW = T.IDSHOW\n" +
            "WHERE UT.IDUSER = #{idUser}")
    @Nullable List<Ticket> findAllPerUser(int idUser);

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

    @Select("SELECT IDTICKET\n" +
            ",IDSHOW\n" +
            ",IDSEAT\n" +
            ",IDHALL\n" +
            ",BUYERNAME\n" +
            ",BUYERSURNAME\n" +
            ",STATUS\n" +
            ",PRICE\n" +
            ",REDUCED FROM ANONYMOUS.TICKET WHERE IDTICKET = #{idTicket})")
    @Nullable Ticket findTicketById(int idTicket);

    @Select("SELECT T.*\n" +
            "FROM \n" +
            "(\n" +
            "    SELECT * \n" +
            "    FROM ANONYMOUS.TICKET \n" +
            "    ORDER BY IDTICKET DESC\n" +
            ") AS T\n" +
            "LIMIT 1")
    @Nullable Ticket findMaxId();

    @Update("UPDATE ANONYMOUS.TICKET\n" +
            "SET STATUS = 'CANCELLED'\n" +
            "WHERE \n" +
            "    IDTICKET IN (#{idTicket})")
    void cancelTicket(int idTicket);

    @Select("SELECT \n" +
            "    T.IDTICKET\n" +
            "    , T.BUYERNAME\n" +
            "    , T.BUYERSURNAME\n" +
            "    , T.PRICE\n" +
            "    , T.REDUCED\n" +
            "    , SH.SHOWDATE\n" +
            "    , SH.SHOWTIME\n" +
            "    , M.TITLE\n" +
            "    , M.SUBTITLE\n" +
            "    , M.DURATIONMIN\n" +
            "    , H.HALLNAME\n" +
            "    , T.IDSEAT\n" +
            "    , S.HALLROW\n" +
            "    , T.STATUS\n" +
            "FROM ANONYMOUS.USER_TICKETS UT\n" +
            "INNER JOIN ANONYMOUS.TICKET T\n" +
            "ON\n" +
            "    T.IDTICKET = UT.IDTICKET\n" +
            "INNER JOIN ANONYMOUS.SHOW SH\n" +
            "ON\n" +
            "    SH.IDSHOW = T.IDSHOW\n" +
            "INNER JOIN ANONYMOUS.HALL H\n" +
            "ON\n" +
            "    H.IDHALL = T.IDHALL\n" +
            "INNER JOIN ANONYMOUS.MOVIE M\n" +
            "ON\n" +
            "    M.IDMOVIE = SH.IDMOVIE\n" +
            "INNER JOIN ANONYMOUS.SEAT S\n" +
            "ON\n" +
            "    S.IDSEAT = T.IDSEAT\n" +
            "    AND H.IDHALL = S.IDHALL\n" +
            "WHERE UT.IDUSER = #{idUser}" +
            "   AND T.IDTICKET = #{idTicket}")
    public PurchaseSummary getPurchaseSummary(int idTicket, int idUser);

    @Select("SELECT \n" +
            "    T.IDTICKET\n" +
            "    , T.BUYERNAME\n" +
            "    , T.BUYERSURNAME\n" +
            "    , T.PRICE\n" +
            "    , T.REDUCED\n" +
            "    , SH.SHOWDATE\n" +
            "    , SH.SHOWTIME\n" +
            "    , M.TITLE\n" +
            "    , M.SUBTITLE\n" +
            "    , M.DURATIONMIN\n" +
            "    , H.HALLNAME\n" +
            "    , T.IDSEAT\n" +
            "    , S.HALLROW\n" +
            "    , T.STATUS\n" +
            "FROM ANONYMOUS.USER_TICKETS UT\n" +
            "INNER JOIN ANONYMOUS.TICKET T\n" +
            "ON\n" +
            "    T.IDTICKET = UT.IDTICKET\n" +
            "INNER JOIN ANONYMOUS.SHOW SH\n" +
            "ON\n" +
            "    SH.IDSHOW = T.IDSHOW\n" +
            "INNER JOIN ANONYMOUS.HALL H\n" +
            "ON\n" +
            "    H.IDHALL = T.IDHALL\n" +
            "INNER JOIN ANONYMOUS.MOVIE M\n" +
            "ON\n" +
            "    M.IDMOVIE = SH.IDMOVIE\n" +
            "INNER JOIN ANONYMOUS.SEAT S\n" +
            "ON\n" +
            "    S.IDSEAT = T.IDSEAT\n" +
            "    AND H.IDHALL = S.IDHALL\n" +
            "WHERE UT.IDUSER = #{idUser}")
    @Nullable public List<PurchaseSummary> getAllPurchasePerUser (int idUser);

}
