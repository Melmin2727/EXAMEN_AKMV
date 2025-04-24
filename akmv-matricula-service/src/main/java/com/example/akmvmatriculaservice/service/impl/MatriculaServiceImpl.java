package com.example.akmvmatriculaservice.service.impl;

import com.example.akmvcursoservice.entity.Curso;
import com.example.akmvestudianteservice.entity.Estudiante;
import com.example.akmvmatriculaservice.dto.CursoDTO;
import com.example.akmvmatriculaservice.dto.EstudianteDTO;
import com.example.akmvmatriculaservice.entity.Matricula;
import com.example.akmvmatriculaservice.feign.CursoFeign;
import com.example.akmvmatriculaservice.feign.EstudianteFeign;
import com.example.akmvmatriculaservice.repository.MatriculaRepository;
import com.example.akmvmatriculaservice.service.MatriculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("ALL")
@Service
public class MatriculaServiceImpl implements MatriculaService {

    @Autowired
    private MatriculaRepository matriculaRepository;

    @Autowired
    private EstudianteFeign estudianteFeignClient;

    @Autowired
    private CursoFeign cursoFeignClient;

    // Crear una nueva matrícula
    @Override
    public Matricula crearMatricula(Matricula matricula) throws Exception {
        // Obtener estudiante usando Feign Client
        EstudianteDTO estudiante = estudianteFeignClient.obtenerEstudiantePorId(matricula.getEstudianteId()).getBody();
        if (estudiante == null) {
            throw new Exception("Estudiante no encontrado");
        }

        // Verificar si el estudiante está activo
        if (!"ACTIVO".equals(estudiante.getEstado())) {
            throw new Exception("El estudiante no está activo");
        }

        // Obtener curso usando Feign Client
        CursoDTO curso = cursoFeignClient.obtenerCursoPorId(matricula.getCursoCodigo()).getBody();
        if (curso == null) {
            throw new Exception("Curso no encontrado");
        }

        // Verificar la capacidad del curso
        if (curso.getCapacidad() == null || curso.getCapacidad() <= 0) {
            throw new Exception("El curso no tiene capacidad disponible");
        }

        // Crear y guardar la matrícula
        return matriculaRepository.save(matricula);
    }

    // Obtener todas las matrículas
    @Override
    public List<Matricula> obtenerTodasLasMatriculas() {
        return matriculaRepository.findAll();
    }

    // Actualizar matrícula
    @Override
    public Matricula actualizarMatricula(Integer id, Matricula matricula) {
        if (matriculaRepository.existsById(id)) {
            matricula.setId(id);  // Establece el ID de la matrícula antes de actualizar
            return matriculaRepository.save(matricula);
        }
        return null; // Retorna null si no se encuentra la matrícula
    }

    // Eliminar matrícula
    @Override
    public boolean eliminarMatricula(Integer id) {
        if (matriculaRepository.existsById(id)) {
            matriculaRepository.deleteById(id);
            return true;
        }
        return false; // Retorna false si no se encuentra la matrícula
    }

    @Override
    public Optional<Matricula> obtenerMatriculaPorId(Integer id) {
        Matricula matricula = matriculaRepository.findById(id).orElse(null);
        if (matricula == null) return Optional.empty();

        try {
            ResponseEntity<CursoDTO> cursoResponse = cursoFeignClient.obtenerCursoPorId(matricula.getCursoCodigo());
            ResponseEntity<EstudianteDTO> estudianteResponse = estudianteFeignClient.obtenerEstudiantePorId(matricula.getEstudianteId());

            if (cursoResponse.getBody() == null) throw new Exception("Curso no encontrado");
            if (estudianteResponse.getBody() == null) throw new Exception("Estudiante no encontrado");

            matricula.setCurso(cursoResponse.getBody());
            matricula.setEstudiante(estudianteResponse.getBody());

            System.out.println("Estudiante ID: " + matricula.getEstudianteId());
            System.out.println("Curso Código: " + matricula.getCursoCodigo());
            System.out.println("Estudiante: " + estudianteResponse.getBody());
            System.out.println("Curso: " + cursoResponse.getBody());

        } catch (Exception e) {
            throw new RuntimeException("Error al obtener datos relacionados: " + e.getMessage());
        }

        return Optional.of(matricula);
    }
}
