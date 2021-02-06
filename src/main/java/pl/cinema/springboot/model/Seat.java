package pl.cinema.springboot.model;

public class Seat {

    public int idSeat;
    public int idHall;
    //public Hall hall;
    public int hallRow;


    @Override
    public String toString() {
        return "Seat{" +
                "idSeat=" + idSeat +
                "idHall=" + idHall +
                "hallRow=" + hallRow +
                "}";
    }

}
