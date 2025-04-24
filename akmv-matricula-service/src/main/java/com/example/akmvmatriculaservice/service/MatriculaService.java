package com.example.akmvmatriculaservice.service;

import com.example.akmvmatriculaservice.entity.Matricula;

import java.util.List;

public interface MatriculaService {

    Matricula crearMatricula(Matricula matricula) throws Exception;

    List<Matricula> obtenerTodasLasMatriculas();

    // Obtener matrícula por ID
    Matricula obtenerMatriculaPorId(Integer id);

    // Actualizar matrícula
    Matricula actualizarMatricula(Integer id, Matricula matricula);

    // Eliminar matrícula
    boolean eliminarMatricula(Integer id);
}
