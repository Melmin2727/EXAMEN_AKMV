package com.example.akmvmatriculaservice.entity;

import com.example.akmvcursoservice.entity.Curso;
import com.example.akmvmatriculaservice.dto.CursoDTO;
import com.example.akmvmatriculaservice.dto.EstudianteDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

@Entity
public class Matricula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer estudianteId;

    @Column(nullable = false)
    private Integer cursoCodigo;

    @Column(nullable = false)
    private LocalDate fechaMatricula;

    @Column(nullable = false)
    private String ciclo;

    @Transient
    private CursoDTO curso;

    @Transient
    private EstudianteDTO estudiante;


    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "curso_id")
    private List<MatriculaCurso> detalle;


    // Getters y setters para curso y estudiante


    public CursoDTO getCurso() { return curso; }
    public void setCurso(CursoDTO curso) { this.curso = curso; }

    public EstudianteDTO getEstudiante() { return estudiante; }
    public void setEstudiante(EstudianteDTO estudiante) { this.estudiante = estudiante; }


    public Matricula() {}

    public Matricula(Integer id, Integer estudianteId, Integer cursoCodigo, LocalDate fechaMatricula, String ciclo) {
        this.id = id;
        this.estudianteId = estudianteId;
        this.cursoCodigo = cursoCodigo;
        this.fechaMatricula = fechaMatricula;
        this.ciclo = ciclo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEstudianteId() {
        return estudianteId;
    }

    public void setEstudianteId(Integer estudianteId) {
        this.estudianteId = estudianteId;
    }

    public Integer getCursoCodigo() {
        return cursoCodigo;
    }

    public void setCursoCodigo(Integer cursoCodigo) {
        this.cursoCodigo = cursoCodigo;
    }

    public LocalDate getFechaMatricula() {
        return fechaMatricula;
    }

    public void setFechaMatricula(LocalDate fechaMatricula) {
        this.fechaMatricula = fechaMatricula;
    }

    public String getCiclo() {
        return ciclo;
    }

    public void setCiclo(String ciclo) {
        this.ciclo = ciclo;
    }
}
