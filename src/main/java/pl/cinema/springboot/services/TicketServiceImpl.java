package pl.cinema.springboot.services;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import pl.cinema.springboot.mail.EmailSender;
import pl.cinema.springboot.mail.GeneratePdfReport;
import pl.cinema.springboot.mapper.TicketMapper;
import pl.cinema.springboot.mapper.UserTicketsMapper;
import pl.cinema.springboot.model.Ticket;
import pl.cinema.springboot.model.userModel.User;
import pl.cinema.springboot.model.userModel.UserRepository;
import pl.cinema.springboot.model.views.PurchaseSummary;

import javax.activation.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TicketServiceImpl {

    private TicketMapper ticketMapper;
    private UserTicketsMapper userTicketsMapper;
    private EmailSender emailSender;
    private UserRepository userRepository;

    public TicketServiceImpl(TicketMapper ticketMapper, UserTicketsMapper userTicketsMapper, EmailSender emailSender, UserRepository userRepository) {
        this.ticketMapper = ticketMapper;
        this.userTicketsMapper = userTicketsMapper;
        this.emailSender = emailSender;
        this.userRepository = userRepository;
    }

    public int getNextIdTicket()
    {
        int id;
        if(ticketMapper.findMaxId() == null)
        {
            id = 1;
        }
        else {
            id = ticketMapper.findMaxId().idTicket + 1;
        }
        return id;
    }

    public List<Ticket> getAll( int idUser){
        return ticketMapper.findAllPerUser(idUser);
    }

    public Ticket addTicket(Ticket ticket) {
        ticket.idTicket = getNextIdTicket();
        ticketMapper.insert(ticket);
        return(ticket);
    }

    public List<Ticket> addTickets(List<Ticket> tickets, int idUser) {

        int[] ticketsId = new int[tickets.size()];
        int ticketId;
        List<PurchaseSummary> var = new ArrayList<>();
        for (int i = 0; i < tickets.size(); i++)
        {
            ticketId = getNextIdTicket();
            tickets.get(i).idTicket = ticketId;
            ticketMapper.insert(tickets.get(i));
            userTicketsMapper.insert(ticketId, idUser);
            ticketsId[i] = ticketId;
        }

        var = purchaseSummary(ticketsId,idUser);
        Optional<User> user = userRepository.findById(Long.valueOf(idUser));
        DataSource bis = GeneratePdfReport.ticketsConfirmation(var);
        emailSender.sendEmail(user.get().getEmail(), "CinemaApp - potwierdzenie zakupu", bis);

        return tickets;
    }

    public void cancelTicket(int idTicket) {
            ticketMapper.cancelTicket(idTicket);
    }

    public List<PurchaseSummary> purchaseSummary(int[] idTicket, int idUser) {

        List<PurchaseSummary> var = new ArrayList<>();
        for (int i = 0; i < idTicket.length;  i++)
        {
            var.add(ticketMapper.getPurchaseSummary(idTicket[i], idUser));
        }
        return var;
    }

    @Nullable
    public List<PurchaseSummary> getAllPurchasePerUser (int idUser) {
        return ticketMapper.getAllPurchasePerUser(idUser);
    }

}
