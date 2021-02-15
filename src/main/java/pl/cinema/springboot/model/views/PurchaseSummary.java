package pl.cinema.springboot.model.views;

import java.sql.Time;
import java.sql.Date;

public class PurchaseSummary {
    public int idTicket;
    public String buyerName;
    public String buyerSurname;
    public float price;
    public int reduced;
    public Date showDate;
    public Time showTime;
    public String title;
    public String subTitle;
    public int durationMin;
    public String hallName;
    public int idSeat;
    public int hallRow;

    public PurchaseSummary() {};

    public PurchaseSummary(int idTicket, String buyerName, String buyerSurname, float price, int reduced, Date showDate, Time showTime, String title, String subTitle, int durationMin, String hallName, int idSeat, int hallRow) {
        this.idTicket = idTicket;
        this.buyerName = buyerName;
        this.buyerSurname = buyerSurname;
        this.price = price;
        this.reduced = reduced;
        this.showDate = showDate;
        this.showTime = showTime;
        this.title = title;
        this.subTitle = subTitle;
        this.durationMin = durationMin;
        this.hallName = hallName;
        this.idSeat = idSeat;
        this.hallRow = hallRow;
    }

    public int getIdTicket() {
        return idTicket;
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

    public int getReduced() {
        return reduced;
    }

    public Date getShowDate() {
        return showDate;
    }

    public Time getShowTime() {
        return showTime;
    }

    public String getTitle() {
        return title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public int getDurationMin() {
        return durationMin;
    }

    public String getHallName() {
        return hallName;
    }

    public int getIdSeat() {
        return idSeat;
    }

    public int getHallRow() {
        return hallRow;
    }
}
