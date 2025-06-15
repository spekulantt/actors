package sk.kasv.polakovsky.film_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sk.kasv.polakovsky.film_api.model.Actor;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Long> {
}