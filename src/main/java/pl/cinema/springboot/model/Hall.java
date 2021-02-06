package pl.cinema.springboot.model;

public class Hall {

    public int idHall;
    public String hallName;
    public int numberOfSeats;
    public int numberOfRows;

    @Override
    public String toString() {
        return "Hall{" +
                "idHall=" + idHall +
                ", hallName='" + hallName + "\'" +
                ", numberOfSeats=" + numberOfSeats +
                ", numberOfRows=" + numberOfRows +
                "}";
    }
}
