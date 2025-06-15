package sk.kasv.polakovsky.film_api.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.kasv.polakovsky.film_api.dto.ActorDto;
import sk.kasv.polakovsky.film_api.model.Actor;
import sk.kasv.polakovsky.film_api.service.ActorService;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/api/actors")
@Tag(name = "Actors", description = "Operations related to actors")
public class ActorController {

    private final ActorService actorService;

    @Autowired
    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    @GetMapping
    @Operation(summary = "Get all actors", description = "Retrieve a list of all actors in the system")
    public List<Actor> getAllActors() {
        return actorService.getAllActors();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get actor by ID", description = "Retrieve an actor by their unique identifier")
    public ResponseEntity<Actor> getActorById(@PathVariable Long id) {
        return actorService.getActorById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Create a new actor", description = "Add a new actor to the system")
    public ResponseEntity<Actor> createActor(@Valid @RequestBody ActorDto actorDto) {
        Actor createdActor = actorService.createActor(actorDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdActor);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing actor", description = "Modify the details of an existing actor")
    public ResponseEntity<Actor> updateActor(@PathVariable Long id, @Valid @RequestBody ActorDto actorDto) {
        return actorService.updateActor(id, actorDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an actor", description = "Remove an actor from the system by their unique identifier")
    public ResponseEntity<Void> deleteActor(@PathVariable Long id) {
        if (actorService.deleteActor(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}