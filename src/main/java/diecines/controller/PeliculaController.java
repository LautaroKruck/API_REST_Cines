package diecines.controller;

import diecines.dto.PeliculaDTO;
import diecines.service.PeliculaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<PeliculaDTO>> getAllPeliculas() {
        return ResponseEntity.ok(peliculaService.getAllPeliculas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PeliculaDTO> getPeliculaById(@PathVariable Long id) {
        PeliculaDTO peliculaDTO = peliculaService.getPeliculaById(id);

        if (peliculaDTO != null) {
            return ResponseEntity.ok(peliculaDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<PeliculaDTO> updatePelicula(@PathVariable Long id, @RequestBody PeliculaDTO peliculaDTO) {
        PeliculaDTO updatedPelicula = peliculaService.updatePelicula(id, peliculaDTO);

        if (updatedPelicula != null) {
            return ResponseEntity.ok(updatedPelicula);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePelicula(@PathVariable Long id) {
        boolean deleted = peliculaService.deletePelicula(id);

        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    // Implementar otros endpoints...
}
