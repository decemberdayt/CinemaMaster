package pl.cinema.springboot.model.views;

public class OccupancyOfSeats {
    public int hallRow;
    public int idSeat;
    public int ifOccupied;

    public OccupancyOfSeats(int hallRow, int idSeat, int ifOccupied) {
        this.hallRow = hallRow;
        this.idSeat = idSeat;
        this.ifOccupied = ifOccupied;
    }

    public int getHallRow() {
        return hallRow;
    }

    public int getIdSeat() {
        return idSeat;
    }

    public int getIfOccupied() {
        return ifOccupied;
    }
}
