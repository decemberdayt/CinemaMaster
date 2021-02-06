package pl.cinema.springboot.model.views;

import java.sql.Date;
import java.sql.Time;

public class AllShowPerMovie {
    public int idShow;
    public int idMovie;
    public int idHall;
    public String title;
    public Date showDate;
    public Time showTime;
    public int numberOfSeats;
    public int numberOfRows;

    public AllShowPerMovie(int idShow, int idMovie, int idHall, Date showDate, Time showTime, int numberOfSeats, int numberOfRows, String title) {
        this.idShow = idShow;
        this.idMovie = idMovie;
        this.idHall = idHall;
        this.showDate = showDate;
        this.showTime = showTime;
        this.numberOfSeats = numberOfSeats;
        this.numberOfRows = numberOfRows;
        this.title = title;
    }

    public int getIdShow() {
        return idShow;
    }

    public int getIdMovie() {
        return idMovie;
    }

    public int getIdHall() {
        return idHall;
    }

    public Date getShowDate() {
        return showDate;
    }

    public Time getShowTime() {
        return showTime;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public int getNumberOfRows() {
        return numberOfRows;
    }

    public String getTitle() {
        return title;
    }
}
