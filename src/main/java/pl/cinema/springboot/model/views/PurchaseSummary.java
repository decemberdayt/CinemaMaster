package pl.cinema.springboot.model.views;

import java.sql.Time;
import java.sql.Date;

public class PurchaseSummary {
    public int idTicket;
    public String buyerName;
    public String buyerSurname;
    public String title;
    public String hallName;
    public Date showDate;
    public Time showTime;
    public int idSeat;
    public int hallRow;
    public float price;

    public PurchaseSummary() {};

    public PurchaseSummary(int idTicket, String buyerName, String buyerSurname, String title, String hallName, Date showDate, Time showTime, int idSeat, int hallRow, float price) {
        this.idTicket = idTicket;
        this.buyerName = buyerName;
        this.buyerSurname = buyerSurname;
        this.title = title;
        this.hallName = hallName;
        this.showDate = showDate;
        this.showTime = showTime;
        this.idSeat = idSeat;
        this.hallRow = hallRow;
        this.price = price;
    }

    public int getIdTicket() {
        return idTicket;
    }

    public String getTitle() {
        return title;
    }

    public Date getShowDate() {
        return showDate;
    }

    public Time getShowTime() {
        return showTime;
    }

    public String getHallName() {
        return hallName;
    }

    public int getHallRow() {
        return hallRow;
    }

    public int getIdSeat() {
        return idSeat;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public String getBuyerSurname() {
        return buyerSurname;
    }

    public float getPrice() {
        return price;
    }
}
