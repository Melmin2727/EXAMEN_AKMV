package com.example.akmvmatriculaservice.feign;

import com.example.akmvestudianteservice.entity.Estudiante;
import com.example.akmvmatriculaservice.dto.EstudianteDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "akvm-estudiante-service", path = "/estudiantes")
public interface EstudianteFeign {

    @GetMapping("/{id}")
    public ResponseEntity<EstudianteDTO> obtenerEstudiantePorId(@PathVariable(required = true) Integer id);
}
