package diecines.repository;

import diecines.model.Sesion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface SesionRepositoryAPI extends JpaRepository<Sesion, Long> {
}
