package com.example.akmvcursoservice.repository;

import com.example.akmvcursoservice.entity.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Integer> {
}
