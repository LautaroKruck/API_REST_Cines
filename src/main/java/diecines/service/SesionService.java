package diecines.service;

import diecines.dto.SesionDTO;
import diecines.model.Sesion;
import diecines.model.Pelicula;
import diecines.repository.SesionRepositoryAPI;
import diecines.repository.PeliculaRepositoryAPI;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SesionService {

    private final SesionRepositoryAPI sesionRepository;
    private final PeliculaRepositoryAPI peliculaRepository;

    public SesionService(SesionRepositoryAPI sesionRepository, PeliculaRepositoryAPI peliculaRepository) {
        this.sesionRepository = sesionRepository;
        this.peliculaRepository = peliculaRepository;
    }

    public SesionDTO createSesion(SesionDTO sesionDTO) {
        Sesion sesion = new Sesion();
        sesion.setDate(sesionDTO.getDate());
        sesion.setRoomId(sesionDTO.getRoomId());

        // Verificar que la película existe
        Optional<Pelicula> peliculaOptional = peliculaRepository.findById(sesionDTO.getMovieId());
        if (peliculaOptional.isPresent()) {
            sesion.setPelicula(peliculaOptional.get());
        } else {
            throw new IllegalArgumentException("La película con ID " + sesionDTO.getMovieId() + " no existe.");
        }

        sesion = sesionRepository.save(sesion);
        return mapToDTO(sesion);
    }

    public List<SesionDTO> getAllSesiones() {
        List<Sesion> sesiones = sesionRepository.findAll();
        return sesiones.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public SesionDTO updateSesion(Long id, SesionDTO sesionDTO) {
        Optional<Sesion> sesionOptional = sesionRepository.findById(id);
        if (sesionOptional.isPresent()) {
            Sesion sesion = sesionOptional.get();
            sesion.setDate(sesionDTO.getDate());
            sesion.setRoomId(sesionDTO.getRoomId());

            // Verificar que la película existe antes de asignarla
            Optional<Pelicula> peliculaOptional = peliculaRepository.findById(sesionDTO.getMovieId());
            if (peliculaOptional.isPresent()) {
                sesion.setPelicula(peliculaOptional.get());
            } else {
                throw new IllegalArgumentException("La película con ID " + sesionDTO.getMovieId() + " no existe.");
            }

            sesion = sesionRepository.save(sesion);
            return mapToDTO(sesion);
        } else {
            throw new IllegalArgumentException("La sesión con ID " + id + " no existe.");
        }
    }

    public boolean deleteSesion(Long id) {
        if (sesionRepository.existsById(id)) {
            sesionRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private SesionDTO mapToDTO(Sesion sesion) {
        SesionDTO sesionDTO = new SesionDTO();
        sesionDTO.setId(sesion.getId());
        sesionDTO.setDate(sesion.getDate());
        sesionDTO.setRoomId(sesion.getRoomId());
        sesionDTO.setMovieId(sesion.getPelicula().getId());  // Extraemos el ID de la película
        return sesionDTO;
    }
}
