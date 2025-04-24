package com.example.akmvmatriculaservice.service;

import com.example.akmvmatriculaservice.entity.Matricula;

import java.util.List;
import java.util.Optional;

public interface MatriculaService {

    Matricula crearMatricula(Matricula matricula) throws Exception;

    List<Matricula> obtenerTodasLasMatriculas();

    Optional<Matricula> obtenerMatriculaPorId(Integer id);

    // Actualizar matrícula
    Matricula actualizarMatricula(Integer id, Matricula matricula);

    // Eliminar matrícula
    boolean eliminarMatricula(Integer id);
}
