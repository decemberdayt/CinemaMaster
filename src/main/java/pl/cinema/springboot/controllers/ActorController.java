package pl.cinema.springboot.controllers;

import org.springframework.web.bind.annotation.*;
import pl.cinema.springboot.mapper.ActorMapper;
import pl.cinema.springboot.model.Actor;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/actors")
public class ActorController {

    private ActorMapper actorMapper;
    protected final Logger log = Logger.getLogger(getClass().getName());

    public ActorController(ActorMapper actorMapper) {
        this.actorMapper = actorMapper;
    }

    @GetMapping
    public List<Actor> getAll() {return actorMapper.findAll();}

    @GetMapping("/{idActor}")
    public Actor getOne(@PathVariable int idActor) {
            log.log(Level.INFO, "Znalazlem aktora o id: " + idActor);
            return actorMapper.findByIdActor(idActor);
    }

    @GetMapping("/name")
    public Actor findActorByName(@RequestParam String name) {
        log.log(Level.INFO, "Znalazlem aktora o imieniu: " + name);
        return actorMapper.findByNameActor(name);
    }

    public int getNextIdActor() {
        int id;
        if(actorMapper.findMaxId() == null)
        {
            id = 1;
        }
        else
        {
            id = actorMapper.findMaxId().idActor + 1;
        }
        return id;
    }

    @RequestMapping("/addActor/confirmed")
    public void addActor(Actor actor)
    {
        actor.idActor = getNextIdActor();
        if(actorMapper.checkIfActorExists(actor) == null) actorMapper.insert(actor);
    }
}
