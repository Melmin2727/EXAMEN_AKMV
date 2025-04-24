package com.example.akmvestudianteservice.service;

import com.example.akmvestudianteservice.entity.Estudiante;

import java.util.List;

public interface EstudianteService {

    Estudiante crearEstudiante(Estudiante estudiante) throws Exception;

    List<Estudiante> obtenerTodosLosEstudiantes();

    Estudiante obtenerEstudiantePorId(Integer id);

    Estudiante actualizarEstudiante(Integer id, Estudiante estudiante);

    boolean eliminarEstudiante(Integer id);
}
