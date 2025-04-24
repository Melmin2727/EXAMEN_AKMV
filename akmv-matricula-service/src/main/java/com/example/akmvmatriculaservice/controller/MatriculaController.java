package com.example.akmvmatriculaservice.controller;

import com.example.akmvmatriculaservice.entity.Matricula;
import com.example.akmvmatriculaservice.service.MatriculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/matriculas")
public class MatriculaController {

    @Autowired
    private MatriculaService matriculaService;

    // Crear una nueva matrícula
    @PostMapping
    public ResponseEntity<Matricula> crearMatricula(@RequestBody Matricula matricula) {
        try {
            Matricula nuevaMatricula = matriculaService.crearMatricula(matricula);
            return new ResponseEntity<>(nuevaMatricula, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    // Obtener todas las matrículas
    @GetMapping
    public ResponseEntity<List<Matricula>> obtenerTodasLasMatriculas() {
        try {
            List<Matricula> matriculas = matriculaService.obtenerTodasLasMatriculas();
            if (matriculas.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(matriculas, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Obtener matrícula por ID
    @GetMapping("/{id}")
    public ResponseEntity<Matricula> obtenerMatriculaPorId(@PathVariable("id") Integer id) {
        try {
            Matricula matricula = matriculaService.obtenerMatriculaPorId(id);
            if (matricula != null) {
                return new ResponseEntity<>(matricula, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Actualizar matrícula
    @PutMapping("/{id}")
    public ResponseEntity<Matricula> actualizarMatricula(@PathVariable("id") Integer id, @RequestBody Matricula matricula) {
        try {
            Matricula matriculaActualizada = matriculaService.actualizarMatricula(id, matricula);
            if (matriculaActualizada != null) {
                return new ResponseEntity<>(matriculaActualizada, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    // Eliminar matrícula
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> eliminarMatricula(@PathVariable("id") Integer id) {
        try {
            boolean eliminado = matriculaService.eliminarMatricula(id);
            if (eliminado) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
