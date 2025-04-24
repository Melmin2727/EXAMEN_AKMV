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
import org.springframework.stereotype.Service;

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
        Estudiante estudiante = estudianteFeignClient.getEstudianteById(matricula.getEstudianteId());
        if (estudiante == null) {
            throw new Exception("Estudiante no encontrado");
        }

        // Verificar si el estudiante está activo
        if (!"ACTIVO".equals(estudiante.getEstado())) {
            throw new Exception("El estudiante no está activo");
        }

        // Obtener curso usando Feign Client
        Curso curso = cursoFeignClient.getCursoById(matricula.getCursoCodigo());
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

    // Obtener matrícula por ID
    @Override
    public Matricula obtenerMatriculaPorId(Integer id) {
        Optional<Matricula> matriculaOptional = matriculaRepository.findById(id);
        return matriculaOptional.orElse(null); // Retorna null si no se encuentra la matrícula
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
}
