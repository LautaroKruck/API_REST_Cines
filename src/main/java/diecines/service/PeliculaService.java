package diecines.service;

import diecines.dto.PeliculaDTO;
import diecines.model.Pelicula;
import diecines.repository.PeliculaRepositoryAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PeliculaService {

    @Autowired
    private final PeliculaRepositoryAPI peliculaRepository;

    public PeliculaService(PeliculaRepositoryAPI peliculaRepository) {
        this.peliculaRepository = peliculaRepository;
    }

    public PeliculaDTO createPelicula(PeliculaDTO peliculaDTO) {
        Pelicula pelicula = new Pelicula();
        pelicula.setTitle(peliculaDTO.getTitle());
        pelicula.setDirector(peliculaDTO.getDirector());
        pelicula.setTime(peliculaDTO.getTime());
        pelicula.setTrailer(peliculaDTO.getTrailer());
        pelicula.setPosterImage(peliculaDTO.getPosterImage());
        pelicula.setScreenshot(peliculaDTO.getScreenshot());
        pelicula.setSynopsis(peliculaDTO.getSynopsis());
        pelicula.setRating(peliculaDTO.getRating());
        pelicula = peliculaRepository.save(pelicula);

        return mapToDTO(pelicula);
    }

    public PeliculaDTO getPeliculaById(Long id) {
        Optional<Pelicula> pelicula = peliculaRepository.findById(id);
        return pelicula.map(this::mapToDTO).orElse(null);
    }

    public List<PeliculaDTO> getAllPeliculas() {
        List<Pelicula> peliculas = peliculaRepository.findAll();
        return peliculas.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public PeliculaDTO updatePelicula(Long id, PeliculaDTO peliculaDTO) {
        Optional<Pelicula> peliculaOptional = peliculaRepository.findById(id);
        if (peliculaOptional.isPresent()) {
            Pelicula pelicula = peliculaOptional.get();
            pelicula.setTitle(peliculaDTO.getTitle());
            pelicula.setDirector(peliculaDTO.getDirector());
            pelicula.setTime(peliculaDTO.getTime());
            pelicula.setTrailer(peliculaDTO.getTrailer());
            pelicula.setPosterImage(peliculaDTO.getPosterImage());
            pelicula.setScreenshot(peliculaDTO.getScreenshot());
            pelicula.setSynopsis(peliculaDTO.getSynopsis());
            pelicula.setRating(peliculaDTO.getRating());
            pelicula = peliculaRepository.save(pelicula);
            return mapToDTO(pelicula);
        }
        return null;
    }

    public boolean deletePelicula(Long id) {
        if (peliculaRepository.existsById(id)) {
            peliculaRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private PeliculaDTO mapToDTO(Pelicula pelicula) {
        PeliculaDTO peliculaDTO = new PeliculaDTO();
        peliculaDTO.setId(pelicula.getId());
        peliculaDTO.setTitle(pelicula.getTitle());
        peliculaDTO.setDirector(pelicula.getDirector());
        peliculaDTO.setTime(pelicula.getTime());
        peliculaDTO.setTrailer(pelicula.getTrailer());
        peliculaDTO.setPosterImage(pelicula.getPosterImage());
        peliculaDTO.setScreenshot(pelicula.getScreenshot());
        peliculaDTO.setSynopsis(pelicula.getSynopsis());
        peliculaDTO.setRating(pelicula.getRating());
        return peliculaDTO;
    }

}
