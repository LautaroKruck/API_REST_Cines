package diecines.controller;

import diecines.dto.PeliculaDTO;
import diecines.service.PeliculaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/peliculas")
public class PeliculaController {

    private final PeliculaService peliculaService;

    public PeliculaController(PeliculaService peliculaService) {
        this.peliculaService = peliculaService;
    }

    @PostMapping
    public ResponseEntity<PeliculaDTO> createPelicula(@RequestBody PeliculaDTO peliculaDTO) {
        return ResponseEntity.ok(peliculaService.createPelicula(peliculaDTO));
    }

    // Implementar otros endpoints...
}
