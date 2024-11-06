package diecines.repository;

import diecines.model.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PeliculaRepositoryAPI extends JpaRepository<Pelicula, Long> {
}
