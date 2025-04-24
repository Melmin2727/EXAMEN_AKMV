package com.example.akmvestudianteservice.repository;

import com.example.akmvestudianteservice.entity.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante, Integer> {
    boolean existsByDocumento(String documento);
}
