package diecines.repository;

import diecines.model.Sesion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SesionRepositoryAPI extends JpaRepository<Sesion, Long> {}
