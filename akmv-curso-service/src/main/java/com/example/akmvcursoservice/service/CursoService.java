package com.example.akmvcursoservice.service;

import com.example.akmvcursoservice.entity.Curso;

import java.util.List;

public interface CursoService {
    Curso crearCurso(Curso curso);
    List<Curso> obtenerTodos();
    Curso obtenerPorId(Integer id);
    Curso actualizarCurso(Integer id, Curso curso);
    boolean eliminarCurso(Integer id);
}
