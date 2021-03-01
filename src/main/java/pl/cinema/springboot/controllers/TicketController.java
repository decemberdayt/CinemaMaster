package pl.cinema.springboot.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.cinema.springboot.model.Ticket;
import pl.cinema.springboot.model.views.PurchaseSummary;
import pl.cinema.springboot.services.TicketServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/tickets")
public class TicketController {


    protected final Logger log = Logger.getLogger(getClass().getName());
    private TicketServiceImpl ticketService;

    public TicketController(TicketServiceImpl ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("/all")
    public List<PurchaseSummary> getAll(@RequestParam int idUser) {
        try {
            return ticketService.getAllPurchasePerUser(idUser);
        } catch (Exception e) {
            PurchaseSummary noTickets = new PurchaseSummary();
            List<PurchaseSummary> noTicketList = new ArrayList<>();
            noTicketList.add(noTickets);
            return noTicketList;
        }
    }


    @PostMapping(value = "/addTicket/confirmed", produces = MediaType.APPLICATION_JSON_VALUE)
    public Ticket addTicket(@RequestBody Ticket ticket) {
        return ticketService.addTicket(ticket);
    }


    @PostMapping(value = "/addTickets/confirmed", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Ticket> addTickets(@RequestBody List<Ticket> tickets, @RequestParam int idUser) {
        return ticketService.addTickets(tickets,idUser);
    }

    @PostMapping(value = "/cancelTicket/confirmed", produces = MediaType.APPLICATION_JSON_VALUE)
    public Ticket cancelTicket(@RequestBody int idTicket) {
        return ticketService.cancelTicket(idTicket);
    }

    @GetMapping("/getTickets/")
    public List<PurchaseSummary> purchaseSummary(@RequestParam int[] idTicket, @RequestParam int idUser) {
        return ticketService.purchaseSummary(idTicket, idUser);
    }
}
