package pl.cinema.springboot.api;

import org.springframework.http.MediaType;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import pl.cinema.springboot.mapper.TicketMapper;
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
    protected final Logger log = Logger.getLogger(getClass().getName());

    public TicketApi(TicketMapper ticketMapper) {
        this.ticketMapper = ticketMapper;
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
    public List<Ticket> addTickets(@RequestBody List<Ticket> tickets) {
        for (int i = 0; i < tickets.size(); i++)
        {
            tickets.get(i).idTicket = getNextIdTicket();
            //tickets.get(i).idHall += 1;
            ticketMapper.insert(tickets.get(i));
        }
        return tickets;
    }

    @GetMapping("/getTickets/")
    public List<PurchaseSummary> purchaseSummary(@RequestParam int[] idTicket)
    {
        List<PurchaseSummary> var = new ArrayList<PurchaseSummary>();
        for (int i = 0; i < idTicket.length;  i++)
        {
            var.add(ticketMapper.getPurchaseSummary(idTicket[i]));
        }
        return var;
    }
}
