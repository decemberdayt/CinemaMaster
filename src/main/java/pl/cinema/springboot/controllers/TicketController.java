package pl.cinema.springboot.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import pl.cinema.springboot.mail.GeneratePdfReport;
import pl.cinema.springboot.mail.EmailSender;
import pl.cinema.springboot.mapper.TicketMapper;
import pl.cinema.springboot.mapper.UserTicketsMapper;
import pl.cinema.springboot.model.Ticket;
import pl.cinema.springboot.model.views.PurchaseSummary;
import pl.cinema.springboot.model.userModel.User;
import pl.cinema.springboot.model.userModel.UserRepository;
import pl.cinema.springboot.services.TicketServiceImpl;

import javax.activation.DataSource;
import java.util.*;
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
    public void cancelTicket(@RequestParam int idTicket) {
        ticketService.cancelTicket(idTicket);
    }

    @GetMapping("/getTickets/")
    public List<PurchaseSummary> purchaseSummary(@RequestParam int[] idTicket, @RequestParam int idUser) {
        return ticketService.purchaseSummary(idTicket, idUser);
    }
}
