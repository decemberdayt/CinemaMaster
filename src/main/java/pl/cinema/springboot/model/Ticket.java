package pl.cinema.springboot.model;

public class Ticket {

    public int idTicket;
    public int idShow; /* foreign key */
    public int idSeat; /* foreign key */
    public int idHall; /* foreign key */
    public String buyerName;
    public String buyerSurname;
    public String status;
    public float price;
    public int reduced;

    public Ticket(int idTicket, int idShow, int idSeat, int idHall, String buyerName, String buyerSurname, String status, float price, int reduced) {
        this.idTicket = idTicket;
        this.idShow = idShow;
        this.idSeat = idSeat;
        this.idHall = idHall;
        this.buyerName = buyerName;
        this.buyerSurname = buyerSurname;
        this.status = status;
        this.price = price;
        this.reduced = reduced;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "idTicket=" + idTicket +
                "idShow=" + idShow +
                "idSeat=" + idSeat +
                "idHall=" + idHall +
                ", buyerName='" + buyerName + "\'" +
                ", buyerSurname='" + buyerSurname + "\'" +
                ", status='" + status + "\'" +
                "price=" + price +
                "reduced=" + reduced +
                "}";
    }
}