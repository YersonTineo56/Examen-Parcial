package com.example.examen_parcial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.examen_parcial.model.Alumno;

@Repository
public interface AlumnoRepository extends JpaRepository<Alumno, Integer> {
    // Métodos personalizados opcionales pueden ser agregados aquí
}
