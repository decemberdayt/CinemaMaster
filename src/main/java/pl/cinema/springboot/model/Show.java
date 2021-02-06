package pl.cinema.springboot.model;

import java.sql.Time;
import java.sql.Date;

public class Show {

    public int idShow;
    public Movie movie;
    public Hall hall;
    public Date showDate;
    public Time showTime;

    @Override
    public String toString() {
        return "Seat{" +
                "idShow=" + idShow +
                "idMovie=" + movie.idMovie +
                "idHall=" + hall.idHall +
                "showDate=" + showDate +
                "showTime=" + showTime +
                "}";
    }

}
