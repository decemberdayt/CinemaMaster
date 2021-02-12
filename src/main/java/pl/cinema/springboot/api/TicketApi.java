package pl.cinema.springboot.api;

import org.springframework.http.MediaType;
import org.springframework.lang.Nullable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.cinema.springboot.mapper.TicketMapper;
import pl.cinema.springboot.mapper.UserTicketsMapper;
import pl.cinema.springboot.model.Ticket;
import pl.cinema.springboot.model.views.PurchaseSummary;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/tickets")
public class TicketApi {

    private TicketMapper ticketMapper;
    private UserTicketsMapper userTicketsMapper;
    protected final Logger log = Logger.getLogger(getClass().getName());

    public TicketApi(TicketMapper ticketMapper, UserTicketsMapper userTicketsMapper) {
        this.ticketMapper = ticketMapper;
        this.userTicketsMapper = userTicketsMapper;
    }

    @GetMapping
    public List<Ticket> getAll() {return ticketMapper.findAll();}

    @GetMapping("/max")
    public Ticket findMaxId() {
        Ticket id = ticketMapper.findMaxId();
        return id;
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


    @PostMapping(value = "/addTicket/confirmed", produces = MediaType.APPLICATION_JSON_VALUE)
    public Ticket addTicket(@RequestBody Ticket ticket) {
        ticket.idTicket = getNextIdTicket();
        ticketMapper.insert(ticket);
        return(ticket);
    }


    @PostMapping(value = "/addTickets/confirmed", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Ticket> addTickets(@RequestBody List<Ticket> tickets, @RequestParam int idUser) {
        for (int i = 0; i < tickets.size(); i++)
        {
            tickets.get(i).idTicket = getNextIdTicket();
            ticketMapper.insert(tickets.get(i));
            userTicketsMapper.insert(tickets.get(i).idTicket, idUser);
        }
        return tickets;
    }

    @GetMapping("/getTickets/")
    public List<PurchaseSummary> purchaseSummary(@RequestParam int[] idTicket, @RequestParam int idUser)
    {
        List<PurchaseSummary> var = new ArrayList<PurchaseSummary>();
        for (int i = 0; i < idTicket.length;  i++)
        {
            var.add(ticketMapper.getPurchaseSummary(idTicket[i], idUser));
        }
        return var;
    }
}
