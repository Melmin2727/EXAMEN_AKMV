package com.example.akmvmatriculaservice.repository;

import com.example.akmvmatriculaservice.entity.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatriculaRepository extends JpaRepository<Matricula, Integer> {
}
