package pl.cinema.springboot.model.userModel;

public class UserTickets {

    private int idTicket;
    private int idUser;

    public UserTickets(int idTicket, int idUser) {
        this.idTicket = idTicket;
        this.idUser = idUser;
    }

    public int getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(int idTicket) {
        this.idTicket = idTicket;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
}
