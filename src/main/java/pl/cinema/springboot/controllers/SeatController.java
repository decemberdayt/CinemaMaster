package pl.cinema.springboot.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.cinema.springboot.mapper.SeatMapper;
import pl.cinema.springboot.model.Seat;
import pl.cinema.springboot.model.views.OccupancyOfSeats;

import java.util.List;
import java.util.logging.Logger;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/seats")
public class SeatController {

    private SeatMapper seatMapper;
    protected final Logger log = Logger.getLogger(getClass().getName());

    public SeatController(SeatMapper seatMapper) {
        this.seatMapper = seatMapper;
    }

    @GetMapping("/{idHall}")
    public List<Seat> getAll(@PathVariable int idHall) {
        return seatMapper.findAll(idHall);
    }

    @GetMapping("/occupancy/{idShow}")
    public List<OccupancyOfSeats> getOccupancyPerShow(@PathVariable int idShow)
    {
        return seatMapper.getOccupancyPerShow(idShow);
    }

}
