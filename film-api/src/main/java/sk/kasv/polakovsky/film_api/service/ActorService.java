package sk.kasv.polakovsky.film_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.kasv.polakovsky.film_api.dto.ActorDto;
import sk.kasv.polakovsky.film_api.model.Actor;
import sk.kasv.polakovsky.film_api.repository.ActorRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ActorService {

    private final ActorRepository actorRepository;

    @Autowired
    public ActorService(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    public List<Actor> getAllActors() {
        return actorRepository.findAll();
    }

    public Optional<Actor> getActorById(Long id) {
        return actorRepository.findById(id);
    }

    public Actor createActor(ActorDto actorDto) {
        Actor actor = new Actor();
        actor.setName(actorDto.name());
        actor.setBirthDate(actorDto.birthDate());
        actor.setNationality(actorDto.nationality());
        return actorRepository.save(actor);
    }

    public Optional<Actor> updateActor(Long id, ActorDto actorDto) {
        return actorRepository.findById(id).map(existingActor -> {
            existingActor.setName(actorDto.name());
            existingActor.setBirthDate(actorDto.birthDate());
            existingActor.setNationality(actorDto.nationality());
            return actorRepository.save(existingActor);
        });
    }

    public boolean deleteActor(Long id) {
        if (actorRepository.existsById(id)) {
            actorRepository.deleteById(id);
            return true;
        }
        return false;
    }
}