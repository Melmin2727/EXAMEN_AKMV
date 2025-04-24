package com.example.akmvmatriculaservice.feign;

import com.example.akmvcursoservice.entity.Curso;
import com.example.akmvmatriculaservice.dto.CursoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "akvm-curso-service", path = "/cursos")
public interface CursoFeign {

    @GetMapping("/{id}")
    public ResponseEntity<CursoDTO> obtenerCursoPorId(@PathVariable(required = true) Integer id);
}
