package pl.cinema.springboot.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserTicketsMapper {

    @Insert("INSERT INTO ANONYMOUS.USER_TICKETS (IDUSER, IDTICKET) VALUES (#{idUser},#{idTicket})")
    void insert(int idTicket, int idUser);



}
