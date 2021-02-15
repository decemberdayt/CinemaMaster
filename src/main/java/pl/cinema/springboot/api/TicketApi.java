package pl.cinema.springboot.api;

import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import pl.cinema.springboot.GeneratePdfReport;
import pl.cinema.springboot.controllers.EmailController;
import pl.cinema.springboot.mail.EmailSender;
import pl.cinema.springboot.mapper.TicketMapper;
import pl.cinema.springboot.mapper.UserTicketsMapper;
import pl.cinema.springboot.model.Ticket;
import pl.cinema.springboot.model.views.PurchaseSummary;
import pl.cinema.springboot.userModel.User;
import pl.cinema.springboot.userModel.UserRepository;

import javax.activation.DataSource;
import java.util.*;
import java.util.logging.Logger;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/tickets")
public class TicketApi {

    private TicketMapper ticketMapper;
    private UserTicketsMapper userTicketsMapper;
    protected final Logger log = Logger.getLogger(getClass().getName());
    private EmailController emailController;
    private final EmailSender emailSender;
    private final TemplateEngine templateEngine;
    private UserRepository userRepository;

    public TicketApi(TicketMapper ticketMapper, UserTicketsMapper userTicketsMapper, EmailController emailController, EmailSender emailSender, TemplateEngine templateEngine, UserRepository userRepository) {
        this.ticketMapper = ticketMapper;
        this.userTicketsMapper = userTicketsMapper;
        this.emailController = emailController;
        this.emailSender = emailSender;
        this.templateEngine = templateEngine;
        this.userRepository = userRepository;
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
        int[] ticketsId = new int[tickets.size()];
        int ticketId;
        for (int i = 0; i < tickets.size(); i++)
        {
            ticketId = getNextIdTicket();
            tickets.get(i).idTicket = ticketId;
            ticketMapper.insert(tickets.get(i));
            userTicketsMapper.insert(ticketId, idUser);
            ticketsId[i] = ticketId;
        }
        purchaseSummary(ticketsId,idUser);
        return tickets;
    }

    @GetMapping("/getTickets/")
    public List<PurchaseSummary> purchaseSummary(@RequestParam int[] idTicket, @RequestParam int idUser) {
        List<PurchaseSummary> var = new ArrayList<>();
        for (int i = 0; i < idTicket.length;  i++)
        {
            var.add(ticketMapper.getPurchaseSummary(idTicket[i], idUser));
        }
        Optional<User> user = userRepository.findById(Long.valueOf(idUser));

        DataSource bis = GeneratePdfReport.ticketsConfirmation(var);

        Context context = new Context();
        context.setVariable("header", "Potwierdzenie dokonania zakupy na CinemaApp");
        context.setVariable("title", "Dziękujemy za zaufanie naszej firmie!");
        context.setVariable("description", "W załączniku znajdziesz plik z potwierdzeniem zakupu. \n" +
                "Plik pdf możesz również pobrać z naszej stronie po zalogowaniu. \n" +
                "Liczymy, że jeszcze do nas wpadniesz! Miłego seansu :)");
        String body = null;
        try {
            body = templateEngine.process("template.html", context);
        } catch (Exception e) {
            //e.printStackTrace();
        }
        emailSender.sendEmail(user.get().getEmail(), "CinemaApp - potwierdzenie zakupu", body, bis);

        return var;
    }
}
