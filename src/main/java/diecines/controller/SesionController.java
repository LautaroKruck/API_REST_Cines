package diecines.controller;


import diecines.dto.SesionDTO;
import diecines.service.SesionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sesiones")
public class SesionController {

    private final SesionService sesionService;

    public SesionController(SesionService sesionService) {
        this.sesionService = sesionService;
    }

    @PostMapping
    public ResponseEntity<SesionDTO> createSesion(@RequestBody SesionDTO sesionDTO) {
        return ResponseEntity.ok(sesionService.createSesion(sesionDTO));
    }

    @GetMapping
    public ResponseEntity<List<SesionDTO>> getAllSesiones() {
        return ResponseEntity.ok(sesionService.getAllSesiones());
    }

    @PutMapping("/{id}")
    public ResponseEntity<SesionDTO> updateSesion(@PathVariable Long id, @RequestBody SesionDTO sesionDTO) {
        SesionDTO updatedSesion = sesionService.updateSesion(id, sesionDTO);
        return updatedSesion != null ? ResponseEntity.ok(updatedSesion) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSesion(@PathVariable Long id) {
        boolean deleted = sesionService.deleteSesion(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

}

