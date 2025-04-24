package com.example.akmvmatriculaservice.feign;

import com.example.akmvmatriculaservice.entity.Estudiante;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "akmv-estudiante-service", path = "/estudiantes")
public interface EstudianteFeign {

    @GetMapping("/{id}")
    Estudiante getEstudianteById(@PathVariable("id") Integer id);
}
